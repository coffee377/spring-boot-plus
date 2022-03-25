dependencies {
  compileOnly("org.springframework.boot:spring-boot-starter-security")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-client")

  api("com.alibaba:sdk:20210421")
  api(project(":${parent!!.name}:${parent!!.name}-core"))

  implementation("org.springframework.boot:spring-boot-starter-data-redis")
}

dependencyManagement {

}
