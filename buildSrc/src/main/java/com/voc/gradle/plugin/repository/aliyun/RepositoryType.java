package com.voc.gradle.plugin.repository.aliyun;

import lombok.Getter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 15:57
 */
@Getter
public enum RepositoryType {
    RELEASE("release"),
    SNAPSHOT("snapshot");

    private final String type;

    RepositoryType(String type) {
        this.type = type;
    }
}
