package com.voc.gradle.plugin.repository;

import com.voc.gradle.plugin.api.IProject;
import org.gradle.api.Named;
import org.gradle.api.provider.Property;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 15:29
 */
public interface RepositoryInfo extends IProject, Named, Serializable {
    /**
     * 仓库地址
     *
     * @return String
     * @since 0.0.1
     */
    String getUrl();

    /**
     * 设置仓库地址
     *
     * @param url String
     * @see #url(String)
     * @since 0.0.1
     * @deprecated
     */
    void setUrl(String url);

    /**
     * 设置仓库地址
     *
     * @param url String
     * @since 0.0.2
     */
    void url(String url);

    /**
     * 用户名
     *
     * @return String
     * @since 0.0.1
     */
    String getUsername();

    /**
     * 设置用户名
     *
     * @param username String
     * @see #username(String)
     * @since 0.0.1
     * @deprecated
     */
    void setUsername(String username);

    /**
     * 设置用户名
     *
     * @param username String
     * @since 0.0.2
     */
    void username(String username);

    /**
     * 密码
     *
     * @return String
     * @since 0.0.1
     */
    String getPassword();

    /**
     * 设置密码
     *
     * @param password String
     * @see #password(String)
     * @since 0.0.1
     * @deprecated
     */
    void setPassword(String password);

    /**
     * 设置密码
     *
     * @param password String
     * @since 0.0.2
     */
    void password(String password);

    /**
     * 是否允许 @{code MavenPublishPlugin} 发布到此仓库
     *
     * @return boolean
     * @since 0.0.1
     */
    boolean isAllowPublish();

    /**
     * 设置是否运行发布到此仓库
     *
     * @param allowPublish boolean
     * @see #publish(boolean)
     * @since 0.0.1
     * @deprecated
     */
    void setAllowPublish(boolean allowPublish);

    /**
     * 设置是否允许发布至此仓库
     *
     * @param allowed boolean
     * @since 0.0.2
     */
    void publish(boolean allowed);


    /**
     * 构建仓库允许发布到此仓库
     *
     * @return Property<Boolean>
     * @since 0.0.2
     * @deprecated
     */
    default Property<Boolean> publish() {
        return null;
    }

    /**
     * 仓库地址
     *
     * @return Property<String>
     * @since 0.0.2
     * @deprecated
     */
    default Property<String> url() {
        return null;
    }

    /**
     * 用户名
     *
     * @return Property<String>
     * @since 0.0.2
     */
    default Property<String> username() {
        return null;
    }

    /**
     * 密码
     *
     * @return Property<String>
     * @since 0.0.2
     * @deprecated
     */
    default Property<String> password() {
        return null;
    }
}
