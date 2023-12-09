/**
 * Copyright (c) 2012-2023 Nikita Koksharov
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.socket.engineio.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.*;
import io.socket.engineio.parser.Parser;
import io.socket.engineio.protocol.EngineIO;
import io.socket.engineio.protocol.EngineIO.Packet;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
@Sharable
public class EngineIOCodec extends MessageToMessageCodec<Packet, Packet> {
    private final Parser parser = Parser.PROTOCOL_V4;
    private final boolean supportBinary = false;
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        /* 服务端发送信息 */
        AtomicBoolean binary = new AtomicBoolean(false);
        ByteBuf out2 = ctx.alloc().buffer(8);
        this.parser.encodePacket(msg, supportBinary, data -> {
            if (data instanceof byte[]) {
                binary.set(true);
                out2.writeBytes((byte[]) data);
            } else if (data instanceof String) {
                out2.writeCharSequence((String) data, StandardCharsets.UTF_8);
            }
        });

        // http 长轮询发送的消息
        HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
        if (binary.get()) {
            res.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_OCTET_STREAM);
        }
        HttpUtil.setContentLength(res, out2.readableBytes());

        out.add(res);
        out.add(new DefaultHttpContent(out2));
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        EngineIO.PacketType packetType = msg.getType();
        switch (packetType) {
            case OPEN:
                /* heartbeat 心跳监测 */
//                /* https://socket.io/docs/v4/engine-io-protocol/#heartbeat */
//                client.schedulePing();
//                client.schedulePingTimeout();
//                log.debug("Handshake for sessionId: {}, query params: {} headers: {}", sessionId, params, headers);
//                out.add(msg);
//                ctx.writeAndFlush(msg);
//                return;
//                ctx.channel().writeAndFlush(msg);
//                out.forEach(ctx::write);
            case CLOSE:
                ctx.channel().close();
            case PING:
            case PONG:
            case MESSAGE:
            case UPGRADE:
            case NOOP:
            default:
                log.debug("接收头信息 {}", msg);
        }

//        Packet<?> packet = (Packet<?>) msg;
//        ByteBuf out = ctx.alloc().buffer(8);
//        AtomicBoolean binary = new AtomicBoolean(false);
////        this.parser.encodePacket(packet, false, data -> {
////            if (data instanceof byte[]) {
////                binary.set(true);
////                out.writeBytes((byte[]) data);
////            } else if (data instanceof String) {
////                out.writeCharSequence((String) data, StandardCharsets.UTF_8);
////            }
////        });
//
//        log.debug("{}", out.toString(StandardCharsets.UTF_8));
//        Channel channel = ctx.channel();
//
//        HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
//        if (binary.get()) {
//            res.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_OCTET_STREAM);
//        }
//        HttpUtil.setContentLength(res, out.readableBytes());
//        channel.write(res);
//
//        if (out.isReadable()) {
//            channel.write(new DefaultHttpContent(out));
//        } else {
//            out.release();
//        }
//
//        channel.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT, promise).addListener(ChannelFutureListener.CLOSE);

    }
}
