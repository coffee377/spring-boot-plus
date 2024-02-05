package io.socket.engineio.parser;

import io.socket.engineio.protocol.EngineIO.Packet;

import java.util.List;

@SuppressWarnings("unchecked")
public interface Parser extends Encoder, Decoder {

    //    Parser PROTOCOL_V3 = new ParserV3();
    Parser PROTOCOL_V4 = new ParserV4();

    int getProtocolVersion();

    interface EncodeCallback<T> {
        void call(T data);
    }

    /**
     * Encode a packet for transfer over transport.
     *
     * @param packet         The packet to encode.
     * @param supportsBinary Whether the transport supports binary encoding.
     * @param callback       The callback to be called with the encoded data.
     */
    @Deprecated
    default void encodePacket(Packet<?> packet, boolean supportsBinary, EncodeCallback<Object> callback) {
        Object encoded = encode(packet, supportsBinary);
        callback.call(encoded);
    }

    /**
     * Encode an array of packets into a payload for transfer over transport.
     *
     * @param packets  Array of packets to encode.
     * @param callback The callback to be called with the encoded data.
     */
    @Deprecated
    default void encodePayload(List<Packet<?>> packets, boolean supportsBinary, EncodeCallback<Object> callback) {
        String encoded = encode(packets, supportsBinary);
        callback.call(encoded);
    }

    interface DecodePayloadCallback<T> {
        boolean call(Packet<T> packet, int index, int total);
    }

    /**
     * Decode a packet received from transport.
     *
     * @param data Data received from transport.
     * @return Packet decoded from data.
     */
    @Deprecated
    default Packet<?> decodePacket(Object data) {
        return decode(data);
    }

    /**
     * Decode payload received from transport.
     *
     * @param data     Data received from transport.
     * @param callback The callback to be called with each decoded packet in payload.
     */
    @Deprecated
    default void decodePayload(Object data, DecodePayloadCallback<Object> callback) {
        if (!(data instanceof String)) {
            throw new IllegalArgumentException("data must be a String");
        }

        List<Packet<?>> packets = decodePayload((String) data);
        for (int i = 0; i < packets.size(); i++) {
            if (!callback.call((Packet<Object>) packets.get(i), i, packets.size())) {
                return;
            }
        }
    }

}
