package io.socket.engineio.parser;

import io.netty.buffer.ByteBuf;
import io.socket.engineio.protocol.EngineIO;

import java.util.List;

public interface Decoder {

    EngineIO.Packet<?> decode(String data);

    EngineIO.Packet<?> decode(byte[] data);

    EngineIO.Packet<?> decode(ByteBuf buffer);

    List<EngineIO.Packet<Object>> decode(String payload, String separator);
}
