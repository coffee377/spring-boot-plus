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
//  implementation(project(":$restful:$restful-dingtalk"))
//  implementation(project(":$restful:$restful-security"))
  implementation("mysql:mysql-connector-java")
//  implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
  implementation("com.baomidou:mybatis-plus-boot-starter")
  implementation("org.mapstruct:mapstruct")
  implementation("org.springframework.security:spring-security-crypto")

  implementation("org.springdoc:springdoc-openapi-ui:1.6.8")

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
