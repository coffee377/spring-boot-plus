package com.voc.security.core.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/22 01:38
 */
@Data
@ConfigurationProperties(prefix = "api.security.root")
public class RootAccountProperties {

    /**
     * 是否启用根用户
     */
    private Boolean enable = true;

    /**
     * 根用户名称
     */
    private String username = "root";

    /**
     * 根用户密码
     */
    private String password = "{noop}123456";
}
