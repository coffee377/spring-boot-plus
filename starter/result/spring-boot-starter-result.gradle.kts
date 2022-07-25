
dependencies {
  compileOnly("org.springframework.boot:spring-boot-starter-web")


  implementation(project(":common:common-api"))

  testImplementation("org.springframework.boot:spring-boot-starter-web")
//  testImplementation(project(":common:common-api"))
}

dependencyManagement {

}
