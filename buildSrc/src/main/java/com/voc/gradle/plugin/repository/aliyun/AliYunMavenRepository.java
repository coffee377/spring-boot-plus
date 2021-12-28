package com.voc.gradle.plugin.repository.aliyun;

import com.voc.gradle.plugin.repository.MavenRepository;
import lombok.Getter;
import lombok.Setter;
import org.gradle.api.Action;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/10 14:49
 */
@Getter
@Setter
public class AliYunMavenRepository extends MavenRepository implements AliYunRepositoryInfo {

    /**
     * 云效仓库前缀
     */
    private String prefix = "https://packages.aliyun.com/maven/repository";

    /**
     * 云效仓库 ID
     */
    private String id;

    public void setId(String id) {
        this.id = id;
        this.release.setId(id);
        this.snapshot.setId(id);
    }

    /**
     * 生产库配置
     */
    private Identification release;

    /**
     * 快照库配置
     */
    private Identification snapshot;

    public AliYunMavenRepository(String name) {
        super(name);
        release = new Identification(RepositoryType.RELEASE);
        snapshot = new Identification(RepositoryType.SNAPSHOT);
    }

    public void release(Action<Identification> action) {
        action.execute(release);
    }

    public void snapshot(Action<Identification> action) {
        action.execute(snapshot);
    }


    @Override
    public String getUrl() {
        return String.format("%s %s", getReleaseUrl(), getSnapshotUrl());
    }

    @Override
    public String getUrl(RepositoryType repositoryType) {
        switch (repositoryType) {
            case SNAPSHOT:
                return String.format("%s/%s", prefix, snapshot.toString());
            case RELEASE:
            default:
                return String.format("%s/%s/", prefix, release.toString());
        }

    }

    @Override
    public String getReleaseUrl() {
        return getUrl(RepositoryType.RELEASE);
    }

    @Override
    public String getSnapshotUrl() {
        return getUrl(RepositoryType.SNAPSHOT);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
