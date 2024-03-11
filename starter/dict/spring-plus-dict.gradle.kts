val common = "common"
val starter = "spring-plus-starter"

dependencies {
  implementation(project(":$common:$common-api"))

  compileOnly("org.springframework.boot:spring-boot-starter-web")
  compileOnly("org.mybatis.spring.boot:mybatis-spring-boot-starter")
  compileOnly("com.baomidou:mybatis-plus-boot-starter")
  testImplementation("javax.servlet:javax.servlet-api")
  testImplementation("org.springframework.boot:spring-boot-starter-web")
}
