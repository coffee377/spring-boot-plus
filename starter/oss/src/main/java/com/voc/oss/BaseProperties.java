package com.voc.oss;

import lombok.Data;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/06/19 19:02
 */
@Data
public class BaseProperties {

    /**
     * 是否激活配置
     */
    private boolean active;

    /**
     * 访问端点
     */
    private String endpoint;


    public BaseProperties(boolean active) {
        this.active = active;
    }

    public BaseProperties() {
        this(false);
    }
}
