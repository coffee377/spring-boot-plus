package com.voc.boot.gitlab;

import com.voc.boot.gitlab.webhook.WebhookEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.net.InetAddress;
import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/13 16:08
 */
@Slf4j
@Component
public class GitlabCommandLineRunner implements CommandLineRunner {

    private final Environment environment;

    public GitlabCommandLineRunner(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(String... args) throws Exception {
        Bindable<GitlabProperties> bindable = Bindable.of(GitlabProperties.class);
        GitlabProperties gitlabProperties = Binder.get(environment).bind(GitlabProperties.PREFIX, bindable).orElse(new GitlabProperties());
        Boolean enable = Optional.ofNullable(gitlabProperties.getEnable()).orElse(true);
        if (enable) {
            log.info("Gitlab config enable, You can call the section's Gitlab API resource by GitlabEndpoint");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Please fill in this address in your Gitlab Webhook: ");
        ServerProperties serverProperties = Binder.get(environment).bind("server", ServerProperties.class).orElse(new ServerProperties());

        boolean https = !ObjectUtils.isEmpty(serverProperties.getSsl()) && serverProperties.getSsl().isEnabled();
        sb.append("http");
        if (https) {
            sb.append("s");
        }
        sb.append("://");
        InetAddress address = serverProperties.getAddress();
        String ip = "127.0.0.1";
        if (!ObjectUtils.isEmpty(address)) {
            ip = address.getHostAddress();
        }
        sb.append(ip);


        Integer port = serverProperties.getPort();
        if (port == null) {
            sb.append(8080);
        } else {
            sb.append(port);
        }

        String contextPath = serverProperties.getServlet().getContextPath();
        if (contextPath != null) {
            sb.append(contextPath);
        }

        sb.append(WebhookEndpoint.ENDPOINT_URL);
        log.info(sb.toString());
    }

}
