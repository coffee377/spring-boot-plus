package io.socket.engineio.parser;

import io.socket.engineio.protocol.EngineIO;

import java.util.List;

public interface Decoder {
    EngineIO.Packet<?> decode(Object data);
    EngineIO.Packet<?> decode(String data);
    EngineIO.Packet<?> decode(byte[] data);
    List<EngineIO.Packet<?>> decodePayload(String payload);
}
