plugins {
  id("com.voc.devtools")
}

group = "com.voc"

/* 子项目配置 */
subprojects {

  group = "com.voc.boot"

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
        publish(true)
      }
    }
  }

  dependencies {
    /* spring boot 版本升级工具*/
//    compileOnly("org.springframework.boot:spring-boot-properties-migrator")
  }

}

tasks.wrapper {
  /* gradle wrapper 版本 */
  gradleVersion = "7.4.2"
}


