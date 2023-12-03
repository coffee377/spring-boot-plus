package com.voc.boot.socket.io.parser.decode.message;

import com.corundumstudio.socketio.namespace.Namespace;
import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.utils.PacketSeparator;
import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NamespaceDecoder extends MessageDecoder {
    @Override
    public void decode(DecoderContext<ByteBuf, Packet> context) {
        log.info("5. look up namespace (if any)");
        ByteBuf buf = context.getData();
        Packet packet = context.geTagret();
        int namespaceEndIndex = buf.bytesBefore(PacketSeparator.NAMESPACE.getValue());
        if (namespaceEndIndex > 0) {
            byte[] bytes = new byte[namespaceEndIndex];
            buf.readBytes(bytes);
            packet.setNamespace(new String(bytes, CharsetUtil.UTF_8));
            buf.readByte();
        } else {
            packet.setNamespace(Namespace.DEFAULT_NAME);
        }
    }
}
