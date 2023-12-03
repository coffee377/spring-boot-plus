package io.socket.engineio.protocol;

@Deprecated
public interface PacketType {
    String getType();

    Integer getId();

    String getUsage();
}
