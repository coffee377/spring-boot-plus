
package io.socket.engineio.handler;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.scheduler.SchedulerKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.util.ReferenceCountUtil;
import io.socket.engineio.Transport;
import io.socket.engineio.parser.Parser;
import io.socket.engineio.protocol.EngineIO.Packet;
import io.socket.engineio.protocol.EngineIO.PacketType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
@Sharable
public class EngineIOHandshake extends SimpleChannelInboundHandler<FullHttpRequest> {
    private boolean allowCustomRequests;
    private String connectPath;
    private ObjectMapper objectMapper;
    private Parser parser;

    private SchedulerKey key;

    public EngineIOHandshake() {
        this(false, "/socket.io/", Jackson2ObjectMapperBuilder.json().build());
        this.parser = Parser.PROTOCOL_V4;
    }

    public EngineIOHandshake(boolean allowCustomRequests, String connectPath, ObjectMapper objectMapper) {
        super();
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
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
//        key = new SchedulerKey(SchedulerKey.Type.PING_TIMEOUT, ctx.channel());
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        scheduler.schedule(key, () -> {
//            ctx.channel().close();
//            log.debug("Client with ip {} opened channel but doesn't send any data! Channel closed!", ctx.channel().remoteAddress());
//        }, configuration.getFirstDataTimeout(), TimeUnit.MILLISECONDS);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
//        scheduler.cancel(key);
        Channel channel = ctx.channel();
        QueryStringDecoder queryDecoder = new QueryStringDecoder(req.uri());
        Map<String, List<String>> parameters = queryDecoder.parameters();

        /* 获取握手时的数据 */
        HandshakeRequest handshakeRequest = HandshakeRequest.from(req);
        if (handshakeRequest.getSessionId() != null) {
            return;
        }

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
            HandshakeData handshakeData = new HandshakeData(req.headers(), queryDecoder.parameters(),
                    (InetSocketAddress) channel.remoteAddress(),
                    (InetSocketAddress) channel.localAddress(),
                    req.uri(), origin != null && !origin.equalsIgnoreCase("null"));
            // todo 首次握手数据回调，接受连接后才发生 open packet
            boolean success = onHandshake(handshakeData);
            if (!success) {
                ByteBuf reason = ctx.alloc().buffer();
                reason.writeCharSequence("握手前置失败", StandardCharsets.UTF_8);
                FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST, reason);
                channel.writeAndFlush(res).addListener(ChannelFutureListener.CLOSE);
                return;
            }

            UUID sessionId = this.generateOrGetSessionIdFromRequest(req.headers());

            HandshakeData data = new HandshakeData(req.headers(), queryDecoder.parameters(),
                    (InetSocketAddress) channel.remoteAddress(),
                    (InetSocketAddress) channel.localAddress(),
                    req.uri(), origin != null && !origin.equalsIgnoreCase("null"));

//            ClientHead client = new ClientHead(sessionId, ackManager, disconnectable, storeFactory, data, clientsBox,
//                    handshakeRequest.getTransport(), scheduler, configuration, parameters);
//            channel.attr(ClientHead.CLIENT).set(client);
//            clientsBox.addClient(client);

            Transport[] upgradeTransports = {Transport.WEBSOCKET};
            HandshakeResponse<UUID> handshakeResponse = new HandshakeResponse<>(sessionId, upgradeTransports);
            String s = objectMapper.writeValueAsString(handshakeResponse);

            Packet<String> packet = new Packet<>(PacketType.OPEN, s);
            /* https://socket.io/zh-CN/docs/v4/engine-io-protocol/#handshake */
            // client.send(packet);
            channel.writeAndFlush(packet);

            /* https://socket.io/docs/v4/engine-io-protocol/#heartbeat */
//            client.schedulePing();
//            client.schedulePingTimeout();

//            Map<String, List<String>> headers = new HashMap<>(req.headers().names().size());
//            for (String name : req.headers().names()) {
//                List<String> values = req.headers().getAll(name);
//                headers.put(name, values);
//            }

            log.debug("Handshake for sessionId: {}, query params: {}", sessionId, parameters);
        }
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
            Set<Cookie> cookies = ServerCookieDecoder.LAX.decode(cookieHeader);

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
