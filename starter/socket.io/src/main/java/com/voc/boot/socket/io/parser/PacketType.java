package com.voc.boot.socket.io.parser;

import java.util.Arrays;

/**
 * <a href="https://socket.io/zh-CN/docs/v4/engine-io-protocol/">engine-io-protocol</a>
 * <a href="https://socket.io/zh-CN/docs/v4/socket-io-protocol/">socket-io-protocol</a>
 */
public enum PacketType {
    UNKNOWN(-1, "Unknown packet type"),

    /* Engine.IO packet types */
    OPEN(0, "Used during the handshake"),
    CLOSE(1, "Used to indicate that a transport can be closed"),
    PING(2, "Used in the heartbeat mechanism"),
    PONG(3, "Used in the heartbeat mechanism"),
    MESSAGE(4, "Used to send a payload to the other side"),
    UPGRADE(5, "Used during the upgrade process"),
    NOOP(6, "Used during the upgrade process"),

    /* Socket.IO packet types */
    CONNECT(0, "Used during the connection to a namespace", true),
    DISCONNECT(1, "Used when disconnecting from a namespace", true),
    EVENT(2, "Used to send data to the other side", true),
    ACK(3, "Used to acknowledge an event", true),
    CONNECT_ERROR(4, "Used during the connection to a namespace", true),
    BINARY_EVENT(5, "Used to send binary data to the other side", true),
    BINARY_ACK(6, "Used to acknowledge an event (the response includes binary data)", true);

    private final ProtocolType protocol;
    private final int value;

    private final String description;

    PacketType(int value, String description) {
        this(value, description, false);
    }

    PacketType(int value, String description, boolean inner) {
        if (inner) {
            this.protocol = ProtocolType.SOCKET_IO;
        } else {
            this.protocol = ProtocolType.ENGINE_IO;
        }
        this.value = value;
        this.description = description;
    }

    public ProtocolType getType() {
        return protocol;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static PacketType valueOfEngineIO(int value) {
        return Arrays.stream(values())
                .filter(packetType -> packetType.protocol.equals(ProtocolType.ENGINE_IO) && packetType.value == value)
                .findFirst().orElse(PacketType.UNKNOWN);
    }

    public static PacketType valueOfSocketIO(int value) {
        return Arrays.stream(values())
                .filter(packetType -> packetType.protocol.equals(ProtocolType.SOCKET_IO) && packetType.value == value)
                .findFirst().orElse(PacketType.UNKNOWN);
    }

}
