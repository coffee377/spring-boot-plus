package com.voc.gradle.plugin.action;

import com.voc.gradle.plugin.BootPlugin;
import com.voc.gradle.plugin.api.IPluginAction;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

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
        project.getLogger().error("=============");

    }
}
