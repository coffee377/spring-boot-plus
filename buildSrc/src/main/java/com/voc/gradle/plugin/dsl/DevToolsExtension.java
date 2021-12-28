package com.voc.gradle.plugin.dsl;

import com.voc.gradle.plugin.repository.MavenRepository;
import com.voc.gradle.plugin.repository.aliyun.AliYunMavenRepository;
import lombok.Getter;
import lombok.Setter;
import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/07/23 09:11
 */
@Getter
@Setter
public class DevToolsExtension {

    private final NamedDomainObjectContainer<MavenRepository> maven;
    private final NamedDomainObjectContainer<AliYunMavenRepository> ali;

    /**
     * 使用阿里云代理的仓库服务 {@link "https://maven.aliyun.com/mvn/guide"}
     */
    private boolean aliMaven = true;

    /**
     * 本地 maven 地址
     */
    private String localMavenRepository;

    /**
     * 是否使用 kotlin
     */
    private boolean kotlin;

    /**
     * 是否使用 groovy
     */
    private boolean groovy;

    /**
     * 是否使用 Google AutoService
     */
    private boolean googleAutoService;

    /**
     * 是否使用 java tools
     */
    private boolean javaTools;

    /**
     * 是否使用 lombok
     */
    private boolean lombok;

    /**
     * 是否开启单元测试
     */
    private boolean junit;

    public DevToolsExtension(Project project) {
        maven = project.container(MavenRepository.class);
        ali = project.container(AliYunMavenRepository.class);
    }

    public void maven(Action<? super NamedDomainObjectContainer<MavenRepository>> action) {
        action.execute(this.maven);
    }

    public void ali(Action<? super NamedDomainObjectContainer<AliYunMavenRepository>> action) {
        action.execute(this.ali);
    }

}
