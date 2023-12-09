package io.socket.engineio.parser;

import io.netty.buffer.ByteBuf;
import io.socket.engineio.protocol.EngineIO;

import java.util.Base64;
import java.util.List;

public class EngineIOV4Decoder implements Decoder {
    @Override
    public EngineIO.Packet<?> decode(String data) {
        char type = data.charAt(0);
        String content = data.substring(1);
        if (type == 'b') {
            EngineIO.Packet<byte[]> packet = new EngineIO.Packet<>(EngineIO.PacketType.MESSAGE);
            packet.setData(Base64.getDecoder().decode(content));
            return packet;
        } else {
            EngineIO.PacketType packetType = EngineIO.PacketType.from(type & 0xf);
            EngineIO.Packet<String> packet = new EngineIO.Packet<>(packetType);
            packet.setData(content);
            return packet;
        }
    }

    @Override
    public EngineIO.Packet<?> decode(byte[] data) {
        return new EngineIO.Packet<>(EngineIO.PacketType.MESSAGE, data);
    }

    @Override
    public EngineIO.Packet<?> decode(ByteBuf buffer) {
        return null;
    }

    private boolean isStringPacket() {
        return false;
    }

    @Override
    public List<EngineIO.Packet<Object>> decode(String payload, String separator) {
        return null;
    }
}
