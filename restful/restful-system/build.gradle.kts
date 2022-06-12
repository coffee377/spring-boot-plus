plugins {
  id("com.voc.app.info")
  id("org.springdoc.openapi-gradle-plugin") version "1.3.4"
}

val restful = parent!!.name

dependencies {
  api(project(":$restful:$restful-core"))
  implementation("org.springframework.boot:spring-boot-starter-web") {
//    exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
  }
  implementation("mysql:mysql-connector-java")
  implementation("com.baomidou:mybatis-plus-boot-starter")
  implementation("org.mapstruct:mapstruct")
  implementation("org.springframework.security:spring-security-crypto")

  implementation("org.springdoc:springdoc-openapi-ui")

  annotationProcessor("org.mapstruct:mapstruct-processor")
  annotationProcessor("org.projectlombok:lombok-mapstruct-binding")

//  compileOnly("org.springframework.boot:spring-boot-starter-security")
//  testImplementation("org.springframework.boot:spring-boot-starter-security")
}

tasks {
  test {
    useJUnitPlatform()
  }
}
