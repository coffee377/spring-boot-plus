import com.voc.gradle.plugin.core.DevType

group = "com.voc.restful"
description = "Spring Boot Plus Restful Dependencies"

plugins {
  id("com.voc.devtools")
}

dependencyManagement {
  dependencies {
    dependencySet("$group:$version") {
      entry("${name}-core")
      entry("${name}-security")
    }
  }
}

devtools {
  type(DevType.BOM)
}

subprojects {
  group = "com.voc.boot.restful"

  apply(plugin = "com.voc.boot")

  dependencyManagement {
    val authorizationServerVersion = ext.get("spring.security.oauth2.authorization.server.version")
    val mybatisPlusVersion = ext.get("mybatis.plus.version")
    val mapstructVersion = ext.get("org.mapstruct.version")
    val openapiUIVersion = ext.get("openapi.ui.version")
    val lombokMapstructBindingVersion = ext.get("lombok.mapstruct.binding.version")
    dependencies {
      dependency("org.springframework.security:spring-security-oauth2-authorization-server:$authorizationServerVersion")
      dependency("com.baomidou:mybatis-plus-boot-starter:$mybatisPlusVersion")
      dependency("org.mapstruct:mapstruct:$mapstructVersion")
      dependency("org.mapstruct:mapstruct-processor:$mapstructVersion")
      dependency("org.springdoc:springdoc-openapi-ui:$openapiUIVersion")
      dependency("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
    }
  }

  dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
  }

}



