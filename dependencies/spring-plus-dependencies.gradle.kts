import com.voc.gradle.plugin.core.DevType

plugins {
  `java-platform`
  `maven-publish`
}

javaPlatform {
  allowDependencies()
}

val common = "common"
val plus = "spring-plus"

repositories {
  maven {
    url = uri("http://nexus.jqk8s.jqsoft.net/repository/maven-public/")
    isAllowInsecureProtocol = true
  }
}

repositories {
  maven {
    url = uri("http://nexus.jqk8s.jqsoft.net/repository/maven-public/")
    isAllowInsecureProtocol = true
  }
}

/* 依赖管理 */
dependencies {
  /* Spring Boot */
  api(platform(libs.spring.boot.dependencies))
  /* Spring Cloud */
  api(platform(libs.spring.cloud.dependencies))
  /* Spring Cloud Alibaba */
  api(platform(libs.spring.cloud.alibaba.dependencies))
  /* Spring Security */
  api(platform(libs.spring.security.bom))

  constraints {
    /* 授权服务器依赖 */
    api(libs.spring.security.oauth2.authorization.server)
    api(libs.bundles.dingtalk)

    api(libs.spring.doc.openapi)
    api(libs.gitlab)

    api(libs.mapstruct)
    api(libs.mapstruct.processor)
    api(libs.lombok.mapstruct.binding)

    api(libs.mybatis.boot)
    api(libs.mybatis.plus.boot)

    api("e-iceblue:spire.xls.free:5.1.0")
    api("com.baomidou:mybatis-plus-boot-starter:3.5.2")

    /* projects */
    api(project(":$common:$common-api"))
    api(project(":$plus:$plus-cache"))
    api(project(":$plus:$plus-dict"))
    api(project(":$plus:$plus-dingtalk"))
    api(project(":$plus:$plus-result"))
  }
}

publishing {
  repositories {
    maven {
      name = "JinQiSoftNexus3"
      val versionType = if (project.version.toString().endsWith("SNAPSHOT", true)) "snapshots" else "releases"
      url = uri("http://nexus.jqk8s.jqsoft.net/repository/maven-${versionType}/")
      isAllowInsecureProtocol = true
      credentials {
        username = System.getenv("DEV_OPTS_NEXUS_USERNAME")
        password = System.getenv("DEV_OPTS_NEXUS_PASSWORD")
      }
    }
  }
  publications {
    create<MavenPublication>("Bom") {
      from(components["javaPlatform"])
    }
  }
}

