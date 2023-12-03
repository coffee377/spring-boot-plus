package com.voc.boot.socket.io.parser.decode.message;

import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketType;
import com.voc.boot.socket.io.utils.PacketSeparator;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcknowledgmentDecoder extends MessageDecoder {
    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("6. look up acknowledgment id");
        ByteBuf buf = context.getData();
        Packet packet = context.geTagret();
        if (packet.getSubType().equals(PacketType.EVENT)) {
            int acknowledgmentEndIndex = buf.bytesBefore(PacketSeparator.ACKNOWLEDGMENT.getValue());
            if (acknowledgmentEndIndex > 0) {
                byte[] bytes = new byte[acknowledgmentEndIndex];
                buf.readBytes(bytes);
                String s = new String(bytes, CharsetUtil.UTF_8);
                packet.setAckId(Long.parseLong(s));
            }
        }
    }
}
