buildscript {
}

plugins {
  `java-library`
  `java-gradle-plugin`
}

group = "com.voc"
version = "0.0.1"

repositories {
  /* Local */
  mavenLocal()
  /* Ali Yun central仓和jcenter仓的聚合仓 */
  maven {
    url = uri("https://maven.aliyun.com/repository/public/")
  }
}

dependencies {
  annotationProcessor("org.projectlombok:lombok:1.18.20")
  /* 公司目前使用版本 */
//  implementation("org.springframework.boot:spring-boot-gradle-plugin:2.3.5.RELEASE")
//  implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
  testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

tasks {
  test {
    useJUnitPlatform()
  }
}


configurations {

  val annotationProcessor = configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
  val testAnnotationProcessor = configurations.getByName(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
  val testImplementation = configurations.getByName(JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME)

  compileOnly {
    extendsFrom(annotationProcessor, testImplementation)
  }

  runtimeClasspath {
    extendsFrom(annotationProcessor, testImplementation)
  }

  testAnnotationProcessor {
    extendsFrom(annotationProcessor)
  }

  testCompileOnly {
    extendsFrom(testAnnotationProcessor)
  }

  testRuntimeClasspath {
    extendsFrom(testAnnotationProcessor)
  }


}

tasks.withType<Jar> {
  archiveBaseName.set("gradle-plugin")
  /* 重复文件策略，排除 */
  setDuplicatesStrategy(DuplicatesStrategy.EXCLUDE)
}

gradlePlugin {
  plugins {
    create("devtools") {
      id = "com.voc.devtools"
      implementationClass = "com.voc.gradle.plugin.DevToolsPlugin"
    }
    create("") {
      id = "com.voc.app.info"
      implementationClass = "com.voc.gradle.plugin.AppInfoPlugin"
    }
  }

}


