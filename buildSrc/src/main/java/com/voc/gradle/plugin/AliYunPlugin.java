package com.voc.gradle.plugin;

import com.voc.gradle.plugin.tasks.devtool.RepositoriesTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.tasks.TaskContainer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/09 10:56
 */
public class AliYunPlugin implements Plugin<Project> {
    /**
     * https://maven.aliyun.com/mvn/guide
     *
     * @param project Project
     */
    @Override
    public void apply(Project project) {
        PluginContainer plugins = project.getPlugins();
        if (plugins.hasPlugin(DevToolPlugin.class)) {
//            System.out.println("存在插件");
            plugins.apply(DevToolPlugin.class);
            plugins.apply(AliYunPlugin.class);
        } else {
//            System.out.println("不存在插件");
        }
        TaskContainer tasks = project.getTasks();
//        RepositoriesTask.
//        tasks.getByName()
        tasks.withType(RepositoriesTask.class, task -> {
            /* Ali Yun central仓和jcenter仓的聚合仓 */
            task.add("https://maven.aliyun.com/repository/public/");
            /* Ali 云效 */
            task.add("https://packages.aliyun.com/maven/repository/2038604-release-0bMxsA/", "5f4ba059fa82bfeb805a1e09", "a3XkZLNApybs");
            task.add("https://packages.aliyun.com/maven/repository/2038604-snapshot-XNRePo/", "5f4ba059fa82bfeb805a1e09", "a3XkZLNApybs");
            /* google */
            task.add("https://maven.aliyun.com/repository/google");
            /* gradle-plugin */
            task.add("https://maven.aliyun.com/repository/gradle-plugin");
            /* spring */
            task.add("https://maven.aliyun.com/repository/spring");
            /* spring-plugin */
            task.add("https://maven.aliyun.com/repository/spring-plugin");
        });
        System.out.println("==============> " + project.getName());
    }
}
