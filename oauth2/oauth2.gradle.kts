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
  group = "com.voc.oauth"

  apply(plugin = "com.voc.boot")

  dependencies {
    implementation(enforcedPlatform(project(":spring-plus-dependencies")))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    /* todo 这里有问题，不传递版本号，无法解析到版本号，不知道是哪里导致的问题 */
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

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
