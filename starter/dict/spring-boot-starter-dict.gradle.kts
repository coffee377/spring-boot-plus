dependencies {
  implementation(project(":common:common-api"))

  compileOnly("org.springframework.boot:spring-boot-starter-web")
  compileOnly(ormLibs.mybatis.boot)
  compileOnly(ormLibs.mybatis.plus.boot)
}
