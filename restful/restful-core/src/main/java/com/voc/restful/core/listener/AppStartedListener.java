package com.voc.restful.core.listener;

import com.voc.restful.core.props.AppInfoProperties;
import com.voc.restful.core.util.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.net.SocketException;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/16 16:46
 */
@Slf4j
public class AppStartedListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        ConfigurableApplicationContext applicationContext = applicationStartedEvent.getApplicationContext();
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        AppInfoProperties appInfo = applicationContext.getBean(AppInfoProperties.class);
        String appName = appInfo.getName();
        String appVersion = appInfo.getVersion();
        try {
            List<String> ips = IPUtils.getIPList();
            String port = env.getProperty("server.port");
            port = StringUtils.hasLength(port) ? port : "8080";
            String path = env.getProperty("server.servlet.context-path");
            path = StringUtils.hasLength(path) ? path : "";

            StringBuilder builder = new StringBuilder();
            builder.append("\n--------------------------------------------------------------------------\n");
            if (StringUtils.hasLength(appName)) {
                builder.append("Application ").append(appName);
            }
            if (StringUtils.hasLength(appVersion)) {
                builder.append("(").append(appVersion).append(")");
            }
            builder.append(" is running! Access URLs:\n");

            for (int i = 0; i < ips.size(); i++) {
                builder.append("访问地址").append(i + 1).append(":\t")
                        .append("http://").append(ips.get(i)).append(":").append(port).append(path).append("/\n");
            }
            builder.append("--------------------------------------------------------------------------");
            if (log.isInfoEnabled()) {
                log.info("{}", builder);
            }
        } catch (SocketException e) {
            if (log.isErrorEnabled()) {
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
