dependencies {
  compileOnly("org.springframework.boot:spring-boot-starter-security")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-client")
  compileOnly("javax.servlet:javax.servlet-api")

//  api("com.alibaba:sdk:20210421")
  api(project(":${parent!!.name}:${parent!!.name}-core"))

  /* 旧版 SDK */
  implementation("com.aliyun:alibaba-dingtalk-service-sdk:2.0.0"){
    exclude(group = "*")
  }
  /* 新版 SDK */
  implementation("com.aliyun:dingtalk:1.3.46") {
    exclude(group = "com.aliyun", module = "alibabacloud-gateway-spi")
//    exclude(group = "*")
  }
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
}

dependencyManagement {

}
