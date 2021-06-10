package com.voc.system.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.Ssl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/08 16:19
 */
@Configuration
@AutoConfigureAfter(RestTemplateAutoConfiguration.class)
public class TestRestTemplateConfiguration {

    @Bean
    TestRestTemplate restTemplate(ServerProperties serverProperties, RestTemplateBuilder restTemplateBuilder) {
        StringBuilder rootUri = new StringBuilder();
        rootUri.append("http");

        Ssl ssl = serverProperties.getSsl();
        if (ssl != null && ssl.isEnabled()) {
            rootUri.append("s");
        }
        rootUri.append("://");

        InetAddress address = serverProperties.getAddress();
        if (address == null) {
            rootUri.append("localhost");
        } else {
            rootUri.append(address.getHostAddress());
        }
        rootUri.append(":");

        Integer port = serverProperties.getPort();
        if (port == null) {
            rootUri.append(8080);
        } else {
            rootUri.append(port);
        }

        RestTemplateBuilder restBuilder = restTemplateBuilder.rootUri(rootUri.toString());
        return new TestRestTemplate(restBuilder);
    }

}
