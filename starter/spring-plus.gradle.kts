import com.voc.gradle.plugin.core.DevType

plugins {
//  id("com.voc.devtools")
//  `java-platform`
}

group = "com.voc.boot"
description = "Spring Plus Dependencies"

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

  dependencies {
    /* 统一引入依赖版本 */
    implementation(enforcedPlatform(project(":spring-plus-dependencies")))

    compileOnly("javax.servlet:javax.servlet-api")
//    compileOnly("jakarta.servlet:jakarta.servlet-api")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
//      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("javax.servlet:javax.servlet-api")
    testImplementation("org.springframework.boot:spring-boot-starter-web")
  }

  tasks {
    test {
      useJUnitPlatform()
    }
  }
}
