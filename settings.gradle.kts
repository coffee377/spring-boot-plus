/* 根项目名称 */
rootProject.name = "spring-boot-plus"

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

val buildFiles = fileTree(rootDir) {
  val excludes = gradle.startParameter.projectProperties["excludeProjects"]?.split(",")
  include("**/*.gradle", "**/*.gradle.kts")
//  exclude("build", "**/gradle", "**/settings.gradle", "**/settings.gradle.kts", "**/buildSrc")
  exclude("build", "**/gradle", "settings.gradle", "buildSrc", "/build.gradle", ".*", "out")
//  if (excludes) {
//    exclude(excludes)
//  }
}

fileTree(rootDir) {
  include("**/*.gradle", "**/*.gradle.kts")
//  exclude("**/settings.gradle", "**/settings.gradle.kts", "**/buildSrc")
  exclude("build", "**/gradle", "settings.gradle", "buildSrc", "/build.gradle", ".*", "out")
}.files.stream()
  /* 非根项目 */
  .filter { file -> !file.parentFile.relativeTo(rootDir).name.isNullOrEmpty() }
  .map { file -> ProjectInfo(rootDir, file) }
  .collect(java.util.stream.Collectors.toList())
//  .filter { info -> Regex(".*(examples|restful)$").matches(info.name) }
  .forEach {
//    println("$it")
    include(it.path)
    if (!it.isDefaultName) {
      val project = findProject(it.path)
      project?.projectDir = it.dir
      project?.name = it.name
      project?.buildFileName = it.buildFileName
//      project(it.path).projectDir = it.dir
//      project(it.path).name = it.name
    }

  }

//include(":examples")
//include(":examples:gradle")
//include(":restful")
//include(":restful:restful-core")
//include(":restful:restful-authorization-server")
//include(":restful:restful-resource-server")
//include(":restful:restful-system")


//val projectReg = arrayListOf(".*(examples|restful)$", "restful-(core|dingtalk)$")
//
/**
 * 项目详细实体类
 */
private class ProjectInfo(rootFile: File, buildFile: File) {
  val dir: File
  val buildFileName: String
  val path: String
  var name: String
  val isDefaultName: Boolean
  val isKotlin: Boolean

  init {
    dir = buildFile.parentFile
    buildFileName = buildFile.name
    isDefaultName = buildFile.name == "build.gradle" || buildFile.name == "build.gradle.kts"
    isKotlin = buildFile.name.endsWith(".kts")
    val paths = dir.relativeTo(rootFile).path.split(File.separator)
    path = ":${java.lang.String.join(":", paths)}"
    name = if (!isDefaultName) {
      if (isKotlin) {
        buildFile.name.replace(".gradle.kts", "")
      } else {
        buildFile.name.replace(".gradle", "")
      }
    } else {
      "${rootFile.name}-${java.lang.String.join("-", paths)}"
    }
  }

  override fun toString(): String {
    return "Project Name\t-> [${name}]\n" +
            "Project Path\t-> [${path}]\n" +
            "Project Dir\t\t-> [${dir.absolutePath}]\n" +
            "buildFileName\t\t-> [${buildFileName}]\n" +
            "isDefaultName \t [${isDefaultName}]\n" +
            "isKotlin \t [${isKotlin}]\n"
  }
}

