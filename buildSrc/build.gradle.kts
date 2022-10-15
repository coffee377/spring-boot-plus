import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `java-library`
  `java-gradle-plugin`
  `embedded-kotlin`
}

configurations {

  val annotationProcessor = configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
  val testAnnotationProcessor = configurations.getByName(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
  val compileOnly = configurations.getByName(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME)
  val runtimeOnly = configurations.getByName(JavaPlugin.RUNTIME_ONLY_CONFIGURATION_NAME)
  val runtimeClasspath = configurations.getByName(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME)
  val testImplementation = configurations.getByName(JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME)

  testAnnotationProcessor {
    extendsFrom(annotationProcessor)
  }

  compileOnly {
    extendsFrom(annotationProcessor)
  }

  testCompileOnly {
    extendsFrom(compileOnly, testAnnotationProcessor)
  }

  runtimeClasspath {
    extendsFrom(annotationProcessor)
  }

  testRuntimeOnly {
    extendsFrom(runtimeOnly)
  }

  all {
    resolutionStrategy {
      cacheDynamicVersionsFor(10, TimeUnit.MINUTES)
      cacheChangingModulesFor(10, TimeUnit.SECONDS)
    }
  }

}

repositories {
  /* Local */
  mavenLocal()
  /* Ali Yun central仓和jcenter仓的聚合仓 */
  maven {
    url = uri("https://maven.aliyun.com/repository/public/")
  }
}

dependencies {
  implementation(libs.spring.boot)
  implementation(libs.dependency.management)
  implementation(libs.semantic.version)

  annotationProcessor(libs.lombok)

  testImplementation(libs.junit.jupiter.api)
  testRuntimeOnly(libs.junit.jupiter.engine)
}

tasks {
  test {
    useJUnitPlatform()
  }

  withType<Jar> {
    archiveBaseName.set("gradle-plugin")
    /* 重复文件策略，排除 */
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
  }

  withType<JavaCompile> {
    options.release.set(8)
  }

  withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "11"
  }

  register("lib", Copy::class.java) {
    group = "build"
    from(configurations.runtimeClasspath)
    into("${buildDir.path}/lib")
  }
}

gradlePlugin {
  plugins {
    create("devtools") {
      id = "com.voc.devtools"
      implementationClass = "com.voc.gradle.plugin.DevToolsPlugin"
    }
    create("app-info") {
      id = "com.voc.app.info"
      implementationClass = "com.voc.gradle.plugin.AppInfoPlugin"
    }
    create("boot") {
      id = "com.voc.boot"
      implementationClass = "com.voc.gradle.plugin.BootPlugin"
    }
  }

}
