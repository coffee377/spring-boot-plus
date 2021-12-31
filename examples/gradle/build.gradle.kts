plugins {
  id("com.voc.devtools")
  id("com.voc.boot")
}

val restful = "spring-boot-plus-restful"

dependencyManagement {
  imports {
    mavenBom("com.voc:spring-boot-plus-restful:$version")
  }

//  dependencies {
//    dependencySet("com.voc:$version") {
//      entry("${restful}-core")
////      entry("${name}-dingtalk")
////      entry("${name}-security")
////      entry("${name}-system")
//    }
//
//  }

}

devtools {
  type(com.voc.gradle.plugin.core.DevType.APP)
}

repositories {
}

dependencies {
//  implementation(project(":$restful:$restful-core"))
  implementation("com.voc:spring-boot-plus-restful-core:0.0.3-SNAPSHOT")
  implementation("org.springframework.boot:spring-boot-starter-web")
//  implementation("org.springframework.boot:spring-boot-starter-web")
//  implementation("org.springframework.boot:spring-boot-starter-cache")
//  implementation("org.springframework.boot:spring-boot-starter-actuator")

  annotationProcessor("org.projectlombok:lombok")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
}

//tasks {
//  withType<Jar> {
//    enabled = false
//  }
//
//  withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
//    enabled = true
//  }
//}
