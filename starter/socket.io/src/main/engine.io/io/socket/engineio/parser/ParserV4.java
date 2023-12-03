package io.socket.engineio.parser;

import io.netty.buffer.ByteBuf;
import io.socket.engineio.protocol.EngineIO.*;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@SuppressWarnings("unchecked")
public final class ParserV4 implements Parser {

    private static final String SEPARATOR = "\u001E";   // (char) 30

    @Override
    public int getProtocolVersion() {
        return Version.V4.getValue();
    }

    /**
     * Encode a packet for transfer over transport.
     *
     * @param packet         The packet to encode.
     * @param supportsBinary Whether the transport supports binary encoding.
     * @param callback       The callback to be called with the encoded data.
     */
    @Override
    public void encodePacket(Packet<?> packet, boolean supportsBinary, EncodeCallback<Object> callback) {
        Object data = packet.getData();
        if (data instanceof byte[]) {
            encodeByteArray((Packet<byte[]>) packet, supportsBinary, callback);
        } else if (data instanceof ByteBuf) {
            String encoded = String.valueOf(packet.getType().getValue());
            ByteBuf res = (ByteBuf) data;
            encoded += String.valueOf(res.toString(StandardCharsets.UTF_8));
            res.release();
            callback.call(encoded);
        } else {
            String encoded = String.valueOf(packet.getType().getValue());

            if (null != packet.getData()) {
                encoded += String.valueOf(packet.getData());
            }

            callback.call(encoded);
        }
    }

    /**
     * Encode an array of packets into a payload for transfer over transport.
     *
     * @param packets  Array of packets to encode.
     * @param callback The callback to be called with the encoded data.
     */
    @Override
    public void encodePayload(List<Packet<?>> packets, boolean supportsBinary, EncodeCallback<Object> callback) {
        final String[] encodedPackets = new String[packets.size()];
        for (int i = 0; i < encodedPackets.length; i++) {
            final Packet<?> packet = packets.get(i);

            final int packetIdx = i;
            encodePacket(packet, false, data -> encodedPackets[packetIdx] = (String) data);
        }

        callback.call(String.join(SEPARATOR, encodedPackets));
    }

    /**
     * Decode a packet received from transport.
     *
     * @param data Data received from transport.
     * @return Packet decoded from data.
     */
    @Override
    public Packet<?> decodePacket(Object data) {
        if(data == null) {
            throw new IllegalArgumentException("data can't null");
        }
        if (data instanceof String) {
            final String stringData = (String) data;
            if (stringData.charAt(0) == 'b') {
                final Packet<byte[]> packet = new Packet<>(PacketType.MESSAGE);
                packet.setData(Base64.getDecoder().decode(stringData.substring(1)));
                return packet;
            } else {
                PacketType packetType = PacketType.from(stringData.charAt(0) & 0xf);
                final Packet<String> packet = new Packet<>(packetType);
                packet.setData(stringData.substring(1));
                return packet;
            }
        } else if (data instanceof byte[]) {
            return new Packet<>(PacketType.MESSAGE, (byte[]) data);
        } else {
            throw new IllegalArgumentException("Invalid type for data: " + data.getClass().getSimpleName());
        }
    }

    /**
     * Decode payload received from transport.
     *
     * @param data     Data received from transport.
     * @param callback The callback to be called with each decoded packet in payload.
     */
    @Override
    public void decodePayload(Object data, DecodePayloadCallback<Object> callback) {
        assert callback != null;

        final ArrayList<Packet<?>> packets = new ArrayList<>();
        if (data instanceof String) {
            final String[] encodedPackets = ((String) data).split(SEPARATOR);
            for (String encodedPacket : encodedPackets) {
                final Packet<?> packet = decodePacket(encodedPacket);
                packets.add(packet);
            }
        } else {
            throw new IllegalArgumentException("data must be a String");
        }

        for (int i = 0; i < packets.size(); i++) {
            if (!callback.call((Packet<Object>) packets.get(i), i, packets.size())) {
                return;
            }
        }
    }

    private void encodeByteArray(Packet<byte[]> packet, boolean supportsBinary, EncodeCallback<Object> callback) {
        if (supportsBinary) {
            callback.call(packet.getData());
        } else {
            final String resultBuilder = "b" + Base64.getEncoder().encodeToString(packet.getData());
            callback.call(resultBuilder);
        }
    }

}
