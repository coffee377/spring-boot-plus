package com.voc.gradle.plugin;

import com.voc.gradle.plugin.tasks.AppInfoTask;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.TaskContainer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/17 18:33
 */
public class AppInfoPlugin extends AbstractPlugin {
    @Override
    public void onApply(Project project) {
        TaskContainer tasks = project.getTasks();
        tasks.register("appInfo", AppInfoTask.class, appInfoTask -> {
            
        });
        Task classesTask = project.getTasks().getByName(JavaPlugin.CLASSES_TASK_NAME);
        classesTask.dependsOn("appInfo");
    }

}
