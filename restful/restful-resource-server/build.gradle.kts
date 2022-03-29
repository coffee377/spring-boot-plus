plugins {
  id("com.voc.app.info")
}

dependencies {
  api(project(":${parent!!.name}:${parent!!.name}-core"))
  api("org.springframework.boot:spring-boot-starter-security")

  /* 资源服务器 */
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("com.nimbusds:oauth2-oidc-sdk")

  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.springframework.boot:spring-boot-starter-cache")

  compileOnly("javax.servlet:javax.servlet-api")
}

