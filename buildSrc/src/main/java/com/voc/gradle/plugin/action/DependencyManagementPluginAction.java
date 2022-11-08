package com.voc.gradle.plugin.action;

import com.voc.gradle.plugin.api.IPluginAction;
import io.spring.gradle.dependencymanagement.DependencyManagementPlugin;
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.DependencyResolveDetails;
import org.gradle.api.artifacts.ModuleVersionSelector;
import org.gradle.api.artifacts.ResolutionStrategy;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2022/01/01 16:52
 */
public class DependencyManagementPluginAction implements IPluginAction {
    @Override
    public Class<? extends Plugin<? extends Project>> getPluginClass() {
        return DependencyManagementPlugin.class;
    }

    @Override
    public void execute(Project project) {
        project.afterEvaluate(evaluated -> {
            DependencyManagementExtension managementExtension = project.getExtensions().getByType(DependencyManagementExtension.class);
            project.getConfigurations().all(configuration-> {
                configuration.resolutionStrategy(new Action<ResolutionStrategy>() {
                    @Override
                    public void execute(ResolutionStrategy resolutionStrategy) {
//                        resolutionStrategy.eachDependency(new Action<DependencyResolveDetails>() {
//                            @Override
//                            public void execute(DependencyResolveDetails details) {
//                                ModuleVersionSelector requested = details.getRequested();
//                                String group = requested.getGroup();
//                                String name = requested.getName();
//                                String version = requested.getVersion();
////                                details.useVersion("");
//                            }
//                        });
//                        Map<String, String> managedVersions= managementExtension.getManagedVersionsForConfigurationHierarchy(configuration);
//                        System.err.println(">>>>>>>> " + managedVersions.size());
                    }
                });
            });
//            int size = managementExtension.getManagedVersions().size();

        });
//        DependencyManagementConfigurationContainer configurationContainer =
//                new DependencyManagementConfigurationContainer(project);
//        MavenPomResolver pomResolver = new MavenPomResolver(project, configurationContainer);
//        DependencyManagementContainer dependencyManagementContainer = new DependencyManagementContainer(project, pomResolver);

//        project.afterEvaluate(new Action<Project>() {
//            @Override
//            public void execute(Project project) {
//                DependencyManagementExtension managementExtension = project.getExtensions().getByType(DependencyManagementExtension.class);
//
//                project.getConfigurations().all(configuration -> {
////                    configuration.getIncoming().beforeResolve(new Action<ResolvableDependencies>() {
////                        @Override
////                        public void execute(ResolvableDependencies resolvableDependencies) {
////                            for (Dependency dependency : resolvableDependencies.getDependencies()) {
////                                if (Objects.equals(dependency.getGroup(), "com.voc")) {
////                                    System.out.println(dependency);
////                                }
////                            }
////
////                        }
////                    });
//                    Map<String, String> managedVersions =
//                            managementExtension.getManagedVersionsForConfigurationHierarchy(configuration);
//                    Map<String, String> managedVersions2 =
//                            managementExtension.getManagedVersionsForConfiguration(configuration);
//                    Map<String, String> managedVersions3 =
//                            managementExtension.getManagedVersions();
//                    VersionResolutionAction versionResolutionAction = new VersionResolutionAction(project, managedVersions);
//                    configuration.resolutionStrategy(
//                            resolutionStrategy -> resolutionStrategy.eachDependency(versionResolutionAction)
//                    );
//
//                });
//            }
//        });


    }
}
