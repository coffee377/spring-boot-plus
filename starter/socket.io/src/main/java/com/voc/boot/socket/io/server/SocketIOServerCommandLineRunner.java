package com.voc.boot.socket.io.server;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import javax.annotation.Resource;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.3
 */
@Slf4j
@ConditionalOnClass(SocketIOServer.class)
public class SocketIOServerCommandLineRunner implements CommandLineRunner, DisposableBean {

    @Resource
    private SocketIOServer socketIOServer;

    @Override
    public void run(String... args) throws Exception {
        socketIOServer.start();
        log.info("socket.io 服务启动成功！");
    }

    @Override
    public void destroy() throws Exception {
        socketIOServer.stop();
    }
}
