package com.voc.boot.socket.io.utils;

public enum PacketSeparator {
    ATTACHMENTS('-'),
    NAMESPACE(','),
    ACKNOWLEDGMENT ('['),
    DATA_JSON_ARRAY(']'),
    DATA_JSON_OBJECT('}'),
    ;
    private final byte value;

    PacketSeparator(char value) {
        this.value = (byte)value;
    }

    public byte getValue() {
        return value;
    }
}
