import com.voc.gradle.plugin.core.DevType

plugins {
  id("com.voc.devtools")
}

group = "com.voc.boot"
description = "Spring Boot Plus Starter Dependencies"

devtools {
  type(DevType.BOM)
}

dependencyManagement {
  dependencies {
    dependencySet("$group:$version") {
      subprojects.forEach {
        entry(it.name)
      }
    }
  }
}

subprojects {
  group = "com.voc.boot"

  apply(plugin = "com.voc.boot")

  dependencyManagement {
    val authorizationServerVersion = ext.get("spring.security.oauth2.authorization.server.version")
    val mybatisPlusVersion = ext.get("mybatis.plus.version")
//    val mapstructVersion = ext.get("org.mapstruct.version")
//    val openapiUIVersion = ext.get("openapi.ui.version")
//    val lombokMapstructBindingVersion = ext.get("lombok.mapstruct.binding.version")
    dependencies {
      dependency("org.springframework.security:spring-security-oauth2-authorization-server:$authorizationServerVersion")
      dependency("com.baomidou:mybatis-plus-boot-starter:$mybatisPlusVersion")
//      dependency("org.mapstruct:mapstruct:$mapstructVersion")
//      dependency("org.mapstruct:mapstruct-processor:$mapstructVersion")
//      dependency("org.springdoc:springdoc-openapi-ui:$openapiUIVersion")
//      dependency("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
    }
  }

  dependencies {
    compileOnly("javax.servlet:javax.servlet-api")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
  }

  tasks {
    test {
      useJUnitPlatform()
    }
  }
}
