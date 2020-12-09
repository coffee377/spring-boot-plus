package com.voc.gradle.plugin.embedded;

import lombok.Getter;
import org.gradle.api.JavaVersion;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/08 16:06
 */
@Getter
public enum ExtraProps {
    /**
     *
     */
    KOTLIN_VERSION("kotlin.version", "1.3.40"),
    LOMBOK_VERSION("lombok.version", "1.18.4"),
    ENABLE_KOTLIN("kotlin.enable", false),
    KOTLIN_INCREMENTAL("kotlin.incremental", true),
    KOTLIN_JVM_VERSION("kotlin.jvm.version", JavaVersion.current().toString()),
    ENABLE_GOOGLE_AUTO_SERVICE_VERSION("google.auto.service.enable", false),

    ;

    private final String key;

    private final Object value;

    private Dependency dependency;

    ExtraProps(String key, Object value) {
        this.key = key;
        this.value = value;
    }
//
//    ExtraProps(String key, Dependency dependency) {
//        this.key = key;
//        this.value = dependency.getVersion();
//    }
}
