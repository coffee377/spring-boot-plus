enableFeaturePreview("VERSION_CATALOGS")
val props = Props()
val springBootVersion = props["org.springframework.boot.version", "2.3.5.RELEASE"]
val dependencyManagementVersion = props["io.spring.dependency-management.version", "1.0.10.RELEASE"]
val lombokVersion = props["lombok.version", "1.18.24"]
val junitJupiterVersion = props["junit.jupiter.version", "5.7.2"]
dependencyResolutionManagement {

  versionCatalogs {
    create("libs") {
      library("spring-boot", "org.springframework.boot", "spring-boot-gradle-plugin").version(springBootVersion)
      library(
        "dependency-management",
        "io.spring.gradle",
        "dependency-management-plugin"
      ).version(dependencyManagementVersion)

      library("lombok", "org.projectlombok", "lombok").version(lombokVersion)
      library("junit-jupiter-api", "org.junit.jupiter", "junit-jupiter-api").version(junitJupiterVersion)
      library("junit-jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine").version(junitJupiterVersion)
      library("semantic-version", "de.skuzzle", "semantic-version").version("2.1.1")
    }
  }

}

class Props {
  private val properties: java.util.Properties = java.util.Properties()

  init {
    val root = rootDir.parentFile.absolutePath
    val file = file("${root}/gradle.properties")
    this.properties.load(java.io.FileInputStream(file))
  }

  operator fun get(name: String, defaultValue: String): String {
    val value = properties.getProperty(name)
    if (!value.isNullOrEmpty()) {
      return value
    }
    return defaultValue
  }
}
