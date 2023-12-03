package com.voc.boot.socket.io.utils;

import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacketUtilsTest {

    @Test
    public void decoder() {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeCharSequence("4212[\"foo\"]", StandardCharsets.UTF_8);
//        byteBuf.writeCharSequence("40/test,{\"token\":\"test\"}", StandardCharsets.UTF_8);
        Packet packet = PacketUtils.decoder(byteBuf);
        assertEquals(PacketType.MESSAGE, packet.getType());
        assertEquals(PacketType.EVENT, packet.getSubType());
//        assertEquals("/test", packetX.getNsp());
//        assertEquals("{\"token\":\"test\"}", packetX.getData());
    }
}
