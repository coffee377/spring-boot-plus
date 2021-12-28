plugins {
//  id("org.springframework.boot") apply false
//  id("com.voc.devtools")
  `java-library`
}

group = "com.voc"

/* 子项目配置 */
subprojects {
//  apply(plugin = "com.voc.devtools")

  group = "com.voc"
//  apply(plugin = "com.github.shalousun.smart-doc")
//
//  smartdoc {
//    configFile = file("src/main/resources/smart-doc.json")
//  }

  val uname = System.getenv("DEV_OPTS_USERNAME")
  val pwd = System.getenv("DEV_OPTS_PASSWORD")

  repositories {
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
  }

//  publishing {
//    publications {
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
//
//    }
//
//    /* 声明要发布到的存储库 */
//    repositories {
//      /* 本地仓库 */
////      maven {
////        name = "local"
////        url = uri("D:/SoftWare/Maven/repository")
////      }
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



