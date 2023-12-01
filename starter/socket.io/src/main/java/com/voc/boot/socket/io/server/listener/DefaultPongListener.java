package com.voc.boot.socket.io.server.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.PongListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultPongListener implements PongListener {
    @Override
    public void onPong(SocketIOClient client) {

    }
}
