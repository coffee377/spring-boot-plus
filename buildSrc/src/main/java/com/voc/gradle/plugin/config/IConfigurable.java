package com.voc.gradle.plugin.config;

import com.voc.gradle.plugin.extension.PluginExtension;
import org.gradle.api.Project;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/13 16:22
 */
public interface IConfigurable<E extends PluginExtension> extends IExtension<E> {

    /**
     * 配置仓库
     *
     * @param project   Project
     */
    default void configureRepositories(Project project) {
    }


    /**
     * 配置依赖
     *
     * @param project   Project
     */
    default void configureDependencies(Project project) {
    }

    /**
     * 源码集合
     *
     * @param project   Project
     */
    default void configureSourceSets(Project project) {
    }


    /**
     * JAVA 编译配置
     *
     * @param project   Project
     */
    default void configureJavaCompile(Project project) {
    }

    /**
     * Kotlin 编译配置
     *
     * @param project   Project
     */
    default void configureKotlinCompile(Project project) {

    }

    /**
     * 打包配置
     *
     * @param project   Project
     */
    default void configureJar(Project project) {
    }

    /**
     * 归档配置
     *
     * @param project   Project
     */
    default void configureWar(Project project) {
    }

    /**
     * 归档配置
     *
     * @param project   Project
     */
    default void configureZip(Project project) {
    }

    /**
     * 其他项配置
     *
     * @param project   Project
     */
    default void configure(Project project) {
    }

}
