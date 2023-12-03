package com.voc.boot.socket.io.parser.decode.message;

import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketType;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketIOPacketTypeDecoder extends MessageDecoder {

    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("3. lookup Socket.IO packet type");
        ByteBuf buf = context.getData();
        Packet packet = context.geTagret();
        int socketTypeId = buf.readByte() & 0xf;
        PacketType socketPacketType = PacketType.valueOfSocketIO(socketTypeId);
        packet.setSubType(socketPacketType);
    }
}
