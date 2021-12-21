import java.net.URI
import java.util.function.Predicate
import java.util.stream.Collectors

pluginManagement {
  repositories {
    gradlePluginPortal()
    maven {
      setUrl("https://repo.spring.io/plugins-release")
    }
  }

  /* 插件版本管理 */
  plugins {
    id("org.asciidoctor.jvm.convert") version "3.1.0"
    id("org.asciidoctor.jvm.convert") version "3.1.0"
    id("com.github.shalousun.smart-doc") version "2.2.2"
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
  }
}

plugins {

}

/* 根项目名称 */
rootProject.name = "spring-boot-plus"

/* 自动引入项目及模块 */
//fileTree(rootDir) {
//  include '**/*.gradle'
//  exclude '**/settings.gradle'
//  exclude '**/buildSrc'
//}.files.stream()
//        .filter(new Predicate<File>() {
//          @Override
//          boolean test(File file) {
//            String relativeName = rootDir.relativePath(file.parentFile)
//            return "" != relativeName
//          }
//        })
//        .collect(Collectors.toList())
//        .each {
////          println "${projectPath(it)} => ${projectName(it)} => ${it.parentFile}"
//          if (projectPath(it).equals(":spring-boot-starter-restful:core")) {
////            include projectPath(it)
////            project(projectPath(it)).projectDir = it.parentFile
////            project(projectPath(it)).name = projectName(it)
//          }
//        }

///**
// * 项目路径
// * @param file
// * @return String
// */
//String projectPath(File file) {
//  return ":${rootDir.relativePath(file.parentFile).replace("/", ":")}"
//}

/**
 * 项目名称
 * @param file
 * @return String
 */
//String projectName(File file) {
//  String[] paths = "${rootDir.relativePath(file.parentFile)}".split("/").reverse()
//  if (paths.length > 1) {
//    return "${paths[1]}-${paths[0]}"
//  }
//  return paths[0]
//}
