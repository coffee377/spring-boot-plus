package com.voc.boot.socket.io.utils;

import com.voc.boot.socket.io.parser.Packet;
import com.voc.boot.socket.io.parser.PacketDecoder;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class PacketUtils {

    public static Packet decoder(ByteBuf buf) {
        PacketDecoder packetDecoder = new PacketDecoder();
        packetDecoder.add(buf);
        AtomicReference<Packet> packetReference = new AtomicReference<>();
        packetDecoder.onDecoded(packet -> {
            packetReference.set(packet);
            if (log.isDebugEnabled()) {
                log.debug("{}", packet);
            }
        });
        return packetReference.get();
    }

    public static boolean hasBinary(Object data) {
        if (data == null) return false;

        if (data instanceof byte[]) {
            return true;
        }

        if (data instanceof JSONArray) {
            JSONArray _obj = (JSONArray) data;
            int length = _obj.length();
            for (int i = 0; i < length; i++) {
                Object v;
                try {
                    v = _obj.isNull(i) ? null : _obj.get(i);
                } catch (JSONException e) {
                    if (log.isWarnEnabled()) {
                        log.warn("An error occurred while retrieving data from JSONArray", e);
                    }
                    return false;
                }
                if (hasBinary(v)) {
                    return true;
                }
            }
        } else if (data instanceof JSONObject) {
            JSONObject _obj = (JSONObject) data;
            Iterator<String> keys = _obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object v;
                try {
                    v = _obj.get(key);
                } catch (JSONException e) {
                    if (log.isWarnEnabled()) {
                        log.warn("An error occurred while retrieving data from JSONObject", e);
                    }
                    return false;
                }
                if (hasBinary(v)) {
                    return true;
                }
            }
        }

        return false;
    }

}
