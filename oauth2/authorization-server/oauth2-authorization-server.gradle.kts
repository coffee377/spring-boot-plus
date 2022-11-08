val starter = "spring-boot-starter"

dependencies {

  implementation(project(":common:common-api"))
  implementation(project(":${parent?.name}:${parent?.name}-security"))
  implementation(project(":$starter:$starter-cache"))
  implementation(project(":$starter:$starter-result"))

  implementation("mysql:mysql-connector-java")

  implementation("org.springframework.security:spring-security-crypto")
  implementation("org.springframework.boot:spring-boot-starter-web")
//  implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter")
  implementation("com.baomidou:mybatis-plus-boot-starter")
//  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")


  /* 认证服务器 */
  implementation("org.springframework.security:spring-security-oauth2-authorization-server")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

  /* 页面 */
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  implementation("org.thymeleaf:thymeleaf-spring5")

  implementation("org.mapstruct:mapstruct")

}
