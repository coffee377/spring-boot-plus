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
  implementation("io.reactivex.rxjava3:rxjava:3.1.4")
  implementation("org.springframework.security:spring-security-crypto")

  /* caffeine 缓存依赖 */
  compileOnly("com.github.ben-manes.caffeine:caffeine")
  /* redis 缓存依赖 */
  compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
  compileOnly("org.springframework.boot:spring-boot-starter-data-mongodb")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  compileOnly("com.google.code.gson:gson")
  compileOnly("org.springframework.boot:spring-boot-starter-security")

  compileOnly("com.baomidou:mybatis-plus-boot-starter")
  compileOnly("org.springframework.security:spring-security-oauth2-authorization-server")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-client")

  testImplementation("org.junit.jupiter:junit-jupiter-api")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

springBoot {
}

tasks {
  test {
    useJUnitPlatform()
  }
}
