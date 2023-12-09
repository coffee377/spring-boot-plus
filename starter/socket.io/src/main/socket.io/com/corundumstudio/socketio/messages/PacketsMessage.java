package com.corundumstudio.socketio.messages;

import io.netty.buffer.ByteBuf;

import io.socket.engineio.Transport;
import com.corundumstudio.socketio.handler.ClientHead;

public class PacketsMessage {

    private final ClientHead client;
    private final ByteBuf content;
    private final Transport transport;

    public PacketsMessage(ClientHead client, ByteBuf content, Transport transport) {
        this.client = client;
        this.content = content;
        this.transport = transport;
    }

    public Transport getTransport() {
        return transport;
    }

    public ClientHead getClient() {
        return client;
    }

    public ByteBuf getContent() {
        return content;
    }

}
