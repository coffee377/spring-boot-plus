dependencies {
  implementation(project(":common:common-api"))

  implementation("mysql:mysql-connector-java")

  implementation("org.springframework.security:spring-security-crypto")
  implementation("org.springframework.boot:spring-boot-starter-web")
//  implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter")
  implementation("com.baomidou:mybatis-plus-boot-starter")
//  implementation("org.springframework.boot:spring-boot-starter-jdbc")

  /* redis 缓存 */
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")

  /* 认证服务器 */
  implementation("org.springframework.security:spring-security-oauth2-authorization-server")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

  implementation("org.mapstruct:mapstruct")
  annotationProcessor("org.mapstruct:mapstruct-processor")
  annotationProcessor("org.projectlombok:lombok-mapstruct-binding")

}
