package com.voc.gradle.plugin.dsl;

import com.voc.gradle.plugin.api.ProjectBase;
import com.voc.gradle.plugin.core.DevType;
import com.voc.gradle.plugin.repository.MavenRepository;
import com.voc.gradle.plugin.repository.RepositoryInfo;
import com.voc.gradle.plugin.repository.aliyun.AliYunMavenRepository;
import com.voc.gradle.plugin.repository.aliyun.AliYunRepositoryInfo;
import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.Project;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.api.provider.Provider;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/07/23 09:11
 */
public class DevToolsExtension extends ProjectBase implements IDevToolsExtension {
    public final Property<DevType> type;
    private final Property<Boolean> aliMavenProxy;
    private final NamedDomainObjectContainer<? extends RepositoryInfo> maven;
    private final NamedDomainObjectContainer<? extends AliYunRepositoryInfo> ali;
    private final Property<String> localMavenRepository;
    private final Property<Boolean> kotlin;
    private final Property<Boolean> groovy;
    private final Property<Boolean> googleAutoService;
    private final Property<Boolean> javaTools;
    private final Property<Boolean> lombok;
    private final Property<Boolean> junit;

    public DevToolsExtension(Project project) {
        super(project);
        maven = project.container(MavenRepository.class);
        ali = project.container(AliYunMavenRepository.class);
        ObjectFactory objectFactory = project.getObjects();

        type = objectFactory.property(DevType.class).value(DevType.LIB);
        aliMavenProxy = objectFactory.property(Boolean.class).value(false);
        localMavenRepository = objectFactory.property(String.class);
        kotlin = objectFactory.property(Boolean.class).value(false);
        groovy = objectFactory.property(Boolean.class).value(false);
        googleAutoService = objectFactory.property(Boolean.class).value(false);
        javaTools = objectFactory.property(Boolean.class).value(false);
        lombok = objectFactory.property(Boolean.class).value(false);
        junit = objectFactory.property(Boolean.class).value(false);
    }

    @Override
    public DevType getType() {
        return type.get();
    }

    @Override
    public void type(DevType devType) {
        this.type.set(devType);
    }

    @Override
    public void type(String devTypeName) {
        Provider<DevType> provider = getProject().provider(() -> DevType.of(devTypeName));
        this.type.set(provider);
    }

    @Override
    public boolean isAliMavenProxy() {
        return this.aliMavenProxy.get();
    }

    @Override
    public void aliMavenProxy(boolean enabled) {
        this.aliMavenProxy.set(enabled);
    }

    @Override
    public NamedDomainObjectContainer<? extends RepositoryInfo> mavenRepository() {
        return this.maven;
    }

    @Override
    public void maven(Action<? super NamedDomainObjectContainer<? extends RepositoryInfo>> action) {
        action.execute(this.maven);
    }

    @Override
    public NamedDomainObjectContainer<? extends AliYunRepositoryInfo> aliMavenRepository() {
        return this.ali;
    }

    @Override
    public void ali(Action<? super NamedDomainObjectContainer<? extends AliYunRepositoryInfo>> action) {
        action.execute(this.ali);
    }

    @Override
    public String getLocalMavenRepository() {
        return this.localMavenRepository.getOrElse("");
    }

    @Override
    public void localMavenRepository(String url) {
        this.localMavenRepository.set(url);
    }

    @Override
    public boolean useKotlin() {
        return this.kotlin.get();
    }

    @Override
    public void kotlin(boolean enabled) {
        this.kotlin.set(enabled);
    }

    @Override
    public boolean useGroovy() {
        return this.groovy.get();
    }

    @Override
    public void groovy(boolean enabled) {
        this.groovy.set(enabled);
    }

    @Override
    public boolean useAutoService() {
        return this.googleAutoService.get();
    }

    @Override
    public void autoService(boolean enabled) {
        this.googleAutoService.set(enabled);
    }

    @Override
    public boolean useJavaTools() {
        return this.javaTools.get();
    }

    @Override
    public void javaTools(boolean enabled) {
        this.javaTools.set(enabled);
    }

    @Override
    public boolean useLombok() {
        return this.lombok.get();
    }

    @Override
    public void lombok(boolean enabled) {
        this.lombok.set(enabled);
    }

    @Override
    public boolean useJunit() {
        return this.junit.get();
    }

    @Override
    public void junit(boolean enabled) {
        this.junit.set(enabled);
    }
}
