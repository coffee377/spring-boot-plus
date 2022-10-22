package com.voc.security.core.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/09 10:08
 */
@Data
@ConfigurationProperties(prefix = "api.security")
public class SecurityProperties {

    /**
     * 不需要验证权限的地址
     */
    private List<String> ignore;

}
