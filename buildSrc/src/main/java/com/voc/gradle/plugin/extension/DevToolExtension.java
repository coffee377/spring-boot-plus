package com.voc.gradle.plugin.extension;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/07/23 09:11
 */
@Getter
@Setter
public class DevToolExtension implements PluginExtension {

    /**
     * 开发工具
     */
    String ide;

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

    @Getter
    public enum IDE {
        /**
         * 开发工具
         */
        IDEA("idea"),
        ECLIPSE("eclipse"),
        VISUAL_STUDIO("visualStudio"),
        XCODE("xcode");

        private final String name;

       public static IDE of(String ideName) {
           return Arrays.stream(IDE.values()).filter(ide -> ide.getName().equalsIgnoreCase(ideName)).findFirst().orElse(IDE.IDEA);
        }

        IDE(String name) {
            this.name = name;
        }
    }

}
