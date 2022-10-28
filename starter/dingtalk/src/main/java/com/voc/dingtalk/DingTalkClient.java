package com.voc.dingtalk;

import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.voc.dingtalk.exception.DingTalkApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/28 14:30
 */
@Configuration
public class DingTalkClient {

    @Bean
    Config config() {
        Config config = new Config();
        config.protocol = "https";
        return config;
    }

    @Bean
    Client oauth2Client(Config config) {
        try {
            return new Client(config);
        } catch (Exception e) {
            if (e instanceof TeaException) {
                TeaException teaException = (TeaException) e;
                throw new DingTalkApiException(teaException.code, teaException.message);
            } else {
                throw new DingTalkApiException(e.getMessage());
            }
        }
    }
}
