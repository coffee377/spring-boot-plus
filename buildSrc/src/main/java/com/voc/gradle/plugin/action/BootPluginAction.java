package com.voc.gradle.plugin.action;

import com.voc.gradle.plugin.BootPlugin;
import com.voc.gradle.plugin.api.IPluginAction;
import com.voc.gradle.plugin.core.DevType;
import com.voc.gradle.plugin.dsl.IBootExtension;
import com.voc.gradle.plugin.dsl.IDevToolsExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.bundling.Jar;
import org.springframework.boot.gradle.plugin.SpringBootPlugin;
import org.springframework.boot.gradle.tasks.bundling.BootJar;
import org.springframework.boot.gradle.tasks.bundling.BootWar;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/30 22:47
 */
public class BootPluginAction implements IPluginAction {
    @Override
    public Class<? extends Plugin<? extends Project>> getPluginClass() {
        return BootPlugin.class;
    }

    @Override
    public void execute(Project project) {
        project.afterEvaluate(evaluated -> {
            DevType devType = evaluated.getExtensions().getByType(IDevToolsExtension.class).getType();
            IBootExtension bootExtension = evaluated.getExtensions().getByType(IBootExtension.class);
            if (DevType.LIB.equals(devType)) {
                bootExtension.library(true);
            }

            TaskContainer tasks = evaluated.getTasks();
            if (DevType.isJar(devType)) {
                boolean library = bootExtension.isLibrary();
                tasks.named(SpringBootPlugin.BOOT_JAR_TASK_NAME, BootJar.class, bootJar -> {
                    bootJar.setEnabled(!library);
                    bootJar.getArchiveClassifier().convention("app");
                });
                tasks.named(JavaPlugin.JAR_TASK_NAME, Jar.class, jar -> {
                    jar.setEnabled(library);
                    jar.getArchiveClassifier().convention("");
                });

            } else {
                tasks.withType(BootWar.class, war -> {
                });
            }

        });
    }
}
