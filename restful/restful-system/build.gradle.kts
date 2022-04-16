val restful = parent!!.name

dependencies {
  api(project(":$restful:$restful-core"))
  implementation("org.springframework.boot:spring-boot-starter-web") {
//    exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
  }
//  implementation(project(":$restful:$restful-dingtalk"))
//  implementation(project(":$restful:$restful-security"))
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
  implementation("com.baomidou:mybatis-plus-boot-starter:3.5.1")

//  compileOnly("org.springframework.boot:spring-boot-starter-security")
//  testImplementation("org.springframework.boot:spring-boot-starter-security")
}

