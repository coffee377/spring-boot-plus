package com.voc.boot.socket.io.server.listener;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
public class DefaultConnectListener implements ConnectListener {

//    @Resource
//    JsonMapper jsonMapper;

    @Override
    public void onConnect(SocketIOClient client) {
        String EIO = client.getEngineIOVersion().getValue();
        String transport = client.getTransport().getValue();
        UUID sessionId = client.getSessionId();
        HandshakeData handshakeData = client.getHandshakeData();
        HttpHeaders httpHeaders = handshakeData.getHttpHeaders();

//        try {
//            jsonMapper.writeValueAsString(handshakeData);
//        } catch (JsonProcessingException e) {
//            log.error("{}",e.getMessage());
//        }
//        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        // 存储SocketIOClient，用于发送消息
//        socketIOClientMap.put(mac, client);
        log.info("EIO: {} transport: {} sid: {} 已连接", EIO, transport, sessionId);
    }
}
