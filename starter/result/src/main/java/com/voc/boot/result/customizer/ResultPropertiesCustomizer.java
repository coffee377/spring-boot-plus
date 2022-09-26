package com.voc.boot.result.customizer;

import com.voc.boot.result.properties.ResultProperties;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/09/26 22:15
 */
@FunctionalInterface
public interface ResultPropertiesCustomizer {

    /**
     * 自定义 ResultProperties
     *
     * @param resultProperties Result 配置
     */
    void customize(ResultProperties resultProperties);

}
