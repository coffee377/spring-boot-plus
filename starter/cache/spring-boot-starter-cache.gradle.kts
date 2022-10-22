dependencies {
//  implementation(project(":common:common-api"))
//  implementation(platform(project(":common:common-api")))
  implementation("org.springframework.boot:spring-boot-starter-cache")
  /* redis 缓存依赖 */
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  /* caffeine 缓存依赖 */
  compileOnly("com.github.ben-manes.caffeine:caffeine")
  compileOnly("org.springframework.boot:spring-boot-starter-web")

}
