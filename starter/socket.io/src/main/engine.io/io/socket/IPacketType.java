package io.socket;

public interface IPacketType {
    @Deprecated
    default int getValue() {
        return getTypeValue();
    }

    @Deprecated
    default byte getByte() {
        return getTypeByte();
    }

    int getTypeValue();

    default byte getTypeByte() {
        return (byte) (getValue() ^ 0x30);
    }

    default char getTypeChar() {
        return (char) getTypeByte();
    }

    default String getTypeSting() {
        return String.valueOf(getTypeChar());
    }
}
