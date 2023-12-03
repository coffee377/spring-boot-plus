package io.socket.socketio.protocol;

import io.netty.buffer.ByteBuf;
import io.socket.IPacketType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SocketIO {
    public enum PacketType implements IPacketType {
        UNKNOWN(-1, "Unknown packet type"),

        /* Socket.IO packet types */
        CONNECT(0, "Used during the connection to a namespace"),
        DISCONNECT(1, "Used when disconnecting from a namespace"),
        EVENT(2, "Used to send data to the other side"),
        ACK(3, "Used to acknowledge an event"),
        CONNECT_ERROR(4, "Used during the connection to a namespace"),
        BINARY_EVENT(5, "Used to send binary data to the other side"),
        BINARY_ACK(6, "Used to acknowledge an event (the response includes binary data)");

        private final int value;

        private final String description;

        PacketType(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        public static PacketType from(int value) {
            return Arrays.stream(values())
                    .filter(type -> type.getValue() == value)
                    .findFirst().orElse(PacketType.UNKNOWN);
        }

    }

    public static class Packet implements IPacketType {
        private final ByteBuf source;
        /**
         * Socket.IO Packet Type
         */
        private PacketType type;
        private int attachments;
        private String namespace;
        private Long ackId;
        private Object data;
        private List<ByteBuf> attachmentsBuffers = Collections.emptyList();


        public Packet(ByteBuf source) {
            this(source, null, null);
        }

        public Packet(PacketType type) {
            this(null, type, null);
        }

        public Packet(ByteBuf source, PacketType type, Object data) {
            this.source = source;
            this.type = type;
            this.data = data;
        }

        public ByteBuf getSource() {
            return source;
        }

        public PacketType getType() {
            return type;
        }

        public void setType(PacketType type) {
            this.type = type;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public Long getAckId() {
            return ackId;
        }

        public void setAckId(Long ackId) {
            this.ackId = ackId;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public int getValue() {
            return type.getValue();
        }


        public boolean hasAttachments() {
            return attachments != 0;
        }

        public boolean isAttachmentsLoaded() {
            return this.attachmentsBuffers.size() == this.attachments;
        }

        public void initAttachments(int attachmentsCount) {
            this.attachments = attachmentsCount;
            this.attachmentsBuffers = new ArrayList<>(attachmentsCount);
        }

        public void addAttachment(ByteBuf attachment) {
            if (this.attachmentsBuffers.size() < attachments) {
                this.attachmentsBuffers.add(attachment);
            }
        }
    }
}
