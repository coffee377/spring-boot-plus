package com.voc.gradle.plugin.tasks;

import org.gradle.api.Project;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/18 21:33
 */
public class AppInfoTask extends BaseTask {
    private final static String SPACE = " ";
    private final static String SEP = ": ";

    public AppInfoTask() {
        this.setGroup("build");
    }

    @TaskAction
    public void action() throws IOException {
        Project project = this.getProject();
        ConfigurationContainer configurations = project.getConfigurations();
        JavaPluginExtension javaExtension = project.getExtensions().getByType(JavaPluginExtension.class);

        SourceSetContainer sourceSets = javaExtension.getSourceSets();
        SourceSet main = sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME);
        String resourcesPath = main.getResources().getSourceDirectories().getAsPath();
        File metaInfoDir = new File(new File(resourcesPath), "META-INF");
        this.getProject().mkdir(metaInfoDir);
        File file = new File(metaInfoDir, "app-info.yml");
        FileWriter writer = new FileWriter(file, false);
        String line1 = writeLine(1, "spring");
        writer.append(line1);
        String line2 = writeLine(2, "application");
        writer.append(line2);
        String line3 = writeLine(3, "name", project.getName());
        writer.append(line3);
        String line4 = writeLine(3, "version", project.getVersion().toString());
        writer.append(line4);
        writer.flush();
    }

    /**
     * 行内容输出
     *
     * @param level 级别
     * @param key   键名
     * @return String
     */
    private String writeLine(int level, String key) {
        return this.writeLine(level, key, "");
    }

    /**
     * 行内容输出
     *
     * @param level   级别
     * @param key     键名
     * @param content 内容
     * @return String
     */
    public String writeLine(int level, String key, String content) {
        StringBuilder builder = new StringBuilder();
        builder.append(repeatSpace(level));
        builder.append(key);
        builder.append(SEP);

        if (content != null && !"".equals(content)) {
            builder.append(content);
        }
        builder.append("\n");
        return builder.toString();
    }

    /**
     * 根据级别生成空格数
     *
     * @param level 级别
     * @return String
     */
    public String repeatSpace(int level) {
        int n = 0;
        if (level > 0) {
            n = level - 1;
        }

        return String.join("", Collections.nCopies(2 * n, SPACE));
    }
}
