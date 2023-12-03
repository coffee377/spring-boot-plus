package com.voc.boot.socket.io.parser.decode;

import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketType;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EngineIOPacketTypeDecoder implements Decoder<ByteBuf, Packet> {

    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("1. lookup Engine.IO packet type");
        int engineTypeId = context.getData().readByte() & 0xf;
        PacketType enginePacketType = PacketType.valueOfEngineIO(engineTypeId);
        context.geTagret().setType(enginePacketType);
    }

    @Override
    public boolean test(DecoderContext<ByteBuf, Packet> context) {
        return Decoder.super.test(context);
    }
}
