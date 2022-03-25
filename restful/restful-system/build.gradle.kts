val restful = parent!!.name

dependencies {
  implementation(project(":$restful:$restful-core"))
//  implementation(project(":$restful:$restful-dingtalk"))
//  implementation(project(":$restful:$restful-security"))
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
  compileOnly("org.springframework.boot:spring-boot-starter-security")
  testImplementation("org.springframework.boot:spring-boot-starter-security")
}

