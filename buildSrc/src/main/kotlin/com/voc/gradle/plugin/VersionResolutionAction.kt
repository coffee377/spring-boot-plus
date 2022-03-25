package com.voc.gradle.plugin

import com.voc.gradle.plugin.api.IProject
import com.voc.gradle.plugin.api.ProjectBase
import lombok.extern.slf4j.Slf4j
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.DependencyResolveDetails
import java.util.stream.Collectors

/**
 * @author  Wu Yujie
 * @email  coffee377@dingtalk.com
 * @time  2022/01/01 17:06
 */
@Slf4j
class VersionResolutionAction constructor(
  project: Project,
  private val managedVersions: Map<String, String>
) :
  ProjectBase(project), Action<DependencyResolveDetails>, IProject {
  private var localProjectNames: Set<String>? = null

  override fun execute(details: DependencyResolveDetails) {
    if (isDependencyOnLocalProject(project, details) && hasManagedVersion(details)) {
      getManagedVersion(details)?.let {
        details.useVersion(it)
      }
    }
  }

  private fun getManagedVersion(details: DependencyResolveDetails): String? {
    return managedVersions["${details.requested.group}:${details.requested.name}"]
  }

  /**
   * 是否有管理的版本
   */
  private fun hasManagedVersion(details: DependencyResolveDetails): Boolean {
    return managedVersions.containsKey("${details.requested.group}:${details.requested.name}")
  }

  /**
   * 是否本地项目依赖
   *
   * @param project Project
   * @param details details
   * @return boolean
   */
  private fun isDependencyOnLocalProject(
    project: Project,
    details: DependencyResolveDetails
  ): Boolean {
    if (localProjectNames == null) {
      localProjectNames = project.rootProject.allprojects
        .stream().map { p: Project -> "${p.group}:${p.name}" }.collect(Collectors.toSet())
    }
    return localProjectNames!!.contains("${details.requested.group}:${details.requested.name}")
  }
}
