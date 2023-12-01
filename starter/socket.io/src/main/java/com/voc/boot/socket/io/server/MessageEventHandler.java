package com.voc.boot.socket.io.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.3
 */
@Slf4j
public class MessageEventHandler {
    @Resource
    private SocketIOServer socketIoServer;

    public static ConcurrentMap<String, SocketIOClient> socketIOClientMap = new ConcurrentHashMap<>();

    /**
     * 客户端连接的时候触发
     *
     * @param client SocketIOClient
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {

    }

    /**
     * 客户端关闭连接时触发
     *
     * @param client SocketIOClient
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
    }

    /**
     * 客户端事件
     *
     * @param client  　客户端信息
     * @param request 请求信息
     * @param data    　客户端发送数据
     */
    @OnEvent(value = "message-event")
    public void onEvent(SocketIOClient client, AckRequest request, Object data) {
        log.info("发来消息：" + data);
        // 回发消息
        client.sendEvent("message-event", "我是服务器都安发送的信息");
        // 广播消息
        sendBroadcast();
    }

    /**
     * 广播消息
     */
    public void sendBroadcast() {
        for (SocketIOClient client : socketIOClientMap.values()) {
            if (client.isChannelOpen()) {
                client.sendEvent("Broadcast", "当前时间", System.currentTimeMillis());
            }
        }

    }
}
