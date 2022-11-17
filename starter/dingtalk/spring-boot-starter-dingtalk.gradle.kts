val common = "common"
val starter = "spring-boot-starter"

dependencies {
  implementation(project(":$common:$common-api"))
  implementation(project(":$starter:$starter-cache"))
//  implementation(project(":$starter:$starter-result"))
  implementation(libs.bundles.dingtalk) {
    /* 旧版 SDK 排除项 */
    exclude(group = "commons-logging", module = "commons-logging")
    exclude(group = "log4j", module = "log4j")
    /* 新版 SDK 排除项 */
//    exclude(group = "com.aliyun", module = "alibabacloud-gateway-spi")
  }
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.data:spring-data-commons")
//  compileOnly("org.springframework.boot:spring-boot-starter-security")
//  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-client")

}

dependencyManagement {

}
