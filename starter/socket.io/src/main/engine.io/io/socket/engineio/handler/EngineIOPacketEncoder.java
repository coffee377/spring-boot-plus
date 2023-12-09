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
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.*;
import io.socket.engineio.parser.Parser;
import io.socket.engineio.protocol.EngineIO;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
@Sharable
public class EngineIOPacketEncoder extends MessageToMessageEncoder<EngineIO.Packet> {
    private final Parser parser = Parser.PROTOCOL_V4;
    @Override
    protected void encode(ChannelHandlerContext ctx, EngineIO.Packet msg, List<Object> out) throws Exception {
        /* 服务端发送信息 */
        AtomicBoolean binary = new AtomicBoolean(false);
        ByteBuf out2 = ctx.alloc().buffer(8);
        this.parser.encodePacket(msg, false, data -> {
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
}
