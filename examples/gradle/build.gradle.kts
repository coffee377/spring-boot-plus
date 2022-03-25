plugins {
  id("com.voc.devtools")
  id("com.voc.boot")
}

val restful = "spring-boot-plus-restful"

println("gradle examples version => $version")

dependencyManagement {
  resolutionStrategy {
    cacheChangingModulesFor(0, TimeUnit.SECONDS)
  }

  imports {
    mavenBom("com.voc:spring-boot-plus-restful:$version")
  }


}

devtools {
  type(com.voc.gradle.plugin.core.DevType.APP)
}

repositories {
}

dependencies {
//  implementation(project(":$restful:$restful-core"))
  implementation("com.voc:spring-boot-plus-restful-core")
  implementation("org.springframework.boot:spring-boot-starter-web")

  annotationProcessor("org.projectlombok:lombok")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
}

configurations {
  all {
    resolutionStrategy {
      eachDependency {
        /* 对于本地项目，DependencyManagementPlugin 版本解析不生效，所以此处手动配置 */
        /* local project dependency. dependency management has not been applied */
        if (requested.group == "com.voc" && requested.name.startsWith(restful)) {
          useVersion("$version")
        }
      }
    }
  }
}
