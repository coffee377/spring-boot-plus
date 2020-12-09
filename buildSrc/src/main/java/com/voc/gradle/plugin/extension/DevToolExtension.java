package com.voc.gradle.plugin.extension;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/07/23 09:11
 */
@Getter
@Setter
public class DevToolExtension implements PluginExtension {

    /**
     * 本地 maven 地址
     */
    private String localMavenRepository;

    /**
     * 是否使用 kotlin
     */
    private boolean kotlin;

    /**
     * 是否使用 groovy
     */
    private boolean groovy;

    /**
     * 是否使用 Google AutoService
     */
    private boolean googleAutoService;

    private boolean javaTools;

//    private LibExtension lib = new LibExtension();
//
//    private CompileExtension compile = new CompileExtension();
//
//    void lib(Action<LibExtension> action) {
//        action.execute(lib);
//    }
//
//    void lib(Closure closure) {
//        ConfigureUtil.configure(closure, lib);
//    }
//
//    void compile(Action<CompileExtension> action) {
//        action.execute(compile);
//    }
//
//    void compile(Closure closure) {
//        ConfigureUtil.configure(closure, compile);
//    }

//    /**
//     * kotlin 版本
//     */
//    private String kotlinVersion;
//
//    /**
//     * lombok 版本
//     */
//    private String lombokVersion;


}
