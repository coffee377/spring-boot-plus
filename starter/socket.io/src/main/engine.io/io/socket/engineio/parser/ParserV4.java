package io.socket.engineio.parser;

import io.socket.engineio.protocol.EngineIO;
import io.socket.engineio.protocol.EngineIO.Packet;
import io.socket.engineio.protocol.EngineIO.PacketType;
import io.socket.engineio.protocol.EngineIO.Version;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public final class ParserV4 implements Parser {

    private static final String SEPARATOR = "\u001E";   // (char) 30

    @Override
    public int getProtocolVersion() {
        return Version.V4.getValue();
    }

    @Override
    public Object encode(Packet<?> packet, boolean supportsBinary) {
        Object data = packet.getData();
        if (data instanceof byte[]) {
            return encodeByteArray((Packet<byte[]>) packet, supportsBinary);
        } else {
            String encoded = packet.getTypeSting();

            if (null != packet.getData()) {
                encoded += String.valueOf(packet.getData());
            }
            return encoded;
        }
    }

    @Override
    public String encode(List<Packet<?>> packets, boolean supportsBinary) {
        return packets.stream()
                .map(packet -> (String) encode(packet, false))
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public Packet<?> decode(Object data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't null");
        }
        if (data instanceof String) {
            return decode((String) data);
        } else if (data instanceof byte[]) {
            return decode((byte[]) data);
        } else {
            throw new IllegalArgumentException("Invalid type for data: " + data.getClass().getSimpleName());
        }
    }

    @Override
    public Packet<?> decode(String data) {
        char type = data.charAt(0);
        String content = data.substring(1);
        if (type == 'b') {
            Packet<byte[]> packet = new EngineIO.Packet<>(EngineIO.PacketType.MESSAGE);
            packet.setData(Base64.getDecoder().decode(content));
            return packet;
        } else {
            PacketType packetType = PacketType.from(type & 0xf);
            Packet<String> packet = new Packet<>(packetType);
            packet.setData(content);
            return packet;
        }
    }

    @Override
    public Packet<?> decode(byte[] data) {
        return new Packet<>(EngineIO.PacketType.MESSAGE, data);
    }

    @Override
    public List<Packet<?>> decodePayload(String payload) {
        return Arrays.stream(payload.split(SEPARATOR))
                .map(this::decode)
                .collect(Collectors.toList());
    }

    private Object encodeByteArray(Packet<byte[]> packet, boolean supportsBinary) {
        if (supportsBinary) {
            return packet.getData();
        } else {
            return "b" + Base64.getEncoder().encodeToString(packet.getData());
        }
    }

}
