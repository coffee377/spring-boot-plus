/* 根项目名称 */
rootProject.name = "spring-boot-plus"

//enableFeaturePreview("VERSION_CATALOGS")

//dependencyResolutionManagement {
//  versionCatalogs {
//
//    create("libs") {
//      library("spring-boot", "org.springframework.boot", "spring-boot-gradle-plugin").version("2.5.0")
//    }
//  }
//}

/* 插件管理 */
pluginManagement {
  /* 插件仓库 */
  repositories {
    gradlePluginPortal()
    maven {
      url = uri("https://repo.spring.io/plugins-release")
    }
  }

  resolutionStrategy {
    eachPlugin {
//      println(">>>>>>>>>>>> ${requested.id.id} ")
//      println(">>>>>>>>>>>> ${requested.id.namespace} ")
//      println(">>>>>>>>>>>> ${requested.id.name} ")
//      if (requested.id.namespace == "com.example") {
//        useModule("com.example:sample-plugins:1.0.0")
//      }
    }
  }

  /* 插件版本管理 */
  plugins {
    id("org.asciidoctor.jvm.convert") version "3.1.0"
    id("org.asciidoctor.jvm.convert") version "3.1.0"
    id("com.github.shalousun.smart-doc") version "2.2.2"
//    id("org.springframework.boot") version "2.3.5.RELEASE"
//    id("io.spring.dependency-management") version "1.0.11.RELEASE"
  }
}

/**
 * 获取所有项目信息
 */
val projectInfos = fileTree(rootDir) {
  val excludes = gradle.startParameter.projectProperties["excludeProjects"]?.split(",")
  include("**/*.gradle", "**/*.gradle.kts")
  exclude("build", "**/gradle", "settings.gradle", "buildSrc", "/build.gradle", ".*", "out")
}.files.stream()
  /* 非根项目 */
  .filter { file -> !file.parentFile.relativeTo(rootDir).name.isNullOrEmpty() }
  .map { file -> ProjectInfo(rootDir, file) }
  .sorted()
  .collect(java.util.stream.Collectors.toList())
  .filter { info -> !Regex(".*(examples|restful|dingtalk).*$").matches(info.name) }

projectInfos.forEach {
  include(it.path)
}

/**
 * 更新项目信息
 */
projectInfos.forEach {
  val project = project(it.dir)
  project.name = it.name
  project.projectDir = it.dir
  project.buildFileName = it.buildFileName
}

/**
 * 项目详细信息
 * @author  Wu Yujie
 * @email  coffee377@dingtalk.com
 * @time  2022/08/06 12:18
 */
class ProjectInfo(rootFile: File, buildFile: File) : Comparable<ProjectInfo> {
  /**
   * 项目所在目录
   */
  val dir: File

  /**
   * gradle build 文件名称
   */
  val buildFileName: String

  /**
   * 项目路径
   */
  val path: String

  /**
   * 项目名称
   */
  var name: String

  init {
    dir = buildFile.parentFile
    buildFileName = buildFile.name
    val isDefaultName = buildFile.name == "build.gradle" || buildFile.name == "build.gradle.kts"
    val isKotlin = buildFile.name.endsWith(".kts")
    val paths = dir.relativeTo(rootFile).path.split(File.separator)
    path = ":${paths.joinToString(":")}"
    name = if (!isDefaultName) {
      if (isKotlin) {
        buildFile.name.replace(".gradle.kts", "")
      } else {
        buildFile.name.replace(".gradle", "")
      }
    } else {
      "${rootFile.name}-${paths.joinToString("-")}"
    }
  }

  override fun compareTo(other: ProjectInfo): Int {
    return this.path.compareTo(other.path)
  }

  override fun toString(): String {
    return "Project Name\t\t-> ${name}\n" +
            "Project Path\t\t-> ${path}\n" +
            "Project Dir\t\t\t-> ${dir.absolutePath}\n" +
            "buildFileName\t\t-> ${buildFileName}\n"
  }
}
