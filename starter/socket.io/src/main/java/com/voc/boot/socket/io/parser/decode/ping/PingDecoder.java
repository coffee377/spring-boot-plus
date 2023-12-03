package com.voc.boot.socket.io.parser.decode.ping;

import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketType;
import com.voc.boot.socket.io.parser.decode.Decoder;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PingDecoder implements Decoder<ByteBuf, Packet> {

    @Override
    public boolean test(DecoderContext<ByteBuf, Packet> context) {
        return Decoder.super.test(context) && context.geTagret().getType().equals(PacketType.PING);
    }
}
