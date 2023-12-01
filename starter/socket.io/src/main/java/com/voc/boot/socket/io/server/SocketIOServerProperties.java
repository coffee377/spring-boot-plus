package com.voc.boot.socket.io.server;

import com.corundumstudio.socketio.Configuration;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.3
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "socket.io.server")
public class SocketIOServerProperties extends Configuration {

    private List<String> namespaces;

    public SocketIOServerProperties() {
        this.setPort(9092);
    }
}
