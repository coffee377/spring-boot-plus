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

//fileTree(rootDir) {
//  include("**/*.gradle", "**/*.gradle.kts")
//  exclude("**/settings.gradle", "**/settings.gradle.kts", "**/buildSrc")
//}.files.stream()
//  /* 非根项目 */
//  .filter { file -> !file.parentFile.relativeTo(rootDir).name.isNullOrEmpty() }
//  .map { file -> ProjectInfo(rootDir, file) }
//  .collect(java.util.stream.Collectors.toList())
////  .filter { info -> Regex(".*(examples|restful)$").matches(info.name) }
//  .forEach {
////    println("====> $it")
////    include(it.path)
////    project(it.path).projectDir = it.dir
////    project(it.path).name = it.name
//  }

//include(":examples")
//include(":examples:gradle")
include(":restful")
include(":restful:restful-core")
include(":restful:restful-authorization-server")
include(":restful:restful-resource-server")
include(":restful:restful-dingtalk")
include(":restful:restful-system")


//val projectReg = arrayListOf(".*(examples|restful)$", "restful-(core|dingtalk)$")
//
///**
// * 项目详细实体类
// */
//private class ProjectInfo(rootFile: File, buildFile: File) {
//  val dir: File
//  val path: String
//  val name: String
//
//  init {
//    dir = buildFile.parentFile
//    val paths = dir.relativeTo(rootFile).path.split(File.separator)
//    path = ":${java.lang.String.join(":", paths)}"
//    name = "${rootFile.name}-${java.lang.String.join("-", paths)}"
//  }
//
//  override fun toString(): String {
//    return "Project Name\t-> [${name}]\nProject Path\t-> [${path}]\nProject Dir\t\t-> [${dir.absolutePath}]\n"
//  }
//}
include("microservice")
