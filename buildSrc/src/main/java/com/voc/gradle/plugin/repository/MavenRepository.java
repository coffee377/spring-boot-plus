package com.voc.gradle.plugin.repository;

import com.voc.gradle.plugin.api.ProjectBase;
import org.gradle.api.Project;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/10 14:49
 */
public class MavenRepository extends ProjectBase implements RepositoryInfo {

    private final Property<String> name;
    public final Property<String> url;
    protected final Property<String> username;
    private final Property<String> password;
    private final Property<Boolean> publish;

    public MavenRepository(Project project) {
        this(null, project);
    }

    public MavenRepository(String name, Project project) {
        super(project);
        ObjectFactory objectFactory = project.getObjects();
        this.name = objectFactory.property(String.class).value(name);
        this.url = objectFactory.property(String.class);
        this.username = objectFactory.property(String.class);
        this.password = objectFactory.property(String.class);
        this.publish = objectFactory.property(Boolean.class).value(false);
    }

    @Override
    public String getUrl() {
        return this.url.getOrNull();
    }

    @Override
    public void setUrl(String url) {
        this.url.set(url);
    }

    @Override
    public void url(String url) {
        this.url.set(url);
    }

    @Override
    public String getUsername() {
        return this.username.getOrNull();
    }

    @Override
    public void setUsername(String username) {
        this.username.set(username);
    }

    @Override
    public void username(String username) {
        this.username.set(username);
    }

    @Override
    public String getPassword() {
        return this.password.getOrNull();
    }

    @Override
    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public void password(String password) {
        this.password.set(password);
    }

    @Override
    public boolean isAllowPublish() {
        return this.publish.get();
    }

    @Override
    public void setAllowPublish(boolean allowPublish) {
        this.publish.set(publish);
    }

    @Override
    public void publish(boolean allowed) {
        this.publish.set(allowed);
    }

    @Override
    public String getName() {
        return this.name.getOrNull();
    }

    @Override
    public int hashCode() {
        return name.hashCode() + url.hashCode() + username.hashCode() + password.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MavenRepository && this.hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {
        return this.url.get();
    }
}
