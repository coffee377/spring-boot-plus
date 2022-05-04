plugins {
  id("com.voc.app.info")
}

dependencies {
  api(project(":${parent!!.name}:${parent!!.name}-core"))
  api("org.springframework.boot:spring-boot-starter-security")

  implementation("mysql:mysql-connector-java")
  implementation("org.springframework.boot:spring-boot-starter-jdbc")
//  implementation("org.springframework.boot:spring-boot-starter-jpa")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("com.baomidou:mybatis-plus-boot-starter")

  /* 认证服务器 */
  implementation("org.springframework.security:spring-security-oauth2-authorization-server")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.springframework.boot:spring-boot-starter-cache")

  compileOnly("javax.servlet:javax.servlet-api")
}

