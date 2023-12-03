package com.voc.boot.socket.io.parser.decode.message;

import com.voc.boot.socket.io.parser.PacketType;
import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinaryAttachmentsDataDecoder extends MessageDecoder {
    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("9. look up binary attachments data");
        ByteBuf frame = context.getData();
        Packet binaryPacket = context.geTagret();
        if (frame.getByte(0) == 'b') {
            // todo base64 解码
            binaryPacket.addAttachment(Unpooled.copiedBuffer(frame));
        } else {
            binaryPacket.addAttachment(Unpooled.copiedBuffer(frame));
        }
        frame.readerIndex(frame.readerIndex() + frame.readableBytes());
    }

    @Override
    public boolean test(DecoderContext<ByteBuf, Packet> context) {
        return super.test(context) && isBinaryPacket(context) && hasAttachments(context);
    }

    private boolean isBinaryPacket(DecoderContext<ByteBuf, Packet> context) {
        PacketType subType = context.geTagret().getSubType();
        return context.geTagret().hasAttachments() && (subType.equals(PacketType.BINARY_EVENT) || subType.equals(PacketType.BINARY_ACK));
    }

    private boolean hasAttachments(DecoderContext<ByteBuf, Packet> context) {
        return context.geTagret().hasAttachments();
    }
}
