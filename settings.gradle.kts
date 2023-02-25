import io.github.coffee377.gradle.plugin.extensions.AutoIncludeProjectExtension

/* 根项目名称 */
rootProject.name = "spring-plus"

pluginManagement {
  repositories {
    maven {
      url = uri("http://nexus.jqk8s.jqsoft.net/repository/maven-public")
      isAllowInsecureProtocol = true
    }
    gradlePluginPortal()
    maven { url = uri("https://repo.spring.io/plugins-release") }
  }

  plugins {
    id("io.github.coffee377.auto-include") version "0.2.0-alpha"
  }
}

dependencyResolutionManagement {
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

plugins {
  id("io.github.coffee377.auto-include")
}

configure<AutoIncludeProjectExtension> {
  exclude(".*persist$")
  exclude("^restful.*")
}

