dependencies {
  implementation(project(":common:common-api"))
  implementation(dingtalk.bundles.sdk) {
    /* 旧版 SDK 排除项 */
    exclude(group = "commons-logging", module = "commons-logging")
    exclude(group = "log4j", module = "log4j")
    /* 新版 SDK 排除项 */
    exclude(group = "com.aliyun", module = "alibabacloud-gateway-spi")
  }
  implementation("org.springframework.boot:spring-boot-starter-data-redis")


  compileOnly("org.springframework.boot:spring-boot-starter-security")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-client")

}

dependencyManagement {

}
