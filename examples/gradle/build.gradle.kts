plugins {
  id("org.springframework.boot")
  id("com.voc.devtools")
}

val restful = "spring-boot-plus-restful"

dependencyManagement {
  imports {
    mavenBom("com.voc:spring-boot-plus-restful:$version")
  }

  dependencies {
    dependencySet("com.voc:$version") {
      entry("${restful}-core")
//      entry("${name}-dingtalk")
//      entry("${name}-security")
//      entry("${name}-system")
    }

  }

}

devtools {
//  isLombok = true
//  isJunit = true
//  ali {
//    create("VOC") {
//      id = "2038604"
//      username = "5f4ba059fa82bfeb805a1e09"
//      password = "a3XkZLNApybs"
//      release {
//        hash = "0bMxsA"
//      }
//      snapshot {
//        hash = "XNRePo"
//      }
//      isAllowPublish = true
//    }
//  }
}

repositories {
}

dependencies {
//  implementation(project(":$restful:$restful-core"))
//  implementation("com.voc:spring-boot-plus-restful-core")
  implementation("org.springframework.boot:spring-boot-starter-web")
//  implementation("org.springframework.boot:spring-boot-starter-web")
//  implementation("org.springframework.boot:spring-boot-starter-cache")
//  implementation("org.springframework.boot:spring-boot-starter-actuator")

  /* caffeine 缓存依赖 */
//  compileOnly("com.github.ben-manes.caffeine:caffeine")
//  /* redis 缓存依赖 */
//  compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
//  compileOnly("org.springframework.boot:spring-boot-starter-data-mongodb")
//  compileOnly("com.google.code.gson:gson")
//  compileOnly("org.springframework.boot:spring-boot-starter-security")
//  implementation("org.springframework.boot:spring-boot-starter-webflux")
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
