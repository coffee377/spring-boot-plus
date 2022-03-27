package com.voc.gradle.plugin;

import com.voc.gradle.plugin.action.BootPluginAction;
import com.voc.gradle.plugin.api.IDependency;
import com.voc.gradle.plugin.api.IPluginAction;
import com.voc.gradle.plugin.dsl.BootExtension;
import com.voc.gradle.plugin.dsl.IBootExtension;
import com.voc.gradle.plugin.util.DepUtils;
import org.gradle.api.Project;
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency;
import org.gradle.api.plugins.PluginContainer;

import java.util.Collections;
import java.util.List;

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
        project.getExtensions().create(IBootExtension.class, BOOT_EXTENSION_NAME, BootExtension.class, project);
        PluginContainer plugins = project.getPlugins();
        plugins.apply(SPRING_BOOT_PLUGIN_ID);
        if (!plugins.hasPlugin(DevToolsPlugin.ID)) {
            plugins.apply(DevToolsPlugin.DEPENDENCY_MANAGEMENT_PLUGIN_ID);
        }
    }

    @Override
    public List<IPluginAction> getPluginActions() {
        return Collections.singletonList(new BootPluginAction());
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
