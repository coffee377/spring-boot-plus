package com.voc.gradle.plugin;

import org.gradle.api.Project;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/13 16:37
 */
public abstract class AbstractPlugin implements IPlugin {

    private Project project;

    //    private final Class<E> extensionClazz;
//
//    @SuppressWarnings("unchecked")
//    public AbstractPlugin() {
//        /* 获取当前new的对象的泛型的父类类型 */
//        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
//        /* 获取第一个类型参数的真实类型 */
//        this.extensionClazz = (Class<E>) pt.getActualTypeArguments()[0];
//    }
////
//    @Override
//    public void apply(Project project) {
//        /* 构建脚本 */
////        this.buildscript(project);
//
//        /* 扩展配置 */
////        if (!extensionClazz.isAssignableFrom(PluginExtension.class)) {
////            this.addExtension(project, extensionClazz);
////        }
////
////        /* 应用插件 */
////        this.onApply(project);
////
////        /* 注册任务 */
////        this.registerTask(project);
////
////        /* 评估后配置 */
////        project.afterEvaluate(p -> {
////            configureRepositories(p);
////            configureDependencies(p);
////            configureSourceSets(p);
////            configureJavaCompile(p);
////            configureKotlinCompile(p);
////            configureJar(p);
////            configureWar(p);
////            configureZip(p);
////            configure(p);
////        });
//    }


    @Override
    public Project getProject() {
        return this.project;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

//    @Override
//    public void configureBuildscript(Action<ScriptHandler> buildScriptAction) {
//        buildScriptAction.execute(getProject().getBuildscript());
//    }
//
//    @Override
//    public void apply(Project project) {
//        this.project = project;
////        this.configureExtension(project.getExtensions());
////        Action<ScriptHandler> action = new Action<ScriptHandler>() {
////            @Override
////            public void execute(ScriptHandler scriptHandler) {
////
////            }
////        };
////        action.execute(project.getBuildscript());
////        this.configureBuildscript(new Action<ScriptHandler>() {
////            @Override
////            public void execute(ScriptHandler scriptHandler) {
////
////            }
////        });
////        this.configureRepositories(project.getRepositories());
////        this.configureDependencies(project.getDependencies());
////        this.configureTask(project.getTasks());
////        this.configure(project);
//    }

}
