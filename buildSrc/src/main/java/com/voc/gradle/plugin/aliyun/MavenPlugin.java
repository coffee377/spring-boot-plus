package com.voc.gradle.plugin.aliyun;

import com.voc.gradle.plugin.AbstractPlugin;
import com.voc.gradle.plugin.tasks.devtool.RepositoriesTask;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.tasks.compile.JavaCompile;

import static com.voc.gradle.plugin.tasks.BaseTask.DEVTOOLS_GROUP_NAME;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/09 10:56
 */
public class MavenPlugin extends AbstractPlugin {
    /**
     * https://maven.aliyun.com/mvn/guide
     *
     * @param project Project
     */
    public void apply2(Project project) {
//        tasks.withType(RepositoriesTask.class, task -> {
//            /* Ali Yun central仓和jcenter仓的聚合仓 */
//            task.addMavenRepository("https://maven.aliyun.com/repository/public/");
//            /* Ali 云效 */
//            task.addMavenRepository("https://packages.aliyun.com/maven/repository/2038604-release-0bMxsA/",
//                    "5f4ba059fa82bfeb805a1e09", "a3XkZLNApybs");
//            task.addMavenRepository("https://packages.aliyun.com/maven/repository/2038604-snapshot-XNRePo/", "5f4ba059fa82bfeb805a1e09", "a3XkZLNApybs");
//            /* google */
//            task.addMavenRepository("https://maven.aliyun.com/repository/google");
//            /* gradle-plugin */
//            task.addMavenRepository("https://maven.aliyun.com/repository/gradle-plugin");
//            /* spring */
//            task.addMavenRepository("https://maven.aliyun.com/repository/spring");
//            /* spring-plugin */
//            task.addMavenRepository("https://maven.aliyun.com/repository/spring-plugin");
//        });
    }

    /**
     * https://maven.aliyun.com/mvn/guide
     *
     * @param project Project
     */
    @Override
    public void onApply(Project project) {
        /* 创建扩展并初始默认值 */
        AliYunMavenExtension aliYunMavenExtension = project.getExtensions().create("aliyun", AliYunMavenExtension.class, project.getObjects());

        RepositoryHandler repositoryHandler = project.getRepositories();

        project.getTasks().register("dep", RepositoriesTask.class, task -> {
            task.setDescription("Automatically add repositories.");
            task.setGroup(DEVTOOLS_GROUP_NAME);
//            task.addMavenLocal();
            task.addMavenCentral();
            task.addMavenRepository("","","");
        });

        project.getTasks().withType(JavaCompile.class, task -> {
            task.dependsOn("dep");
        });

//        project.afterEvaluate(p->{
//            AliYunMavenExtension byType = p.getExtensions().getByType(AliYunMavenExtension.class);
//
//            MavenArtifactRepository maven = repositoryHandler.maven(mavenRepository -> {
//                mavenRepository.setUrl(url);
//                mavenRepository.credentials(credentials -> {
//                    if (StringUtils.isNotEmpty(username)) {
//                        credentials.setUsername(username);
//                    }
//                    if (StringUtils.isNotEmpty(password)) {
//                        credentials.setPassword(password);
//                    }
//                });
//            });
//            repositoryHandler.add(maven);
//
//        });


    }


}
