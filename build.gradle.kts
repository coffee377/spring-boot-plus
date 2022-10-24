import com.voc.gradle.plugin.repository.VersionType

plugins {
  id("com.voc.devtools")
  id("org.jetbrains.changelog") version "1.3.1"
}

group = "com.voc"

dependencyManagement {
//    val authorizationServerVersion = ext.get("spring.security.oauth2.authorization.server.version")
//    val mybatisPlusVersion = ext.get("mybatis.plus.version")
//    val mapstructVersion = ext.get("org.mapstruct.version")
//    val openapiUIVersion = ext.get("openapi.ui.version")
//    val lombokMapstructBindingVersion = ext.get("lombok.mapstruct.binding.version")
  dependencies {
//      dependency("com.alibaba.cloud:spring-cloud-alibaba-dependencies:2021.1")
//      dependency("org.springframework.security:spring-security-oauth2-authorization-server:$authorizationServerVersion")
//      dependency("com.baomidou:mybatis-plus-boot-starter:$mybatisPlusVersion")
//      dependency("org.mapstruct:mapstruct:$mapstructVersion")
//      dependency("org.mapstruct:mapstruct-processor:$mapstructVersion")
//      dependency("org.springdoc:springdoc-openapi-ui:$openapiUIVersion")
//      dependency("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
  }
}

/* 子项目配置 */
subprojects {

  group = "com.voc"

  if (!project.name.endsWith("dependencies")) {

    apply(plugin = "com.voc.devtools")
    devtools {
      aliMavenProxy(true)
      ali {
        create("AliYun") {
          id("2038604")
          usernameFromEnvironment("DEV_OPTS_USERNAME")
          passwordFromEnvironment("DEV_OPTS_PASSWORD")
          release {
            hash("0bMxsA")
          }
          snapshot {
            hash("XNRePo")
          }
          publish(false)
        }
      }
      maven {
//      create("n3"){
//        url("http://nexus.jqk8s.jqsoft.net/repository/maven-public/")
//      }
        create("jqsoft-nexus3") {
          url(VersionType.RELEASE, "http://nexus.jqk8s.jqsoft.net/repository/maven-releases/")
          url(VersionType.SNAPSHOT, "http://nexus.jqk8s.jqsoft.net/repository/maven-snapshots/")
          publish(true)
          usernameFromEnvironment("DEV_OPTS_JQ_USERNAME")
          passwordFromEnvironment("DEV_OPTS_JQ_PASSWORD")
        }
      }
    }
  } else {
    println(project.name)
  }

  dependencies {
    /* spring boot 版本升级工具*/
//    compileOnly("org.springframework.boot:spring-boot-properties-migrator")
  }

}

tasks.wrapper {
  /* gradle wrapper 版本 */
  gradleVersion = "7.4.2"
}

