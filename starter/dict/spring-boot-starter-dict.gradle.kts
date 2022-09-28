dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation(project(":common:common-api"))

  compileOnly("org.mybatis:mybatis:3.5.11")
//  compileOnly("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
}
