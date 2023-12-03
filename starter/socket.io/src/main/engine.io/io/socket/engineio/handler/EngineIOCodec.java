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

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.Transport;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.*;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import io.socket.engineio.protocol.EngineIO.*;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.socket.engineio.parser.Parser;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
@Sharable
public class EngineIOCodec extends ChannelDuplexHandler {
    private boolean allowCustomRequests;
    private String connectPath;
    private ObjectMapper objectMapper;
    private Parser parser;

    public EngineIOCodec() {
        this(false, "/socket.io/", Jackson2ObjectMapperBuilder.json().build());
        this.parser = io.socket.engineio.parser.Parser.PROTOCOL_V4;
    }

    public EngineIOCodec(boolean allowCustomRequests, String connectPath, ObjectMapper objectMapper) {
        this.allowCustomRequests = allowCustomRequests;
        this.connectPath = connectPath;
        this.objectMapper = objectMapper;
    }

    public void setAllowCustomRequests(boolean allowCustomRequests) {
        this.allowCustomRequests = allowCustomRequests;
    }

    public void setConnectPath(String connectPath) {
        this.connectPath = connectPath;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest req = (HttpRequest) msg;
            Channel channel = ctx.channel();
            QueryStringDecoder queryDecoder = new QueryStringDecoder(req.uri());

            /* 获取握手时的数据 */
            HandshakeRequest handshakeRequest = HandshakeRequest.from(req);

            log.debug("{} {}", req.method(), req.uri());

            /*  不允许使用自定义请求头 */
            if (!allowCustomRequests && !handshakeRequest.getPath().startsWith(connectPath)) {
                HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
                channel.writeAndFlush(res).addListener(ChannelFutureListener.CLOSE);
                ReferenceCountUtil.release(req);
                return;
            }

            if (handshakeRequest.getPath().equals(connectPath) && handshakeRequest.isFirst()) {
                String origin = req.headers().get(HttpHeaderNames.ORIGIN);
                HandshakeData data = new HandshakeData(req.headers(), queryDecoder.parameters(),
                        (InetSocketAddress) channel.remoteAddress(),
                        (InetSocketAddress) channel.localAddress(),
                        req.uri(), origin != null && !origin.equalsIgnoreCase("null"));
                // todo 首次握手数据回调，接受连接后才发生 open packet
                boolean success = onHandshake(data);
                if (!success) {
                    HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
                    channel.writeAndFlush(res).addListener(ChannelFutureListener.CLOSE);
                }
                UUID sessionId = this.generateOrGetSessionIdFromRequest(req.headers());
                Transport[] upgradeTransports = {Transport.WEBSOCKET};

                HandshakeResponse<UUID> handshakeResponse = new HandshakeResponse<>(sessionId, upgradeTransports);
//                Packet<HandshakeResponse> openPacket = new Packet<>(PacketType.OPEN,handshakeResponse);

                final ByteBuf encBuf = ctx.alloc().directBuffer();
                OutputStream out = new ByteBufOutputStream(encBuf);
                objectMapper.writeValue(out, handshakeResponse);
//                byte[] dst = new byte[encBuf.readableBytes()];
//                encBuf.readBytes(dst);
                Packet<ByteBuf> openPacket = new Packet<>(PacketType.OPEN);
                openPacket.setData(encBuf);

                /* https://socket.io/zh-CN/docs/v4/engine-io-protocol/#handshake */
//                client.send(packet);
//                objectMapper.w
//                HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
                channel.writeAndFlush(openPacket);
//                ctx.fireChannelRead(openPacket);
                /* heartbeat 心跳监测 */
//                /* https://socket.io/docs/v4/engine-io-protocol/#heartbeat */
//                client.schedulePing();
//                client.schedulePingTimeout();
//                log.debug("Handshake for sessionId: {}, query params: {} headers: {}", sessionId, params, headers);
            } else {
                HttpMethod method = handshakeRequest.getMethod();
                if (HttpMethod.GET.equals(method)) {
                    log.debug("GET requests, for receiving data from the server");
                    HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
                    channel.write(res);

                    ByteBuf out = ctx.alloc().buffer(8);
                    out.writeCharSequence("ok", StandardCharsets.UTF_8);
                    HttpUtil.setContentLength(res, out.readableBytes());
                    if (out.isReadable()) {
                        channel.write(new DefaultHttpContent(out));
                    } else {
                        out.release();
                    }
                    channel.write(new DefaultHttpContent(out));

                    channel.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT).addListener(ChannelFutureListener.CLOSE);
                    ReferenceCountUtil.release(req);
                } else if (HttpMethod.POST.equals(method)) {
                    log.debug("POST requests, for sending data to the server");
                    HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
                    channel.write(res);

                    ByteBuf out = ctx.alloc().buffer(8);
                    Packet<String> packet = new Packet<>(PacketType.MESSAGE, String.format("0{\"sid\":\"%s\"}",
                            handshakeRequest.getSid()));
                    this.parser.encodePacket(packet, false, data -> {
                        if (data instanceof byte[]) {
                            out.writeBytes((byte[]) data);
                        } else if (data instanceof String) {
                            out.writeCharSequence((String) data, StandardCharsets.UTF_8);
                        }
                    });
                    HttpUtil.setContentLength(res, out.readableBytes());
                    if (out.isReadable()) {
                        channel.write(new DefaultHttpContent(out));
                    } else {
                        out.release();
                    }

                    channel.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT).addListener(ChannelFutureListener.CLOSE);
                    ReferenceCountUtil.release(req);
                }
            }
        }
        else {
            ctx.fireChannelRead(msg);
        }

    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Packet) {
            Packet<?> packet = (Packet<?>) msg;
            ByteBuf out = ctx.alloc().buffer(8);
            this.parser.encodePacket(packet, false, data -> {
                if (data instanceof byte[]) {
                    out.writeBytes((byte[]) data);
                } else if (data instanceof String) {
                    out.writeCharSequence((String) data, StandardCharsets.UTF_8);
                }
            });
//            out.writeByte(PacketType.OPEN.getByte());
//            String result = objectMapper.writeValueAsString(((Packet<?>) msg).getData());
//            out.writeCharSequence(result, StandardCharsets.UTF_8);
            log.debug("{}", out.toString(StandardCharsets.UTF_8));
            Channel channel = ctx.channel();

            HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
            HttpUtil.setContentLength(res, out.readableBytes());
            channel.write(res);

            if (out.isReadable()) {
                channel.write(new DefaultHttpContent(out));
            } else {
                out.release();
            }

            channel.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT, promise).addListener(ChannelFutureListener.CLOSE);
            return;
        }
        super.write(ctx, msg, promise);
    }

    private byte toChar(int number) {
        return (byte) (number ^ 0x30);
    }

    private boolean onHandshake(HandshakeData data) {
        return true;
    }

    private UUID generateOrGetSessionIdFromRequest(HttpHeaders headers) {
        List<String> values = headers.getAll("io");
        if (values.size() == 1) {
            try {
                return UUID.fromString(values.get(0));
            } catch (IllegalArgumentException iaex) {
                log.warn("Malformed UUID received for session! io=" + values.get(0));
            }
        }

        for (String cookieHeader : headers.getAll(HttpHeaderNames.COOKIE)) {
            Set<io.netty.handler.codec.http.cookie.Cookie> cookies = ServerCookieDecoder.LAX.decode(cookieHeader);

            for (Cookie cookie : cookies) {
                if (cookie.name().equals("io")) {
                    try {
                        return UUID.fromString(cookie.value());
                    } catch (IllegalArgumentException iaex) {
                        log.warn("Malformed UUID received for session! io=" + cookie.value());
                    }
                }
            }
        }

        return UUID.randomUUID();
    }
}
