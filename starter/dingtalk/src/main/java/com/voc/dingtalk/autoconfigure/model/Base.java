package com.voc.dingtalk.autoconfigure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/27 21:31
 */
@Data
@EqualsAndHashCode
public class Base {
    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用的唯一标识
     */
    private String agentId;

    /**
     * 应用的 Key
     */
    private String appKey;

    /**
     * 应用的密钥
     */
    private String appSecret;
}
