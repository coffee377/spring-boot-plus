plugins {
  id("com.voc.app.info")
}

repositories {
}

dependencies {
  annotationProcessor("org.projectlombok:lombok")

  implementation("org.springframework.boot:spring-boot-starter-web") {
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
  }
  implementation("org.springframework.boot:spring-boot-starter-undertow")
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

  /* caffeine 缓存依赖 */
  compileOnly("com.github.ben-manes.caffeine:caffeine")
  /* redis 缓存依赖 */
  compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
  compileOnly("org.springframework.boot:spring-boot-starter-data-mongodb")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  compileOnly("com.google.code.gson:gson")
  compileOnly("org.springframework.boot:spring-boot-starter-security")
}

springBoot {
}
