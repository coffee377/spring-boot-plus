package com.voc.boot.socket.io.server.listener;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class DefaultConnectListener implements ConnectListener {

    JsonMapper jsonMapper;

    public DefaultConnectListener(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void onConnect(SocketIOClient client) {
        String EIO = client.getEngineIOVersion().getValue();
        String transport = client.getTransport().getValue();
        UUID sessionId = client.getSessionId();
        HandshakeData handshakeData = client.getHandshakeData();
        if (log.isTraceEnabled()) {
            try {
                String data = jsonMapper.writeValueAsString(handshakeData);
                log.trace("{}", data);
            } catch (JsonProcessingException e) {
                log.error("{}", e.getMessage());
            }
        }

//        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        // 存储SocketIOClient，用于发送消息
//        socketIOClientMap.put(mac, client);
        if (log.isInfoEnabled()) {
            log.info("EIO: {} transport: {} sid: {} 已连接", EIO, transport, sessionId);
        }
    }
}
