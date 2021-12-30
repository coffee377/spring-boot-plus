plugins {
//  `java-library`
}

group = "com.voc"

/* 子项目配置 */
subprojects {
//  apply(plugin = "com.voc.devtools")

  group = "com.voc"

  val uname = System.getenv("DEV_OPTS_USERNAME")
  val pwd = System.getenv("DEV_OPTS_PASSWORD")

}

tasks.wrapper {
  /* gradle wrapper 版本 */
  gradleVersion = "7.3.2"
}



