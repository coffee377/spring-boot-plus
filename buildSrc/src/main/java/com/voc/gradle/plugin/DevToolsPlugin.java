package com.voc.gradle.plugin;

import com.voc.gradle.plugin.action.BootPluginAction;
import com.voc.gradle.plugin.action.DevToolsPluginAction;
import com.voc.gradle.plugin.action.JavaPluginAction;
import com.voc.gradle.plugin.action.MavenPublishPluginAction;
import com.voc.gradle.plugin.api.IPluginAction;
import com.voc.gradle.plugin.dsl.DevToolsExtension;
import com.voc.gradle.plugin.dsl.IDevToolsExtension;
import com.voc.gradle.plugin.embedded.IDE;
import com.voc.gradle.plugin.util.ExtraPropsUtils;
import lombok.Getter;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;
import org.gradle.ide.visualstudio.plugins.VisualStudioPlugin;
import org.gradle.ide.xcode.plugins.XcodePlugin;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.idea.IdeaPlugin;
import org.gradle.util.GradleVersion;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/23 14:27
 */
@Getter
public class DevToolsPlugin implements Plugin<Project> {
    public static final String ID = "com.voc.devtools";
    public static final String MIN_GRADLE_VERSION = "6.8";
    public static final String DEPENDENCY_MANAGEMENT_PLUGIN_ID = "io.spring.dependency-management";
    public static final String DEV_TOOL_EXTENSION_NAME = "devtools";

    @Override
    public void apply(Project project) {
        verifyGradleVersion();
        createExtension(project);
        applyPlugins(project);
        registerPluginActions(project);
    }

    private void verifyGradleVersion() {
        GradleVersion currentVersion = GradleVersion.current();
        if (currentVersion.compareTo(GradleVersion.version(MIN_GRADLE_VERSION)) < 0) {
            throw new GradleException("Devtools plugin requires Gradle 6.8.x, 6.9.x, or 7.x. "
                    + "The current version is " + currentVersion);
        }
    }

    private void registerPluginActions(Project project) {
        List<IPluginAction> actions = Arrays.asList(
                new JavaPluginAction(), new DevToolsPluginAction(), new MavenPublishPluginAction(),
                new BootPluginAction()
        );
        for (IPluginAction pluginAction : actions) {
            withPluginClassOfAction(pluginAction, (pluginClass) ->
                    project.getPlugins().withType(pluginClass, (plugin) -> pluginAction.execute(project)));
        }
    }

    private void withPluginClassOfAction(IPluginAction action,
                                         Consumer<Class<? extends Plugin<? extends Project>>> consumer) {
        try {
            consumer.accept(action.getPluginClass());
        } catch (Throwable ex) {
            // Plugin class unavailable. Continue.
        }
    }

    /**
     * 创建配置扩展
     *
     * @param project Project
     */
    private void createExtension(Project project) {
        ExtensionContainer extensions = project.getExtensions();
        extensions.create(IDevToolsExtension.class, DEV_TOOL_EXTENSION_NAME, DevToolsExtension.class, project);
//        extensions.create(DEV_TOOL_EXTENSION_NAME, IDevToolsExtension.class, project);
    }

    /**
     * 配置使用插件
     *
     * @param project Project
     */
    private void applyPlugins(Project project) {
        PluginContainer plugins = project.getPlugins();

        /* 1. 应用开发工具插件，默认使用 idea */
        String ide = ExtraPropsUtils.getStringValue(project, "ide", "idea");
        this.applyIdePlugin(plugins, IDE.of(ide));

        /* 2.应用 Java 插件 */
        plugins.apply(JavaPlugin.class);

        /* 3.应用 MavenPublish 插件 */
        plugins.apply(MavenPublishPlugin.class);

        /* 4.依赖管理插件 */
        plugins.apply(DEPENDENCY_MANAGEMENT_PLUGIN_ID);

    }

    /**
     * 应用开发工具插件
     *
     * @param plugins PluginContainer
     * @param ide     String
     */
    private void applyIdePlugin(PluginContainer plugins, IDE ide) {
        switch (ide) {
            case ECLIPSE:
                plugins.apply(EclipsePlugin.class);
                break;
            case VISUAL_STUDIO:
                plugins.apply(VisualStudioPlugin.class);
                break;
            case XCODE:
                plugins.apply(XcodePlugin.class);
                break;
            case IDEA:
            default:
                plugins.apply(IdeaPlugin.class);
        }
    }


}
