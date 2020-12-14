package com.voc.gradle.plugin.config;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.initialization.dsl.ScriptHandler;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.tasks.TaskContainer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/13 16:22
 */
public interface IConfigurable {

    /**
     * 获取当前项目
     *
     * @return Project
     */
    Project getProject();

    /**
     * 设置当前项目
     *
     * @param project Project
     */
    void setProject(Project project);

    /**
     * 配置扩展属性
     *
     * @param action Action<ExtensionContainer>
     */
    default void configureExtension(Action<ExtensionContainer> action) {
        action.execute(getProject().getExtensions());
    }

    /**
     * 配置脚本
     *
     * @param action Action<ScriptHandler>
     */
    default void configureBuildscript(Action<ScriptHandler> action) {
        action.execute(getProject().getBuildscript());
    }

    /**
     * 配置仓库
     *
     * @param action Action<RepositoryHandler>
     */
    default void configureRepositories(Action<RepositoryHandler> action) {
        action.execute(getProject().getRepositories());
    }

    /**
     * 配置依赖
     *
     * @param action        Action<DependencyHandler>
     * @param afterEvaluate 项目评估后
     */
    default void configureDependencies(Action<DependencyHandler> action, boolean afterEvaluate) {
        if (afterEvaluate) {
            getProject().afterEvaluate(project -> action.execute(project.getDependencies()));
        } else {
            action.execute(getProject().getDependencies());
        }
    }

    /**
     * 配置任务
     *
     * @param action Action<TaskContainer>
     */
    default void configureTask(Action<TaskContainer> action) {
        action.execute(getProject().getTasks());
    }

}
