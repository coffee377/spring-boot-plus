package io.socket.engineio.protocol;

@Deprecated
public class Packet<T> {

    PacketType type;
    T data;

    public Packet(PacketType type) {
        this(type, null);
    }

    public Packet(PacketType type, T data) {
        this.type = type;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public PacketType getType() {
        return type;
    }


}
