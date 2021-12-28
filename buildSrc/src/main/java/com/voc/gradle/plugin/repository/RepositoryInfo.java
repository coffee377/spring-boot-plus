package com.voc.gradle.plugin.repository;

import org.gradle.api.Named;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 15:29
 */
public interface RepositoryInfo extends Named, Serializable {
    /**
     * 仓库地址
     *
     * @return String
     */
    String getUrl();

    /**
     * 设置仓库地址
     *
     * @param url String
     */
    void setUrl(String url);

    /**
     * 用户名
     *
     * @return String
     */
    String getUsername();

    /**
     * 设置用户名
     *
     * @param username String
     */
    void setUsername(String username);

    /**
     * 密码
     *
     * @return String
     */
    String getPassword();

    /**
     * 设置密码
     *
     * @param password String
     */
    void setPassword(String password);

    /**
     * 是否运行发布到此仓库
     *
     * @return boolean
     */
    boolean isAllowPublish();

    /**
     * 设置是否运行发布到此仓库
     *
     * @param allowPublish boolean
     */
    void setAllowPublish(boolean allowPublish);
}
