import com.voc.gradle.plugin.repository.VersionType

plugins {
  id("com.voc.devtools")
  id("org.jetbrains.changelog") version "1.3.1"
}

group = "com.voc"

allprojects {
  extra["jackson-bom.version"] = "2.15.3"
}

/* 子项目配置 */
subprojects {

  group = "com.voc"
  apply<MavenPublishPlugin>()

  if (!project.name.endsWith("dependencies")) {

    apply(plugin = "com.voc.devtools")
    devtools {
      aliMavenProxy(true)
      ali {
        create("AliYun") {
          id("2038604")
          usernameFromEnvironment("DEV_OPTS_USERNAME")
          passwordFromEnvironment("DEV_OPTS_PASSWORD")
          release {
            hash("0bMxsA")
          }
          snapshot {
            hash("XNRePo")
          }
          publish(false)
        }
      }
      maven {
        create("nexus3") {
          url("http://nexus.jqk8s.jqsoft.net/repository/maven-public/")
        }
        create("JinQiSoftNexus3") {
          url(VersionType.RELEASE, "http://nexus.jqk8s.jqsoft.net/repository/maven-releases/")
          url(VersionType.SNAPSHOT, "http://nexus.jqk8s.jqsoft.net/repository/maven-snapshots/")
          publish(true)
          usernameFromEnvironment("DEV_OPTS_NEXUS_USERNAME")
          passwordFromEnvironment("DEV_OPTS_NEXUS_PASSWORD")
        }
      }
    }
  } else {
    println(project.name)
  }

  publishing {
    repositories {
      maven {
        name = "local"
        url = uri("${rootProject.buildDir}/publications/repos")
      }
    }
  }

  dependencies {
    // implementation(platform(project(":spring-boot-plus-dependencies")))
    /* spring boot 版本升级工具*/
    // compileOnly("org.springframework.boot:spring-boot-properties-migrator")
  }

}

tasks.wrapper {
  /* gradle wrapper 版本 */
  gradleVersion = "8.6"
}

