package com.voc.gradle.plugin;

import com.voc.gradle.plugin.api.IDependency;
import com.voc.gradle.plugin.extension.BootExtension;
import com.voc.gradle.plugin.util.DepUtils;
import org.gradle.api.Project;
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.jvm.tasks.Jar;
//import org.springframework.boot.gradle.tasks.bundling.BootJar;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/09 14:57
 */
public class BootPlugin extends AbstractPlugin implements IDependency {
    public static final String SPRING_BOOT_PLUGIN_ID = "org.springframework.boot";
    public static final String BOOT_EXTENSION_NAME = "boot";

    @Override
    public void onApply(Project project) {
        project.getExtensions().create(BOOT_EXTENSION_NAME, BootExtension.class, project);
        PluginContainer plugins = project.getPlugins();
        plugins.apply(SPRING_BOOT_PLUGIN_ID);
        if (!plugins.hasPlugin(DevToolsPlugin.ID)) {
            plugins.apply(DevToolsPlugin.DEPENDENCY_MANAGEMENT_PLUGIN_ID);
        }
        this.configureBoot(project);
    }

    private void configureBoot(Project project) {
        project.afterEvaluate(p -> {
            BootExtension extension = p.getExtensions().getByType(BootExtension.class);
            boolean library = extension.getLibrary().getOrElse(Boolean.FALSE);
            TaskContainer tasks = p.getTasks();
//            tasks.withType(BootJar.class, bootJar -> bootJar.setEnabled(!library));
            tasks.withType(Jar.class, jar -> jar.setEnabled(library));
        });
    }

    /**
     * 单元测试配置
     */
    private void configureUnitTest() {
//        project.getConfigurations().all("");
        DefaultExternalModuleDependency dependency = DepUtils.of("", "", null);
        addDependency("", dependency);
//        addTestImplementation(null);
    }

}
