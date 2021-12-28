package com.voc.gradle.plugin.repository;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/10 14:49
 */
public class MavenRepository implements RepositoryInfo {

    /**
     * 名称
     */
    private final String name;

    /**
     * 仓库地址
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 允许发布到此仓库
     */
    private boolean allowPublish;

    public MavenRepository() {
        this(null);
    }

    public MavenRepository(String name) {
        this.name = name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAllowPublish() {
        return allowPublish;
    }

    @Override
    public void setAllowPublish(boolean allowPublish) {
        this.allowPublish = allowPublish;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return url.hashCode() + username.hashCode() + password.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MavenRepository && this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return getUrl();
    }
}
