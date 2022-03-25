plugins {
  id("com.voc.app.info")
}

repositories {
}

dependencies {
  annotationProcessor("org.projectlombok:lombok")

  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation("org.springframework.boot:spring-boot-starter-actuator")

  /* caffeine 缓存依赖 */
  compileOnly("com.github.ben-manes.caffeine:caffeine")
  /* redis 缓存依赖 */
  compileOnly("org.springframework.boot:spring-boot-starter-data-redis")
  compileOnly("org.springframework.boot:spring-boot-starter-data-mongodb")
  compileOnly("com.google.code.gson:gson")
  compileOnly("org.springframework.boot:spring-boot-starter-security")
}

springBoot{
}
