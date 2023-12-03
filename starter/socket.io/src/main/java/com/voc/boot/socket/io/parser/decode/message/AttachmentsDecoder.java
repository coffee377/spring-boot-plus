package com.voc.boot.socket.io.parser.decode.message;

import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.DecodingException;
import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketType;
import com.voc.boot.socket.io.utils.PacketSeparator;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttachmentsDecoder extends MessageDecoder {
    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("4. look up attachments if type binary");
        ByteBuf buf = context.getData();
        Packet packet = context.geTagret();
        if (packet.getSubType().equals(PacketType.BINARY_EVENT) || packet.getSubType().equals(PacketType.BINARY_ACK)) {
            int attachmentsEndIndex = buf.bytesBefore(PacketSeparator.ATTACHMENTS.getValue());
            if (attachmentsEndIndex == -1) {
                throw new DecodingException("illegal attachments");
            }
            if (attachmentsEndIndex > 0) {
                byte[] bytes = new byte[attachmentsEndIndex];
                buf.readBytes(bytes);
                int count = Integer.parseInt(new String(bytes, CharsetUtil.UTF_8));
                packet.setAttachments(count);
                buf.readByte();
            }
        }
    }
}
