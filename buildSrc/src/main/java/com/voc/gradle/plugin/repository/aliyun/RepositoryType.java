package com.voc.gradle.plugin.repository.aliyun;

import lombok.Getter;
import org.gradle.api.Project;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 15:57
 */
@Getter
public enum RepositoryType {
    /**
     * 快照版本
     */
    SNAPSHOT("snapshot"),
    /**
     * 里程碑版本
     */
    MILESTONE("milestone"),
    /**
     * 正式版
     */
    RELEASE("release");

    private final String type;

    RepositoryType(String type) {
        this.type = type;
    }

    public static RepositoryType forProject(@NonNull Project project) {
        String version = project.getVersion().toString();
        int modifierIndex = version.lastIndexOf('-');
        if (modifierIndex == -1) {
            return RepositoryType.RELEASE;
        }
        String type = version.substring(modifierIndex + 1);
        if (type.startsWith("M") || type.startsWith("RC")) {
            return RepositoryType.MILESTONE;
        }
        return RepositoryType.SNAPSHOT;
    }

}
