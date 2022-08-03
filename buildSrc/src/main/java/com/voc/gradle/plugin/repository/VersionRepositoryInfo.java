package com.voc.gradle.plugin.repository;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/03 16:09
 */
public interface VersionRepositoryInfo extends RepositoryInfo {

    /**
     * 根据 {@link VersionType} 获取仓库地址
     *
     * @param versionType
     * @return
     */
    String getUrl(VersionType versionType);

    /**
     * 配置不同类型仓库地址
     *
     * @param versionType
     * @param url
     */
    void url(VersionType versionType, String url);

    /**
     * 移除指定配置 URL
     *
     * @param versionType
     */
    void removeUrl(VersionType versionType);
}
