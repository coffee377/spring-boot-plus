package io.socket.engineio.parser;

import io.socket.engineio.protocol.EngineIO;

import java.util.List;

public interface Encoder {
    Object encode(EngineIO.Packet<?> packet, boolean supportsBinary);
    String encode(List<EngineIO.Packet<?>> packets, boolean supportsBinary);
}
