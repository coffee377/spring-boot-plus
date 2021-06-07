package com.voc.system;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/06/07 11:37
 */
@Getter
@Setter
@ConfigurationProperties("api.system")
public class SystemProperties {

    /**
     * 系统模块前缀名称
     */
    private String prefix;

}
