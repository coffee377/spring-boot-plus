package com.voc.boot.socket.io.parser.encode;

import com.voc.boot.socket.io.parser.Binary;
import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketType;
import com.voc.boot.socket.io.parser.Parser;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.voc.boot.socket.io.utils.HasBinary.hasBinary;

@Slf4j
public class Encoder implements Parser.Encoder {
    @Override
    public void encode(Packet packet, Callback callback) {
        if ((packet.getType().equals(PacketType.EVENT) || packet.getType().equals(PacketType.ACK) && hasBinary(packet.getData()))) {
            packet.setType(packet.getType() == PacketType.EVENT ? PacketType.BINARY_EVENT : PacketType.BINARY_ACK);
        }

        log.debug("encoding packet {}", packet);

        if (PacketType.BINARY_EVENT == packet.getType() || PacketType.BINARY_ACK == packet.getType()) {
            encodeAsBinary(packet, callback);
        } else {
            String encoding = encodeAsString(packet);
            callback.call(new String[]{encoding});
        }
    }

    private String encodeAsString(Packet obj) {
        StringBuilder str = new StringBuilder("" + obj.getType());

        if (PacketType.BINARY_EVENT == obj.getType() || PacketType.BINARY_ACK == obj.getType()) {
            str.append(obj.getAttachments());
            str.append("-");
        }

        if (obj.getNamespace() != null && !obj.getNamespace().isEmpty() && !"/".equals(obj.getNamespace())) {
            str.append(obj.getNamespace());
            str.append(",");
        }

        if (obj.getAckId() >= 0) {
            str.append(obj.getAckId());
        }

        if (obj.getData() != null) {
            str.append(obj.getData());
        }

        log.debug("encoded {} as {}", obj, str);
        return str.toString();
    }

    private void encodeAsBinary(Packet obj, Callback callback) {
        Binary.DeconstructedPacket deconstruction = Binary.deconstructPacket(obj);
        String pack = encodeAsString(deconstruction.packet);
        List<Object> buffers = new ArrayList<>(Arrays.asList(deconstruction.buffers));

        buffers.add(0, pack);
        callback.call(buffers.toArray());
    }
}
