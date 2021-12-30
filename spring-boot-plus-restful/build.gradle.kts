import org.springframework.boot.gradle.tasks.bundling.BootJar
import com.voc.gradle.plugin.core.DevType

description = "Spring Boot Plus Restful Dependencies"

plugins {
  id("com.voc.devtools")
}

dependencyManagement {
  dependencies {
    dependencySet("com.voc:$version") {
      entry("${name}-core")
    }
  }
}

allprojects {
  apply(plugin = "com.voc.devtools")

  devtools {
    aliMavenProxy(true)
    ali {
      create("AliYun") {
        id("2038604")
        username("5f4ba059fa82bfeb805a1e09")
        password("a3XkZLNApybs")
        release {
          hash("0bMxsA")
        }
        snapshot {
          hash("XNRePo")
        }
        publish(true)
      }
    }
  }
}

devtools {
  type(DevType.BOM)
}

subprojects {
  apply(plugin = "org.springframework.boot")

  dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
  }

  tasks {
    withType<Jar> {
      enabled = true
    }

    withType<BootJar> {
      enabled = false
    }
  }
}



