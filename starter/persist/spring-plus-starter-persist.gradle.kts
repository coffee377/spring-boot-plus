val common = "common"

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
  implementation(project(":$common:$common-api"))
//  implementation(ormLibs.mybatis.plus)
}
