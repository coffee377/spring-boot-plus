package com.voc.boot.socket.io.parser;

import com.voc.boot.socket.io.parser.decode.Decoder;
import com.voc.boot.socket.io.parser.decode.DecoderContext;
import com.voc.boot.socket.io.parser.decode.EngineIOPacketTypeDecoder;
import com.voc.boot.socket.io.parser.decode.message.*;
import com.voc.boot.socket.io.parser.decode.ping.PingDataDecoder;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
final public class PacketDecoder implements Decoder<ByteBuf, Packet> {


    /*package*/
    IOParser.BinaryReconstructor reconstructor;


    private Consumer<Packet> consumer;
    DecoderContext<ByteBuf, Packet> context;

    Packet packet = new Packet();

    @Override
    public List<Decoder<ByteBuf, Packet>> getDecoders() {
        List<Decoder<ByteBuf, Packet>> decoders = new ArrayList<>();
        decoders.add(new EngineIOPacketTypeDecoder());

        decoders.add(new PingDataDecoder());

        decoders.add(new SocketIOPacketTypeDecoder());

        decoders.add(new AttachmentsDecoder());
        decoders.add(new NamespaceDecoder());
        decoders.add(new AcknowledgmentDecoder());

        decoders.add(new JSONObjectDataDecoder());

        decoders.add(new JSONArrayDataDecoder());

        decoders.add(new BinaryAttachmentsDataDecoder());
        decoders.add(new AttachmentsLoadedDecoder());
        return decoders;
    }

    @Override
    public void add(ByteBuf data) {
        Packet packet = new Packet();
        packet.setSource(data);
        this.add(data, packet);
    }

    void add(ByteBuf data, Packet target) {
        context = new DecoderContext<>(data, target);
        decode(context);
    }

    @Override
    public void destroy() {
        if (this.reconstructor != null) {
            this.reconstructor.finishReconstruction();
        }
        this.context = null;
    }

    @Override
    public void onDecoded(Consumer<Packet> consumer) {
        if (consumer != null) {
            consumer.accept(context.geTagret());
        }
    }

    public void add(String obj) {
        Packet packet = decodeString(obj);
        if (packet.getType().equals(PacketType.BINARY_EVENT) || packet.getType().equals(PacketType.BINARY_ACK)) {
            this.reconstructor = new IOParser.BinaryReconstructor(packet);

            if (this.reconstructor.reconPack.getAttachments() == 0) {
                if (this.consumer != null) {
                    this.consumer.accept(packet);
                }
            }
        } else {
            if (this.consumer != null) {
                this.consumer.accept(packet);
            }
        }
    }

    public void add(byte[] obj) {
        if (this.reconstructor == null) {
            throw new RuntimeException("got binary data when not reconstructing a packet");
        } else {
            Packet packet = this.reconstructor.takeBinaryData(obj);
            if (packet != null) {
                this.reconstructor = null;
                if (this.consumer != null) {
                    this.consumer.accept(packet);
                }
            }
        }
    }

    private Packet decodeString(String str) {
        int i = 0;
        int length = str.length();

        // look up type
        int enginePacketTypeValue = Character.getNumericValue(str.charAt(0));
        PacketType packetType = PacketType.valueOfEngineIO(enginePacketTypeValue);
        if (packetType.equals(PacketType.UNKNOWN)) {
            throw new DecodingException("unknown packet type " + enginePacketTypeValue);
        }

        Packet p = new Packet(packetType);
        // look up attachments if type binary
        if (p.getType().equals(PacketType.BINARY_EVENT) || p.getType().equals(PacketType.BINARY_ACK)) {
            if (!str.contains("-") || length <= i + 1) {
                throw new DecodingException("illegal attachments");
            }
            StringBuilder attachments = new StringBuilder();
            while (str.charAt(++i) != '-') {
                attachments.append(str.charAt(i));
            }
            p.setAttachments(Integer.parseInt(attachments.toString()));
        }

        // look up namespace (if any)
        if (length > i + 1 && '/' == str.charAt(i + 1)) {
            StringBuilder nsp = new StringBuilder();
            while (true) {
                ++i;
                char c = str.charAt(i);
                if (',' == c) break;
                nsp.append(c);
                if (i + 1 == length) break;
            }
            p.setNamespace(nsp.toString());
        } else {
            p.setNamespace("/");
        }

        // look up id
        if (length > i + 1) {
            char next = str.charAt(i + 1);
            if (Character.getNumericValue(next) > -1) {
                StringBuilder id = new StringBuilder();
                while (true) {
                    ++i;
                    char c = str.charAt(i);
                    if (Character.getNumericValue(c) < 0) {
                        --i;
                        break;
                    }
                    id.append(c);
                    if (i + 1 == length) break;
                }
                try {
                    p.setAckId(Long.parseLong(id.toString()));
                } catch (NumberFormatException e) {
                    throw new DecodingException("invalid payload");
                }
            }
        }

        // look up json data
        if (length > i + 1) {
            try {
                str.charAt(++i);
                p.setData(new JSONTokener(str.substring(i)).nextValue());
            } catch (JSONException e) {
                if (log.isWarnEnabled()) {
                    log.warn("An error occurred while retrieving data from JSONToken", e);
                }
                throw new DecodingException("invalid payload");
            }
            if (!isPayloadValid(p)) {
                throw new DecodingException("invalid payload");
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("decoded {} as {}", str, p);
        }
        return p;
    }

    private boolean isPayloadValid(Packet packet) {
        if (packet.getSubType() != null) {
            Object payload = packet.getData();
            switch (packet.getSubType()) {
                case CONNECT:
                    return payload instanceof JSONObject;
                case CONNECT_ERROR:
                    return payload instanceof JSONObject || payload instanceof String;
                case DISCONNECT:
                    return payload == null;
                case EVENT:
                case BINARY_EVENT:
                    return payload instanceof JSONArray
                            && !((JSONArray) payload).isEmpty()
                            && !((JSONArray) payload).isNull(0);
                case ACK:
                case BINARY_ACK:
                    return payload instanceof JSONArray;
                default:
                    return false;
            }
        }
        switch (packet.getType()) {
            case OPEN:
            case CLOSE:
            case PING:
            case MESSAGE:
            case UPGRADE:
            case NOOP:
            default:
                return packet.getData() instanceof JSONObject;
        }
    }


}
