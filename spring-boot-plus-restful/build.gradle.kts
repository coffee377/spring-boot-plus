import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
  id("com.voc.devtools")
}

dependencyManagement {
  generatedPomCustomization {
//    enabled(false)
  }


  dependencies {
    dependencySet(mapOf("group" to "com.voc", "version" to "$version")) {
      entry("${name}-core")
//      entry("${name}-dingtalk")
//      entry("${name}-security")
//      entry("${name}-system")
    }

  }
}



afterEvaluate {
  components.forEach {
    println("=============> ${it.name}")
  }
}

publishing {
  publications {
    create<MavenPublication>("pom") {
      pom {
        packaging = "pom"
        name.set("spring-boot-plus-restful")
        description.set("Spring Boot Plus Restful Dependencies")
        withXml {
          val root = asNode()
        }

      }
    }

  }
}

tasks.withType<Jar> {
//  archiveExtension.set("pom")
}
java {
//  withJavadocJar()
//  withSourcesJar()
}

subprojects {
  apply(plugin = "org.springframework.boot")

  dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
  }

  publishing {
    publications {
      create<MavenPublication>("mavenJava") {
        from(components["java"])
        pom {
          packaging = "pom"
        }
      }

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



