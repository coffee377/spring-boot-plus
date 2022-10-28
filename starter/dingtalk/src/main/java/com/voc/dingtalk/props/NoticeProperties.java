package com.voc.dingtalk.props;

import com.voc.dingtalk.autoconfigure.model.Robot;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/24 10:57
 */
@Data
@ConfigurationProperties("dingtalk.notice")
public class NoticeProperties implements InitializingBean {
    /**
     * 是否启用消息通知
     */
    private boolean enable;

    /**
     * 机器人配置
     */
    private Map<String, Robot> robots = new HashMap<>(2);


    @Override
    public void afterPropertiesSet() throws Exception {
        getRobots().values().forEach(this::validateRobot);
    }

    private void validateRobot(Robot app) {
        if (!StringUtils.hasText(app.getAppKey())) {
            throw new IllegalStateException("appKey must not be empty.");
        }
        if (!StringUtils.hasText(app.getAppSecret())) {
            throw new IllegalStateException("appSecret must not be empty.");
        }
    }

}
