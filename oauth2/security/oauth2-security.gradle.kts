dependencies {
//  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation(project(":common:common-api"))
  implementation(project(":spring-boot-starter:spring-boot-starter-result"))
  implementation("org.springframework.security:spring-security-crypto")
  implementation("org.springframework.security:spring-security-oauth2-core")

  compileOnly("org.springframework.boot:spring-boot-starter-security")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  compileOnly("org.springframework.security:spring-security-oauth2-authorization-server")
//  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-client")
}

