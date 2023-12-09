package io.socket.engineio.protocol;

import io.netty.buffer.ByteBuf;
import io.socket.engineio.Transport;

public interface EngineMessage {

    ByteBuf getContent();

    Transport getTransport();
}
