dependencies {
  compileOnly("org.springframework.boot:spring-boot-starter-security")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-client")
  compileOnly("javax.servlet:javax.servlet-api")


  api("com.alibaba:sdk:20210421")
  api(project(":${parent!!.name}:${parent!!.name}-core"))

  implementation("org.springframework.boot:spring-boot-starter-data-redis")
}

dependencyManagement {

}
