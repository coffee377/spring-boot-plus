plugins {
  id("com.voc.app.info")
}

dependencyManagement {

  dependencies {
    dependency("org.springframework.security:spring-security-oauth2-authorization-server:0.2.1")
  }

}

dependencies {
  api(project(":${parent!!.name}:${parent!!.name}-core"))
  api("org.springframework.boot:spring-boot-starter-security")

  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  implementation("org.springframework.security:spring-security-oauth2-authorization-server")

  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.springframework.boot:spring-boot-starter-cache")
}



