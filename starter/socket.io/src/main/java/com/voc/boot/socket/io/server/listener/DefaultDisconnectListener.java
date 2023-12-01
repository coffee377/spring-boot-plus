package com.voc.boot.socket.io.server.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultDisconnectListener implements DisconnectListener {
    @Override
    public void onDisconnect(SocketIOClient client) {
        log.info("客户端: {} 断开了连接", client.getSessionId());
    }
}
