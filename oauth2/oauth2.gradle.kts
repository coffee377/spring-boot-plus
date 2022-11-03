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
