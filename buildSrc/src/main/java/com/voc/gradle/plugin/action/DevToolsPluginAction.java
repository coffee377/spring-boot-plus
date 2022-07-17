package com.voc.gradle.plugin.action;

import com.voc.gradle.plugin.DevToolsPlugin;
import com.voc.gradle.plugin.api.IDependency;
import com.voc.gradle.plugin.api.IPluginAction;
import com.voc.gradle.plugin.api.IRepository;
import com.voc.gradle.plugin.dsl.IDevToolsExtension;
import com.voc.gradle.plugin.embedded.DepEnum;
import com.voc.gradle.plugin.embedded.ExtraProps;
import com.voc.gradle.plugin.util.ExtraPropsUtils;
import com.voc.gradle.plugin.util.StringUtils;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.testing.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 11:02
 */
public class DevToolsPluginAction implements IPluginAction, IRepository, IDependency {
    public final Map<String, String> INNER_ALI_MAVEN = Collections.unmodifiableMap(
            new HashMap<String, String>() {{
                put("Public", "https://maven.aliyun.com/repository/public/");
                put("GradlePlugin", "https://maven.aliyun.com/repository/gradle-plugin");
                put("Spring", "https://maven.aliyun.com/repository/spring");
                put("SpringPlugin", "https://maven.aliyun.com/repository/spring-plugin");
                put("Google", "https://maven.aliyun.com/repository/google");
            }}
    );
    private static final Pattern JUNIT_4_PATTERN = Pattern.compile("^4.*");
    private static final Pattern JUNIT_5_PATTERN = Pattern.compile("^5.*");

    private Project project;

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public Class<? extends Plugin<? extends Project>> getPluginClass() {
        return DevToolsPlugin.class;
    }

    @Override
    public void execute(Project project) {
        setProject(project);
        configureRepositories(project);
        configureDependencies(project);
    }

    /**
     * 配置默认仓库
     *
     * @param project Project
     */
    private void configureRepositories(Project project) {
        project.afterEvaluate(evaluated -> {
            IDevToolsExtension extension = evaluated.getExtensions().getByType(IDevToolsExtension.class);

            /* 本地仓库地址 */
            this.addMavenLocal(extension.getLocalMavenRepository());

            /* 中央仓库 */
            this.addMavenCentral();

            /* 阿里云代理的仓库服务 */
            if (extension.isAliMavenProxy()) {
                INNER_ALI_MAVEN.forEach((name, url) ->
                        extension.mavenRepository().create(name, mavenRepositories -> mavenRepositories.setUrl(url))
                );
            }

            /* 自定义配置的仓库 */
            extension.mavenRepository().all(repositoryInfo -> {
                if (project.getLogger().isDebugEnabled()) {
                    String url = repositoryInfo.toString();
                    if (StringUtils.isNotEmpty(url)) {
                        project.getLogger().debug("maven: " + url);
                    }
                }
                addMavenRepository(repositoryInfo);
            });

            /* Ali 云效 */
            extension.aliMavenRepository().all(aliYun -> {
                if (project.getLogger().isDebugEnabled()) {
                    String url = aliYun.toString();
                    if (StringUtils.isNotEmpty(url)) {
                        project.getLogger().debug("maven: " + url);
                    }
                }
                addAliYunMavenRepository(aliYun);
            });

        });
    }

    /**
     * 配置默认依赖
     *
     * @param project Project
     */
    private void configureDependencies(Project project) {
        project.afterEvaluate(evaluated -> {
            IDevToolsExtension extension = evaluated.getExtensions().getByType(IDevToolsExtension.class);
            /* lombok */
            if (extension.useLombok()) {
                this.addAnnotationProcessor(DepEnum.LOMBOK);
            }

            /* java tools */
            if (extension.useJavaTools()) {
                String tools = System.getProperty("java.home").replace("jre", "") + "/lib/tools.jar";
                this.addDependency(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME, project.files(tools));
            }

            /* Google Auto Service */
            if (extension.useAutoService()) {
                this.addAnnotationProcessor(DepEnum.GOOGLE_AUTO_SERVICE);
            }

            /* 单元测试 */
            if (extension.useJunit()) {
                String junitVersion = ExtraPropsUtils.getStringValue(project, ExtraProps.JUNIT_VERSION);
                if (JUNIT_4_PATTERN.matcher(junitVersion).matches()) {
                    this.addTestImplementation(DepEnum.JUNIT_4);
                } else if (JUNIT_5_PATTERN.matcher(junitVersion).matches()) {
                    this.addTestImplementation(DepEnum.JUNIT_5_API);
                    this.addTestRuntimeOnly(DepEnum.JUNIT_5_ENGINE);
                    project.getTasks().withType(Test.class, new Action<Test>() {
                        @Override
                        public void execute(Test test) {
                            test.useJUnitPlatform();
                        }
                    });
                }
            }

        });
    }

}
