package com.voc.boot.socket.io.parser;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Packet {
    private ByteBuf source;
    /**
     * Engine.IO Packet Type
     */
    private PacketType type;
    /**
     * Socket.IO Packet Type
     */
    private PacketType subType;
    private int attachments;
    private String namespace;
    private Long ackId;
    private Object data;
    private List<ByteBuf> attachmentsBuffers = Collections.emptyList();
    public Packet() {
    }

    public Packet(PacketType type) {
        this(type, null);
    }

    public Packet(PacketType type, Object data) {
        this.type = type;
        this.data = data;
    }

    public boolean hasAttachments() {
        return attachments != 0;
    }
    public boolean isAttachmentsLoaded() {
        return this.attachmentsBuffers.size() == this.attachments;
    }

    public void setAttachments(int attachments) {
        this.initAttachments(attachments);
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
