dependencies {
  implementation(project(":common:common-api"))
  implementation("org.springframework.boot:spring-boot-starter-web")

  compileOnly(orm.mybatis.boot)
}
