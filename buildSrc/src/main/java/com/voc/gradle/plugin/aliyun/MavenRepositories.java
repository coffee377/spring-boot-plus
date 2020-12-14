package com.voc.gradle.plugin.aliyun;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/10 14:49
 */
public class MavenRepositories {

    public String url;

    public String username;

    public String password;

    public MavenRepositories(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public MavenRepositories(String url) {
        this(url, null, null);
    }


    @Override
    public int hashCode() {
        return url.hashCode() + username.hashCode() + password.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MavenRepositories && this.hashCode() == obj.hashCode();
    }
}
