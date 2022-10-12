dependencies {
//  api(project(":${parent!!.name}:${parent!!.name}-core"))
  implementation(project(":common:common-api"))
  implementation(project(":${parent?.name}:${parent?.name}-security"))
  implementation("org.springframework.boot:spring-boot-starter-security")

  /* 资源服务器 */
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("com.nimbusds:oauth2-oidc-sdk")

  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.springframework.boot:spring-boot-starter-cache")

//  compileOnly("javax.servlet:javax.servlet-api")
}
