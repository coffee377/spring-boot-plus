plugins {
  `java-platform`
  `maven-publish`

  id("io.spring.dependency-management")
}

javaPlatform {
  allowDependencies()
}

val common = "common"
val starter = "spring-plus"

repositories {
  maven {
    url = uri("http://nexus.jqk8s.jqsoft.net/repository/maven-public/")
    isAllowInsecureProtocol = true
  }
}

dependencyManagement {
//  imports {
//
//    mavenBom(libs.spring.boot.dependencies.get().toString())
////    mavenBom("org.springframework.boot:spring-boot-dependencies:2.5.14")
//  }
  generatedPomCustomization {
    enabled(false)
  }
//
////  pomConfigurer.configurePom()
}

/* 依赖管理 */
dependencies {
  /* Spring Boot */
  api(platform(libs.spring.boot.dependencies))
  /* Spring Cloud */
  api(platform(libs.spring.cloud.dependencies))
  /* Spring Cloud Alibaba */
  api(platform(libs.spring.cloud.alibaba.dependencies))

  api(platform(libs.spring.security.bom))

  constraints {
    /* 授权服务器依赖 */
    api(libs.spring.security.oauth2.authorization.server)
    api(libs.bundles.dingtalk)

    api("com.baomidou:mybatis-plus-boot-starter:3.5.2")
    api("org.mapstruct:mapstruct:1.5.0.RC1")
    api("org.mapstruct:mapstruct-processor:1.5.0.RC1")
    api("org.projectlombok:lombok-mapstruct-binding:0.2.0")
//    api("org.gitlab4j:gitlab4j-api:5.0.1")
//    api("org.springdoc:springdoc-openapi-ui:1.6.11")
    api(libs.spring.doc.openapi)
    api(libs.gitlab)

    api(toolsLibs.mapstruct)
    api(toolsLibs.mapstruct.processor)
    api(toolsLibs.lombok.mapstruct)

    /* projects */
    api(project(":$common:$common-api"))
    api(project(":$starter:$starter-cache"))
    api(project(":$starter:$starter-dict"))
    api(project(":$starter:$starter-dingtalk"))
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
        username = System.getenv("DEV_OPTS_JQ_USERNAME")
        password = System.getenv("DEV_OPTS_JQ_PASSWORD")
      }
    }
  }
  publications {
    create<MavenPublication>("Bom") {
      from(components["javaPlatform"])
    }
  }
}

