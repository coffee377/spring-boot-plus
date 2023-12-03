package io.socket.engineio.parser;

import io.socket.engineio.protocol.EngineIO.Packet;

import java.util.List;

public interface Parser {

    Parser PROTOCOL_V3 = new ParserV3();
    Parser PROTOCOL_V4 = new ParserV4();

    int getProtocolVersion();

    interface EncodeCallback<T> {
        void call(T data);
    }
    void encodePacket(Packet<?> packet, boolean supportsBinary, EncodeCallback<Object> callback);
    void encodePayload(List<Packet<?>> packets, boolean supportsBinary, EncodeCallback<Object> callback);

    interface DecodePayloadCallback<T> {
        boolean call(Packet<T> packet, int index, int total);
    }
    Packet<?> decodePacket(Object data);
    void decodePayload(Object data, DecodePayloadCallback<Object> callback);
}
