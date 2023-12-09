package io.socket.engineio.transports;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.socket.engineio.Transport;
import io.socket.engineio.handler.HandshakeRequest;
import io.socket.engineio.parser.Parser;
import io.socket.engineio.protocol.EngineIO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
public class EngineIOPollingTransport extends SimpleChannelInboundHandler<FullHttpRequest> implements TransportName {
    private final Parser parser = Parser.PROTOCOL_V4;

    public EngineIOPollingTransport() {
        log.error("每次都会创建");
    }

    @Override
    public String getName() {
        return Transport.POLLING.getName();
    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        return super.acceptInboundMessage(msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        log.debug("FullHttpRequest => {}", req.content().toString(StandardCharsets.UTF_8));
        QueryStringDecoder queryDecoder = new QueryStringDecoder(req.uri());
        /* 获取握手时的数据 */
        HandshakeRequest handshakeRequest = HandshakeRequest.from(req);
        if (!handshakeRequest.isFirst() && handshakeRequest.getTransport().getName().equals(getName())) {
            handleMessage(ctx, req, handshakeRequest.getSessionId(), queryDecoder);
        }
    }

    private void handleMessage(ChannelHandlerContext ctx, FullHttpRequest req, String sessionId, QueryStringDecoder queryDecoder)
            throws IOException {
        String origin = req.headers().get(HttpHeaderNames.ORIGIN);
        if (HttpMethod.POST.equals(req.method())) {
            onPost(sessionId, ctx, origin, req.content());
        } else if (HttpMethod.GET.equals(req.method())) {
            onGet(sessionId, ctx, origin);
        } else if (HttpMethod.OPTIONS.equals(req.method())) {
            onOptions(sessionId, ctx, origin);
        } else {
            if (log.isErrorEnabled()) {
                log.error("Wrong {} method invocation for {}", req.method(), sessionId);
            }
//            sendError(ctx);
        }
    }

    private void onOptions(String sessionId, ChannelHandlerContext ctx, String origin) {

    }

    private void onGet(String sessionId, ChannelHandlerContext ctx, String origin) {

    }

    private void onPost(String sessionId, ChannelHandlerContext ctx, String origin, ByteBuf content) {
        // release POST response before message processing
        HttpResponse res = new DefaultHttpResponse(HTTP_1_1, HttpResponseStatus.OK);
        ctx.channel().write(res);
        ByteBuf ok = ctx.alloc().directBuffer();
        ok.writeCharSequence("ok", StandardCharsets.UTF_8);

        ctx.channel().write(new DefaultHttpContent(ok));
        ok.release();
        ctx.channel().flush();
//        ctx.channel().writeAndFlush(res);

        log.debug("post data {}", content.toString(StandardCharsets.UTF_8));

//        Boolean b64 = ctx.channel().attr(EncoderHandler.B64).get();
//        if (b64 != null && b64) {
//            Integer jsonIndex = ctx.channel().attr(EncoderHandler.JSONP_INDEX).get();
//            content = decoder.preprocessJson(jsonIndex, content);
//        }
        ByteBuf cb = content.readBytes(content.readableBytes());
        EngineIO.Packet<?> packet = parser.decodePacket(cb.toString(StandardCharsets.UTF_8));
        ctx.pipeline().fireChannelRead(packet);
    }


}
