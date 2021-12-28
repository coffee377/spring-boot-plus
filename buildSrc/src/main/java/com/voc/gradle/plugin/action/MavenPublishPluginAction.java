package com.voc.gradle.plugin.action;

import com.voc.gradle.plugin.api.IPluginAction;
import com.voc.gradle.plugin.util.StringUtils;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.api.artifacts.repositories.PasswordCredentials;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.api.publish.maven.MavenPublication;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;

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
        PublishingExtension publishing = project.getExtensions().getByType(PublishingExtension.class);
        RepositoryHandler repositoryHandler = publishing.getRepositories();
        MavenArtifactRepository maven = repositoryHandler.maven(repository -> {
            repository.setUrl("url");
            repository.credentials(credentials -> {
                if (StringUtils.isNotEmpty("username")) {
                    credentials.setUsername("username");
                }
                if (StringUtils.isNotEmpty("password")) {
                    credentials.setPassword("password");
                }
            });
        });
        repositoryHandler.add(maven);

        publishing.repositories(new Action<RepositoryHandler>() {
            @Override
            public void execute(RepositoryHandler artifactRepositories) {
                artifactRepositories.maven(new Action<MavenArtifactRepository>() {
                    @Override
                    public void execute(MavenArtifactRepository mavenArtifactRepository) {
                        mavenArtifactRepository.setUrl("");
                        mavenArtifactRepository.credentials(new Action<PasswordCredentials>() {
                            @Override
                            public void execute(PasswordCredentials passwordCredentials) {
                                passwordCredentials.setUsername("");
                                passwordCredentials.setPassword("");
                            }
                        });
                    }
                });
            }
        });
        MavenPublication mavenPublication = publishing.getPublications().create("maven", MavenPublication.class);
//        project.afterEvaluate((evaluated) -> {
//            project.getPlugins().withType(JavaPlugin.class).all((javaPlugin) -> {
//                if (((Jar) project.getTasks().getByName(JavaPlugin.JAR_TASK_NAME)).isEnabled()) {
//                    project.getComponents().matching((component) -> component.getName().equals("java"))
//                            .all((javaComponent) -> mavenPublication.from(javaComponent));
//                }
//            });
//        });
//        project.getPlugins().withType(JavaPlatformPlugin.class)
//                .all((javaPlugin) -> project.getComponents()
//                        .matching((component) -> component.getName().equals("javaPlatform"))
//                        .all((javaComponent) -> mavenPublication.from(javaComponent)));
    }
}
