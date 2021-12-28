package com.voc.gradle.plugin.repository.aliyun;

import com.voc.gradle.plugin.repository.RepositoryInfo;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 15:29
 */
public interface AliYunRepositoryInfo extends RepositoryInfo {

    /**
     * 仓库地址
     *
     * @param repositoryType RepositoryType
     * @return String
     */
    String getUrl(RepositoryType repositoryType);


//    void release(Action<Identification> action);
//
//    void snapshot(Action<Identification> action);

    /**
     * 正式版地址
     *
     * @return String
     */
    String getReleaseUrl();

    /**
     * 快照版地址
     *
     * @return String
     */
    String getSnapshotUrl();
}
