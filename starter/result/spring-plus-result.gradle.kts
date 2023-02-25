val common = "common"
val starter = "spring-plus"

dependencies {
  implementation(project(":$common:$common-api"))
  implementation("org.springframework.boot:spring-boot-starter-web")
}

dependencyManagement {

}
