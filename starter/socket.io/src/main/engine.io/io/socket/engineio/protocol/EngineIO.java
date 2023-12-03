package io.socket.engineio.protocol;

import io.socket.IPacketType;
import lombok.Data;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class EngineIO {
    public enum Version {
        UNKNOWN(0),
        /**
         * @link <a href="https://github.com/socketio/engine.io-protocol/tree/v2">Engine.IO version 2</a>
         */
        V2(2),
        /**
         * @link <a href="https://github.com/socketio/engine.io-protocol/tree/v3">Engine.IO version 3</a>
         */
        V3(3),
        /**
         * current version
         *
         * @link <a href="https://github.com/socketio/engine.io-protocol/tree/main">Engine.IO version 4</a>
         */
        V4(4);

        private final int value;
        public static final String PARAMETER_NAME = "EIO";

        Version(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Version from(String value) {
            return Arrays.stream(values())
                    .filter(version -> value.equals(Integer.toString(version.value)))
                    .findFirst().orElse(UNKNOWN);
        }

    }

    public enum PacketType implements io.socket.engineio.protocol.PacketType, IPacketType {
        OPEN("open", 0, "Used during the handshake"),
        CLOSE("close", 1, "Used to indicate that a transport can be closed"),
        PING("ping", 2, "Used in the heartbeat mechanism"),
        PONG("pong", 3, "Used in the heartbeat mechanism"),
        MESSAGE("message", 4, "Used to send a payload to the other side"),
        UPGRADE("upgrade", 5, "Used during the upgrade process"),
        NOOP("noop", 6, "Used during the upgrade process"),

        ;

        private final String type;
        private final int id;
        private final String usage;

        PacketType(String type, int id, String usage) {
            this.type = type;
            this.id = id;
            this.usage = usage;
        }


        @Override
        public String getType() {
            return type;
        }

        @Override
        public Integer getId() {
            return id;
        }

        @Override
        public String getUsage() {
            return usage;
        }

        @Override
        public int getValue() {
            return this.id;
        }

        public static PacketType from(int value) {
            return Arrays.stream(values())
                    .filter(type -> type.getValue() == value)
                    .findFirst().orElseThrow(() -> new IllegalArgumentException("Can't parse " + value));
        }


    }

    public static class Packet<T> implements IPacketType {

        private final PacketType type;
        private T data;

        public Packet(PacketType type) {
            this(type, null);
        }

        public Packet(PacketType type, T data) {
            this.type = type;
            this.data = data;
        }

        public PacketType getType() {
            return type;
        }

        public void setData(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        @Override
        public int getValue() {
            return type.getValue();
        }
    }
}
