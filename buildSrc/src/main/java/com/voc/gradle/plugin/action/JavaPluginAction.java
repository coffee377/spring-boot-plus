package com.voc.gradle.plugin.action;

import com.voc.gradle.plugin.api.IPluginAction;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/27 20:19
 */
public class JavaPluginAction implements IPluginAction {

    @Override
    public Class<? extends Plugin<? extends Project>> getPluginClass() {
        return JavaPlugin.class;
    }

    @Override
    public void execute(Project project) {

    }
}
