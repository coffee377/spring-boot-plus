package com.voc.boot.socket.io.parser.decode.ping;

import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PingDataDecoder extends PingDecoder {
    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("2. lookup Engine.IO packet ping data");
        ByteBuf buf = context.getData();
        Packet packet = context.geTagret();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String data = new String(bytes, CharsetUtil.UTF_8);
        packet.setData(data);
    }

}
