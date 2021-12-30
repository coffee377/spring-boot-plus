package com.voc.gradle.plugin.repository.aliyun;

import com.voc.gradle.plugin.repository.RepositoryInfo;
import org.gradle.api.Action;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 15:29
 */
public interface AliYunRepositoryInfo extends RepositoryInfo {

    /**
     * 云效仓库前缀
     *
     * @return String
     * @since 0.0.1
     */
    String getPrefix();

    /**
     * 设置云效仓库前缀
     *
     * @param prefix String
     * @since 0.0.2
     */
    default void prefix(String prefix) {
    }

    /**
     * 云效仓库 ID
     *
     * @return String
     * @since 0.0.1
     */
    String getId();

    /**
     * 设置云效仓库 ID
     *
     * @param id String
     * @since 0.0.2
     */
    default void id(String id) {
    }

    /**
     * 正式库配置
     *
     * @param action Action<Identification>
     * @since 0.0.1
     */
    void release(Action<Identification> action);

    /**
     * 快照库配置
     *
     * @param action Action<Identification>
     * @since 0.0.1
     */
    void snapshot(Action<Identification> action);

    /**
     * 正式版地址
     *
     * @return String
     * @see #getValidUrl()
     * @since 0.0.1
     * @deprecated
     */
    default String getReleaseUrl() {
        return null;
    }

    /**
     * 快照版地址
     *
     * @return String
     * @see #getValidUrl()
     * @since 0.0.1
     * @deprecated
     */
    default String getSnapshotUrl() {
        return null;
    }

    /**
     * 获取依赖有效仓库地址
     *
     * @return List<String>
     * @since 0.0.2
     */
    List<String> getValidUrl();

}
