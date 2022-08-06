package com.voc.gradle.plugin;

import lombok.extern.slf4j.Slf4j;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.ConfigurableFileTree;
import org.gradle.api.initialization.ProjectDescriptor;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;

import java.io.File;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/08/06 11:25
 */
@Slf4j
public class AutoProjectPlugin implements Plugin<Gradle> {
    @Override
    public void apply(Gradle gradle) {
        log.warn("{}", gradle.getGradleVersion());
        gradle.beforeSettings(new Action<Settings>() {
            @Override
            public void execute(Settings settings) {
                log.warn("自定义配置");
                File rootDir = settings.getRootDir();
                ProjectDescriptor rootProject = settings.getRootProject();

//                rootProject(rootDir, new Action<ConfigurableFileTree>() {
//                            @Override
//                            public void execute(ConfigurableFileTree files) {
//                                files.include("**/*.gradle", "**/*.gradle.kts");
//                                files.exclude("build", "**/gradle", "settings.gradle", "buildSrc", "/build.gradle", ".*", "out");
//                            }
//                        }).getFiles()
//                        .stream().map(file -> file.getAbsoluteFile())
//                        .forEach(file -> project.getLogger().error("", file.getAbsolutePath()));

//                        .stream().filter(file -> file.getParentFile())


            }
        });
    }
}
