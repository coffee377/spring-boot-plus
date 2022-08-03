package com.voc.gradle.plugin.repository.aliyun;

import com.voc.gradle.plugin.repository.MavenRepository;
import com.voc.gradle.plugin.repository.VersionType;
import com.voc.gradle.plugin.util.StringUtils;
import lombok.EqualsAndHashCode;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * 阿里云效仓库地址组成 [prefix]/[id]-[type]-[hash]
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/10 14:49
 */
@EqualsAndHashCode(callSuper = true)
public class AliYunMavenRepository extends MavenRepository implements AliYunRepositoryInfo {
    private Property<String> prefix;
    private Property<String> id;
    private final Identification release;
    private final Identification snapshot;

    public AliYunMavenRepository(Project project) {
        this(null, project);
    }

    public AliYunMavenRepository(String name, Project project) {
        super(name, project);
        initProps(project.getObjects());
        release = new Identification(RepositoryType.RELEASE);
        snapshot = new Identification(RepositoryType.SNAPSHOT);
    }

    private void initProps(ObjectFactory objectFactory) {
        this.prefix = objectFactory.property(String.class).value("https://packages.aliyun.com/maven/repository");
        this.id = objectFactory.property(String.class);
    }

    @Override
    public String getPrefix() {
        return this.prefix.get();
    }

    @Override
    public void prefix(String prefix) {
        this.prefix.set(prefix);
    }

    @Override
    public String getId() {
        return this.id.get();
    }

    @Override
    public void id(String id) {
        this.id.set(id);
        release(identification -> identification.setId(id));
        snapshot(identification -> identification.setId(id));
    }

    @Override
    public void release(Action<Identification> action) {
        action.execute(this.release);
        handledUrl(VersionType.RELEASE, this.release);
    }

    @Override
    public void snapshot(Action<Identification> action) {
        action.execute(snapshot);
        handledUrl(VersionType.SNAPSHOT, this.snapshot);
    }

    @Override
    public String getReleaseUrl() {
        return getUrl(VersionType.RELEASE);
    }

    @Override
    public String getSnapshotUrl() {
        return getUrl(VersionType.SNAPSHOT);
    }

    @Override
    public List<String> getValidUrl() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        getValidUrl().stream().filter(StringUtils::isNotEmpty).forEach(url -> builder.append(url).append(" "));
        return builder.toString();
    }

    private void handledUrl(VersionType versionType, Identification identification) {
        String suffix = identification.toString();
        if (StringUtils.isEmpty(suffix)) {
            super.removeUrl(versionType);
        } else {
            super.url(versionType, String.format("%s/%s", prefix.get(), suffix));
        }
    }

}
