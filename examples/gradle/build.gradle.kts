plugins {
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  id("com.voc.app.info")
//  id("com.voc.devtools")
}

val restful = "spring-boot-plus-restful"

dependencyManagement {
  imports {
    mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
//    mavenBom("com.voc:spring-boot-plus-restful:0.0.1")
  }

  dependencies {
    dependencySet(mapOf("group" to "com.voc", "version" to "0.0.1")) {
      entry("${restful}-core")
//      entry("${name}-dingtalk")
//      entry("${name}-security")
//      entry("${name}-system")
    }

  }

}


repositories {
//  mavenLocal()
//  maven{
//    url = uri("https://packages.aliyun.com/maven/repository/2038604-release-0bMxsA/")
//    credentials {
//      username = "5f4ba059fa82bfeb805a1e09"
//      password = "a3XkZLNApybs"
//    }
//  }
}

dependencies {
  constraints {

  }
//  implementation(project(":$restful:$restful-core"))
  implementation("com.voc:spring-boot-plus-restful-core:0.0.1-SNAPSHOT")
  implementation("org.springframework.boot:spring-boot-starter-web")
//  implementation project(":$restful:$restful-dingtalk")
//  implementation project(":$restful:$restful-system")
//  implementation project(":$restful:$restful-security")
//  compileOnly 'com.google.code.gson:gson:2.8.6'
//  implementation 'com.github.ben-manes.caffeine:caffeine'
//  implementation("org.springframework.boot:spring-boot-starter-data-redis")
//  testImplementation("org.springframework.boot:spring-boot-starter-test") {
//    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
//  }
}

tasks {
  withType<Jar> {
    enabled = false
  }

  withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = true
  }
}
