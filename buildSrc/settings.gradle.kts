dependencyResolutionManagement {

  versionCatalogs {
    create("libs") {
      from(files("../gradle/libs.versions.toml"))
    }
  }

}

/* 插件管理 */
pluginManagement {

  /* 插件版本管理 */
  plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.gradle.kotlin.kotlin-dsl") version "2.4.1"
  }

}
