package com.voc.restful.core.autoconfigure.json;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/05/06 21:37
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "api.json.wrapper")
public class JsonWrapper {

    /**
     * <p>是否启用响应结果自动包装，默认 true</p>
     * <p>局部控制请使用 {@link com.voc.restful.core.response.ResponseResult} 注解</p>
     */
    private Boolean enable = true;

    /**
     * 全局包装需要忽略的类名称(完全限定名)
     */
    List<String> ignoredClass = new ArrayList<>();

    /**
     * 是否 springdoc 项目，若是则自动添加忽略的类
     */
    Boolean springdoc = false;

    public void setSpringdoc(Boolean springdoc) {
        this.springdoc = springdoc;
        List<String> springDoc = Arrays.asList(
                "org.springdoc.webmvc.api.OpenApiWebMvcResource",
                "org.springdoc.webmvc.api.OpenApiActuatorResource"
        );
        if (springdoc) {
            this.ignoredClass.addAll(springDoc);
        } else {
            this.ignoredClass.removeAll(springDoc);
        }
    }
}
