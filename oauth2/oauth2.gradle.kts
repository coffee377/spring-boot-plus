group = "com.voc.oauth"
description = "OAuth2 Dependencies"

devtools {
  type(com.voc.gradle.plugin.core.DevType.BOM)
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
    val mapstructVersion = ext.get("org.mapstruct.version")
    val openapiUIVersion = ext.get("openapi.ui.version")
    val lombokMapstructBindingVersion = ext.get("lombok.mapstruct.binding.version")
    dependencies {
//      dependency("org.springframework.security:spring-security-oauth2-authorization-server:$authorizationServerVersion")
//      dependency("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
      dependency("com.baomidou:mybatis-plus-boot-starter:3.5.2")
      dependency("org.mapstruct:mapstruct:$mapstructVersion")
      dependency("org.mapstruct:mapstruct-processor:$mapstructVersion")
      dependency("org.springdoc:springdoc-openapi-ui:$openapiUIVersion")
      dependency("org.projectlombok:lombok-mapstruct-binding:$lombokMapstructBindingVersion")
    }
  }

  dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
//      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    compileOnly("javax.servlet:javax.servlet-api")
  }

  tasks {
    test {
      useJUnitPlatform()
    }
  }
}
