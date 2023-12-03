package io.socket.engineio.handler;

import com.corundumstudio.socketio.Transport;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import lombok.Data;
import io.socket.engineio.protocol.EngineIO;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class HandshakeRequest {
    private HttpMethod method;
    private String path;
    private EngineIO.Version engineIOVersion;
    private Transport transport;
    private String sid;
    private String time;


    public HandshakeRequest(HttpMethod method, String path, EngineIO.Version engineIOVersion, Transport transport) {
        this.method = method;
        this.path = path;
        this.engineIOVersion = engineIOVersion;
        this.transport = transport;
    }

    public boolean isFirst() {
        return sid == null || sid.isEmpty();
    }

    public static HandshakeRequest from(HttpRequest request) {
        QueryStringDecoder queryDecoder = new QueryStringDecoder(request.uri());
        Map<String, List<String>> parameters = queryDecoder.parameters();

        EngineIO.Version version = EngineIO.Version.UNKNOWN;
        List<String> versions = parameters.get(EngineIO.Version.PARAMETER_NAME);
        if (versions != null) {
            try {
                version = EngineIO.Version.from(versions.get(0));
            } catch (IllegalArgumentException ignored) {
            }
        }

        Transport transport = Transport.WEBSOCKET;
        List<String> transports = parameters.get(Transport.PARAMETER_NAME);
        if (transports != null) {
            try {
                transport = Transport.from(transports.get(0));
            } catch (IllegalArgumentException ignored) {
            }
        }
        HandshakeRequest handshakeRequest = new HandshakeRequest(request.method(), queryDecoder.path(), version, transport);

        List<String> sid = parameters.getOrDefault("sid", Collections.singletonList(null));
        handshakeRequest.setSid(sid.get(0));

        List<String> time = parameters.getOrDefault("t", Collections.singletonList(null));
        handshakeRequest.setTime(time.get(0));
        return handshakeRequest;
    }
}
