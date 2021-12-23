plugins {
  id("org.springframework.boot") apply false
//  id("io.spring.dependency-management") apply false
//  id("com.github.shalousun.smart-doc") apply true
//  `maven-publish`
//  `java-library`
}

group = "com.voc"

/* 所有项目配置 */
allprojects {

  apply(plugin = "com.voc.devtools")
//  apply(plugin = "java-library")
//  apply(plugin = "maven-publish")

//  configurations {
//    all {
//      resolutionStrategy {
//        /* 动态版本依赖缓存 10 minutes */
//        cacheDynamicVersionsFor(10, TimeUnit.MINUTES)
//        /* SNAPSHOT版本依赖缓存 0 seconds */
//        cacheChangingModulesFor(10, TimeUnit.SECONDS)
//      }
//    }
//
//    val annotationProcessor = configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
//    val testAnnotationProcessor = configurations.getByName(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
//
//    compileOnly {
//      extendsFrom(annotationProcessor)
//    }
//
//    runtimeClasspath {
//      extendsFrom(annotationProcessor)
//    }
//
//    testCompileOnly {
//      extendsFrom(annotationProcessor)
//    }
//
//    testAnnotationProcessor {
//      extendsFrom(annotationProcessor)
//    }
//
//    testImplementation {
//      extendsFrom(testAnnotationProcessor)
//    }
//
//  }
//
//  tasks.withType<Jar> {
//    /* 重复文件策略，排除 */
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//  }
//
//  tasks.withType<Delete> {
//    delete("out", "build")
//  }
}
dependencies {
//  implementation("org.projectlombok:lombok:1.18.22")
}

/* 子项目配置 */
subprojects {
//  apply(plugin = "java-library")
  group = "com.voc"
//  apply(plugin = "com.github.shalousun.smart-doc")
//
//  smartdoc {
//    configFile = file("src/main/resources/smart-doc.json")
//  }
//
////  path.startsWith(":spring-boot") {
////
////  }
//
//  val uname = System.getenv("DEV_OPTS_USERNAME")
//  val pwd = System.getenv("DEV_OPTS_PASSWORD")
//
//  repositories {
//    mavenLocal()
//    maven {
//      /* Ali Yun central仓和jcenter仓的聚合仓 */
//      url = uri("https://maven.aliyun.com/repository/public/")
//    }
//    /* 阿里云效账号 */
//    if (!uname.isNullOrEmpty() && !pwd.isNullOrEmpty()) {
//      maven {
//        url = uri("https://packages.aliyun.com/maven/repository/2038604-release-0bMxsA/")
//        credentials {
//          username = uname
//          password = pwd
//        }
//      }
//      maven {
//        url = uri("https://packages.aliyun.com/maven/repository/2038604-snapshot-XNRePo/")
//        credentials {
//          username = uname
//          password = pwd
//        }
//      }
//    }
//
//  }
//
//  dependencies {
//    annotationProcessor("org.projectlombok:lombok:1.18.4")
//    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.2")
//  }
//
//  publishing {
//    publications {
//
//      withType<MavenPublication> {
//        versionMapping {
//          usage("java-api") {
//            fromResolutionOf(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME)
//          }
//          usage("java-runtime") {
//            fromResolutionResult()
//          }
//        }
//
//        pom {
//
//          withXml {
//            val root = asNode()
////            root.appendNode("name", "libui")
////            root.appendNode("description", "Kotlin/Native interop to libui: a portable GUI library")
////            root.appendNode("url", POM_SCM_URL)
//          }
//
//          licenses {
//
//          }
//
//          developers {
//            developer {
//              id.set("coffee377")
//              name.set("Wu Yujie")
//              email.set("coffee377@dingtalk.com")
//            }
//          }
//
//        }
//      }
//    }
//
//    /* 声明要发布到的存储库 */
//    repositories {
//      /* 本地仓库 */
//      maven {
//        name = "local"
//        url = uri("D:/SoftWare/Maven/repository")
//      }
//
//      /* 阿里云效个人仓库 */
//      if (!uname.isNullOrEmpty() && !pwd.isNullOrEmpty()) {
//        maven {
//          // change to point to your repo, e.g. http://my.org/repo
//          val repo = "https://packages.aliyun.com/maven/repository/2038604"
//          val snapshotsRepo = "${repo}-snapshot-XNRePo/"
//          val releaseRepo = "${repo}-release-0bMxsA/"
//          name = "AliYun"
//          url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepo else releaseRepo)
//          credentials {
//            username = uname
//            password = pwd
//          }
//        }
//      }
//    }
//  }
}

tasks.wrapper {
  /* gradle wrapper 版本 */
  gradleVersion = "7.3.2"
}



