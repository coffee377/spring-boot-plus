package com.voc.gradle.plugin.action;

import com.voc.gradle.plugin.api.IPluginAction;
import com.voc.gradle.plugin.core.DevType;
import com.voc.gradle.plugin.dsl.IDevToolsExtension;
import com.voc.gradle.plugin.repository.RepositoryInfo;
import com.voc.gradle.plugin.repository.aliyun.AliYunRepositoryInfo;
import com.voc.gradle.plugin.util.RepositoryUtil;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.attributes.Usage;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.JavaPlatformPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.publish.VariantVersionMappingStrategy;
import org.gradle.api.publish.maven.*;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;
import org.gradle.api.tasks.bundling.Jar;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 14:38
 */
public class MavenPublishPluginAction implements IPluginAction {
    @Override
    public Class<? extends Plugin<? extends Project>> getPluginClass() {
        return MavenPublishPlugin.class;
    }

    @Override
    public void execute(Project project) {
        ExtensionContainer extensions = project.getExtensions();
        PublishingExtension publishing = extensions.getByType(PublishingExtension.class);

        /* 1. 解析需要发布到的仓库 */
        resolvePublishRepository(project, publishing.getRepositories());

        /* 2. 发布配置 */
        publishing.getPublications().withType(MavenPublication.class)
                .all(mavenPublication -> customizeMavenPublication(mavenPublication, project));

        /* 3. */
        resolvePublication(project);
    }

    private void resolvePublication(Project project) {
        project.afterEvaluate(evaluated -> {
            IDevToolsExtension devToolsExtension = evaluated.getExtensions().getByType(IDevToolsExtension.class);
            DevType devType = devToolsExtension.getType();
            PublishingExtension publishing = evaluated.getExtensions().getByType(PublishingExtension.class);
            MavenPublication mavenPublication = publishing.getPublications().create(devType.getPubName(), MavenPublication.class);
            if (DevType.isJar(devType)) {
                PluginContainer plugins = evaluated.getPlugins();
                plugins.withType(JavaPlugin.class).all(javaPlugin -> {
                    evaluated.getTasks().withType(Jar.class).all(jar -> {
                        if (jar.getEnabled()) {
                            evaluated.getComponents().matching(component -> "java".equals(component.getName()))
                                    .all(mavenPublication::from);
                        }
                    });
                });
                plugins.withType(JavaPlatformPlugin.class)
                        .all((javaPlugin) -> evaluated.getComponents()
                                .matching((component) -> "javaPlatform".equals(component.getName()))
                                .all(mavenPublication::from));
            }

        });

    }

    private void customizeMavenPublication(MavenPublication publication, Project project) {
        customizePom(publication.getPom(), project);
        project.getPlugins().withType(JavaPlugin.class)
                .all((javaPlugin) -> customizeJavaMavenPublication(publication, project));
    }

    private void customizePom(MavenPom pom, Project project) {
        pom.getName().set(project.provider(project::getName));
        customizePackaging(pom, project);
        pom.getDescription().set(project.provider(project::getDescription));
        pom.licenses(this::customizeLicences);
        pom.developers(this::customizeDevelopers);
        pom.scm((scm) -> customizeScm(scm, project));
    }

    private void customizePackaging(MavenPom pom, Project project) {
        project.afterEvaluate(evaluated -> {
            DevType devType = evaluated.getExtensions().getByType(IDevToolsExtension.class).getType();
            if (DevType.isPom(devType) || DevType.isWar(devType)) {
                pom.setPackaging(devType.getPackaging());
            }
        });
    }


    private void customizeScm(MavenPomScm scm, Project project) {

    }

    private void customizeDevelopers(MavenPomDeveloperSpec developers) {
        developers.developer((developer) -> {
            developer.getId().set("coffee377");
            developer.getName().set("Wu Yujie");
            developer.getEmail().set("coffee377@dingtalk.com");
        });
    }

    private void customizeLicences(MavenPomLicenseSpec mavenPomLicenseSpec) {

    }


    private void customizeJavaMavenPublication(MavenPublication publication, Project project) {
        publication.versionMapping((strategy) -> strategy.usage(Usage.JAVA_API, (mappingStrategy) -> mappingStrategy
                .fromResolutionOf(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME)));
        publication.versionMapping(
                (strategy) -> strategy.usage(Usage.JAVA_RUNTIME, VariantVersionMappingStrategy::fromResolutionResult));
    }


    private void resolvePublishRepository(Project project, RepositoryHandler publishingRepositories) {
        project.afterEvaluate(evaluated -> {
            IDevToolsExtension devToolsExtension = evaluated.getExtensions().getByType(IDevToolsExtension.class);
            // TODO: 2021/12/30 10:28
            List<RepositoryInfo> mavenRepositories = devToolsExtension.mavenRepository().stream()
                    .filter((Predicate<RepositoryInfo>) RepositoryInfo::isAllowPublish).collect(Collectors.toList());
            List<AliYunRepositoryInfo> aliYunMavenRepositories = devToolsExtension.aliMavenRepository().stream()
                    .filter((Predicate<AliYunRepositoryInfo>) RepositoryInfo::isAllowPublish).collect(Collectors.toList());
            mavenRepositories.addAll(aliYunMavenRepositories);
            resolveRepositoryInfo(publishingRepositories, mavenRepositories);
        });
    }

    private void resolveRepositoryInfo(RepositoryHandler repositoryHandler, List<RepositoryInfo> repositoryInfos) {
        repositoryInfos.forEach(repositoryInfo -> RepositoryUtil.addRepository(repositoryHandler, repositoryInfo));
    }
}
