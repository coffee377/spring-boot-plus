plugins {
  id("com.voc.app.info")
}

val restful = parent!!.name

dependencies {
  api(project(":$restful:$restful-core"))
  implementation("org.springframework.boot:spring-boot-starter-web") {
//    exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
  }
//  implementation(project(":$restful:$restful-dingtalk"))
//  implementation(project(":$restful:$restful-security"))
  implementation("mysql:mysql-connector-java")
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
  implementation("com.baomidou:mybatis-plus-boot-starter")
  implementation("org.mapstruct:mapstruct")

  annotationProcessor("org.mapstruct:mapstruct-processor")
  annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

//  compileOnly("org.springframework.boot:spring-boot-starter-security")
//  testImplementation("org.springframework.boot:spring-boot-starter-security")
}

tasks {
  test {
    useJUnitPlatform()
  }
}
