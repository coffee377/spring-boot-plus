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

  }
}

plugins {
  id("io.github.coffee377.auto-include")
}

configure<AutoIncludeProjectExtension> {
  exclude(".*persist$")
//  exclude("oauth2-authorization-server")
  exclude("^restful.*")
}

