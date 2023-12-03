package com.voc.boot.socket.io.parser.decode.message;

import com.corundumstudio.socketio.protocol.PacketEncoder;
import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketType;
import com.voc.boot.socket.io.parser.decode.DecoderContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class AttachmentsLoadedDecoder extends MessageDecoder {
    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("10. process binary attachments loaded");
//        LinkedList<ByteBuf> slices = new LinkedList<ByteBuf>();
//        ByteBuf source = binaryPacket.getDataSource();
//        for (int i = 0; i < binaryPacket.getAttachments().size(); i++) {
//            ByteBuf attachment = binaryPacket.getAttachments().get(i);
//            ByteBuf scanValue = Unpooled.copiedBuffer("{\"_placeholder\":true,\"num\":" + i + "}", CharsetUtil.UTF_8);
//            int pos = PacketEncoder.find(source, scanValue);
//            if (pos == -1) {
//                scanValue = Unpooled.copiedBuffer("{\"num\":" + i + ",\"_placeholder\":true}", CharsetUtil.UTF_8);
//                pos = PacketEncoder.find(source, scanValue);
//                if (pos == -1) {
//                    throw new IllegalStateException("Can't find attachment by index: " + i + " in packet source");
//                }
//            }
//
//            ByteBuf prefixBuf = source.slice(source.readerIndex(), pos - source.readerIndex());
//            slices.add(prefixBuf);
//            slices.add(QUOTES);
//            slices.add(attachment);
//            slices.add(QUOTES);
//
//            source.readerIndex(pos + scanValue.readableBytes());
//        }
//        slices.add(source.slice());
//
//        ByteBuf compositeBuf = Unpooled.wrappedBuffer(slices.toArray(new ByteBuf[0]));
//        parseBody(head, compositeBuf, binaryPacket);
//        head.setLastBinaryPacket(null);
    }

    @Override
    public boolean test(DecoderContext<ByteBuf, Packet> context) {
        return super.test(context) && isBinaryPacket(context) && isAttachmentsLoaded(context);
    }

    private boolean isBinaryPacket(DecoderContext<ByteBuf, Packet> context) {
        PacketType subType = context.geTagret().getSubType();
        return context.geTagret().hasAttachments() && (subType.equals(PacketType.BINARY_EVENT) || subType.equals(PacketType.BINARY_ACK));
    }

    private boolean isAttachmentsLoaded(DecoderContext<ByteBuf, Packet> context) {
        return context.geTagret().hasAttachments() && context.geTagret().isAttachmentsLoaded();
    }
}
