package com.voc.boot.socket.io.server;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.voc.boot.socket.io.server.listener.DefaultConnectListener;
import com.voc.boot.socket.io.server.listener.DefaultDisconnectListener;
import com.voc.boot.socket.io.server.listener.DefaultPongListener;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Optional;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.3
 */
@ConditionalOnClass(SocketIOServer.class)
@EnableConfigurationProperties(SocketIOServerProperties.class)
@Import({SocketIOServerCommandLineRunner.class, MessageEventHandler.class})
public class SocketIOServerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    JsonMapper jsonMapper(){
        return JsonMapper.builder().build();
    }

    @Bean
    @ConditionalOnMissingBean
    SocketIOServer socketIOServer(ObjectProvider<SocketIOServerProperties> configProvider) {
        SocketIOServerProperties config = configProvider.getIfAvailable(SocketIOServerProperties::new);
        SocketIOServer server = new SocketIOServer(config);
//        server.addPongListener(new DefaultPongListener());
        server.addConnectListener(new DefaultConnectListener());
        server.addDisconnectListener(new DefaultDisconnectListener());
        Optional.ofNullable(config.getNamespaces()).ifPresent(nss -> nss.forEach(server::addNamespace));
        return server;
    }


    /**
     * 扫描 netty-socketio 的注解，比如 @OnConnect、@OnDisconnect、@OnEvent
     *
     * @param socketIOServer SocketIOServer
     * @return SpringAnnotationScanner
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketIOServer) {
        return new SpringAnnotationScanner(socketIOServer);
    }

}
