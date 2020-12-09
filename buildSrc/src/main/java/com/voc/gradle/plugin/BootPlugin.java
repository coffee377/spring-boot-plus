package com.voc.gradle.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.PluginContainer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/09 14:57
 */
public class BootPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        PluginContainer plugins = project.getPlugins();
//        plugins.apply("");
    }
}
