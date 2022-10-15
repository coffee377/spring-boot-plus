/* 根项目名称 */
rootProject.name = "spring-boot-plus"

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
  versionCatalogs {

    create("libs") {
      version("spring-boot", "2.5.14")
      version("dependency-management", "1.0.11.RELEASE")
      version("asciidoctor", "3.3.2")
      version("smart-doc", "2.6.0-release") // https://plugins.gradle.org/plugin/com.github.shalousun.smart-doc

      plugin("spring-boot", "org.springframework.boot").versionRef("spring-boot")
      plugin("dependency-management", "io.spring.dependency-management").versionRef("dependency-management")
      plugin("asciidoctor", "org.asciidoctor.jvm.convert").versionRef("asciidoctor")
      plugin("asciidoctor-pdf", "org.asciidoctor.jvm.pdf").versionRef("asciidoctor")
      plugin("asciidoctor-epub", "org.asciidoctor.jvm.epub").versionRef("asciidoctor")
      plugin("smart-doc", "com.github.shalousun.smart-doc").versionRef("smart-doc")
    }

    /* 钉钉 API */
    create("dingtalk") {
      /* 旧版 SDK */
      library("sdk", "com.aliyun", "alibaba-dingtalk-service-sdk").version("2.0.0")
      /* 新版 SDK */
      library("api", "com.aliyun", "dingtalk").version("1.4.52")
      bundle("sdk", listOf("sdk", "api"))
    }

    create("spring") {
      version("boot", "2.5.0")
      library("boot", "org.springframework.boot", "spring-boot-gradle-plugin").versionRef("boot")

      library("framework", "org.springframework", "spring-framework-bom").version("5.3.23")
      library("security", "org.springframework.security", "spring-security-bom").version("5.7.3")
      library(
        "authorization-server",
        "org.springframework.security",
        "spring-security-oauth2-authorization-server"
      ).version("0.3.1")

      version("openapi", "1.6.11")
      library("doc-openapi", "org.springdoc", "springdoc-openapi-ui").versionRef("openapi")
      library("doc-openapi-webflux", "org.springdoc", "springdoc-openapi-webflux-ui").versionRef("openapi")
    }

    create("tools") {
      version("mapstruct", "1.5.0.RC1")
      library("mapstruct", "org.mapstruct", "mapstruct").versionRef("mapstruct")

      library("mapstruct-processor", "org.mapstruct", "mapstruct-processor").versionRef("mapstruct")
      library("lombok", "org.projectlombok", "lombok").version("1.18.24")
      library("lombok-mapstruct", "org.projectlombok", "lombok-mapstruct-binding").version("0.2.0")

      library("servlet-api", "javax.servlet", "javax.servlet-api").version("4.0.1")

      library("junit5", "org.junit.jupiter", "junit-jupiter-api").version("5.8.2")


    }

    create("orm") {
      library("mybatis", "org.mybatis", "mybatis").version("3.5.11")
      library("mybatis-boot", "org.mybatis.spring.boot", "mybatis-spring-boot-starter").version("2.2.2")
      library("mybatis-plus-boot", "com.baomidou", "mybatis-plus-boot-starter").version("3.4.2")
    }
  }
}

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
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("org.asciidoctor.jvm.pdf") version "3.3.2"
    id("com.github.shalousun.smart-doc") version "2.2.2"
//    id("org.springframework.boot") version "2.5.0.RELEASE"
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
  .filter { info -> !Regex(".*(examples|restful).*$").matches(info.name) }

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
