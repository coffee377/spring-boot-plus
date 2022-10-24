/* 根项目名称 */
rootProject.name = "spring-boot-plus"

/* 插件管理 */
pluginManagement {
  /* 插件仓库 */
  repositories {
    maven { url = uri(file("D:\\Project\\personal\\gradle-devtools\\build\\publications\\repos")) }
    gradlePluginPortal()
    maven { url = uri("https://repo.spring.io/plugins-release") }
  }

  /* 插件版本管理 */
  plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("org.asciidoctor.jvm.pdf") version "3.3.2"
    id("org.asciidoctor.jvm.epub") version "3.3.2"
    id("com.github.shalousun.smart-doc") version "2.6.0-release"

    id("com.voc.auto") version "0.1.0-SNAPSHOT"
//    id("org.springframework.boot") version "2.5.0.RELEASE"
//    id("io.spring.dependency-management") version "1.0.11.RELEASE"
  }
}

plugins {
  id("com.voc.auto")
}

dependencyResolutionManagement {
//  defaultLibrariesExtensionName.set("voc")

  versionCatalogs {

    /* 钉钉 API */
    create("dingtalkLibs") {
      /* 旧版 SDK */
      library("sdk", "com.aliyun", "alibaba-dingtalk-service-sdk").version("2.0.0")
      /* 新版 SDK */
      library("api", "com.aliyun", "dingtalk").version("1.4.52")
      bundle("sdk", listOf("sdk", "api"))
    }

    create("springLibs") {
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

    create("toolsLibs") {
      version("mapstruct", "1.5.0.RC1")
      library("mapstruct", "org.mapstruct", "mapstruct").versionRef("mapstruct")

      library("mapstruct-processor", "org.mapstruct", "mapstruct-processor").versionRef("mapstruct")
      library("lombok", "org.projectlombok", "lombok").version("1.18.24")
      library("lombok-mapstruct", "org.projectlombok", "lombok-mapstruct-binding").version("0.2.0")

      library("servlet-api", "javax.servlet", "javax.servlet-api").version("4.0.1")

      library("junit5", "org.junit.jupiter", "junit-jupiter-api").version("5.8.2")

    }

    create("ormLibs") {
      library("mybatis", "org.mybatis", "mybatis").version("3.5.11")
      library("mybatis-boot", "org.mybatis.spring.boot", "mybatis-spring-boot-starter").version("2.2.2")
      library("mybatis-plus-boot", "com.baomidou", "mybatis-plus-boot-starter").version("3.4.2")
    }
  }
}

