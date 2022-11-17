import com.voc.gradle.plugin.core.DevType

plugins {
//  id("com.voc.devtools")
//  `java-platform`
}

group = "com.voc.boot"
description = "Spring Plus Starter Dependencies"

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

dependencies {
  implementation(enforcedPlatform(project(":spring-plus-dependencies")))
  constraints {

  }
}

subprojects {
  group = "com.voc.boot"

  apply(plugin = "com.voc.boot")

  dependencies {
    compileOnly("javax.servlet:javax.servlet-api")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

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
