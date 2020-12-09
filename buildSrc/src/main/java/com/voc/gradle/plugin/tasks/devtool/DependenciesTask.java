package com.voc.gradle.plugin.tasks.devtool;

import com.voc.gradle.plugin.tasks.BaseTask;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.tasks.TaskAction;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/11 17:27
 */
public class DependenciesTask extends BaseTask {

//    private Map<String, Set> dd;

    @TaskAction
    public void ff() {
        System.out.println(">>>>>>>>>>>>>>>>> DependenciesTask");
    }

    /**
     * 添加依赖
     *
     * @param configurationName  配置面名称
     * @param dependencyNotation 依赖
     */
    public void add(String configurationName, Object dependencyNotation) {
        Project project = getProject();
        DependencyHandler dependencies = project.getDependencies();
        Dependency dependency = dependencies.create(dependencyNotation);
        dependencies.add(configurationName, dependency);
    }

//    /**
//     * 添加 annotationProcessor 依赖
//     *
//     * @param dependency Dependency
//     * @see JavaPlugin#ANNOTATION_PROCESSOR_CONFIGURATION_NAME
//     */
//    public void addAnnotationProcessor(Dependency dependency) {
//        DefaultExternalModuleDependency of = DependencyUtils.of(dependency, "");
//        String version = "";
//        add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, DependencyUtils.of(dependency, version));
//    }
//
//    /**
//     * 添加 implementation 依赖
//     *
//     * @param dependency Dependency
//     * @see JavaPlugin#IMPLEMENTATION_CONFIGURATION_NAME
//     */
//    public void addImplementation(Dependency dependency) {
//
//    }
//
//    /**
//     * 添加 testImplementation 依赖
//     *
//     * @param dependency Dependency
//     * @see JavaPlugin#TEST_IMPLEMENTATION_CONFIGURATION_NAME
//     */
//    public void addTestImplementation(Dependency dependency) {
//
//    }
//
//    /**
//     * 添加 compileOnly 依赖
//     *
//     * @param dependency Dependency
//     * @see JavaPlugin#COMPILE_ONLY_CONFIGURATION_NAME
//     */
//    public void addCompileOnly(Dependency dependency) {
//
//    }
//
//    /**
//     * 添加 api 依赖
//     *
//     * @param dependency Dependency
//     * @see JavaPlugin#API_CONFIGURATION_NAME
//     */
//    public void addApi(Dependency dependency) {
//
//    }
//
//    public void libAdd(String configurationName, Object dependencyNotation) {
//        getProject().afterEvaluate(project -> project.getDependencies().add(configurationName, dependencyNotation));
//    }
//
//    public <V> void libAdd(String configurationName, Callable<V> callable) {
//        try {
//            libAdd(configurationName, callable.call());
//        } catch (Exception e) {
//            getLogger().error(e.getMessage(), e);
//        }
//    }
//
//    public <V> void add(String configurationName, Callable<V> callable) {
//        try {
//            add(configurationName, callable.call());
//        } catch (Exception e) {
//            getLogger().error(e.getMessage(), e);
//        }
//    }

//    /**
//     * 项目下指定目录中的依赖包
//     *
//     * @param basePluginExtension BasePluginExtension
//     */
//    public void lib(BasePluginExtension basePluginExtension) {
//
//        this.libCheck(basePluginExtension);
//
//        /* 项目下指定目录中的依赖包， */
//        LibExtension lib = basePluginExtension.getLib();
//
//        lib.getExclude().addAll(Arrays.asList(lib.getAnnotationProcessorDir(), lib.getPluginsDir()));
//
//        libAdd(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, () ->
//                getProject().fileTree(lib.getDirName(), files -> {
//                    files.include(lib.getInclude());
//                    files.exclude(lib.getExclude());
//                }));
//
//    }
//
//    /**
//     * lib 检查
//     *
//     * @param basePluginExtension BasePluginExtension
//     */
//    public void libCheck(BasePluginExtension basePluginExtension) {
//        /* 项目下指定目录中的依赖包， */
//        LibExtension lib = basePluginExtension.getLib();
//
//        if (lib.getExclude() == null) {
//            lib.setExclude(new HashSet<>());
//        }
//        if (lib.getInclude() == null) {
//            lib.setInclude(new HashSet<>());
//        }
//    }

}
