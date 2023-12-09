package io.socket.engineio.transports;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.socket.engineio.Transport;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class EngineIOWebSocketTransport extends SimpleChannelInboundHandler<WebSocketFrame> implements TransportName {

    @Override
    public String getName() {
        return Transport.WEBSOCKET.getName();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        log.debug("WebSocketFrame => {}",msg.content().toString(StandardCharsets.UTF_8));
    }
}
