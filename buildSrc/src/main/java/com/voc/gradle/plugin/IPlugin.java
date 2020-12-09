package com.voc.gradle.plugin;

import com.voc.gradle.plugin.config.IConfigurable;
import com.voc.gradle.plugin.extension.PluginExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/13 16:19
 */
public interface IPlugin<E extends PluginExtension> extends Plugin<Project>, IConfigurable<E> {

    /**
     * 构建脚本
     *
     * @param project Project
     */
    default void buildscript(Project project) {
//        System.out.println(project.getName() + " " + new Date() + " - 在解析setting.gradle之后，开始解析build.gradle之前");
    }

    /**
     * 应用插件
     *
     * @param project Project
     */
    void onApply(Project project);

    /**
     * 注册添加任务
     *
     * @param project Project
     */
    void registerTask(Project project);

}
