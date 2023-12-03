package io.socket;

public interface IPacketType {
    int getValue();

    default byte getByte() {
        return (byte) (getValue() ^ 0x30);
    }
}
