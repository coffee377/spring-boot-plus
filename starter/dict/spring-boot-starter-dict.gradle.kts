dependencies {
  implementation(project(":common:common-api"))
//  implementation(platform(project(":common:common-api")))
  implementation("org.springframework.boot:spring-boot-starter-web")

  compileOnly(ormLibs.mybatis.boot)
}
