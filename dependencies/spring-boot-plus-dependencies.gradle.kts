plugins {
  `java-platform`
  `maven-publish`
}

javaPlatform {
  allowDependencies()
}

dependencies {
  /* Spring Boot 依赖 */
  api(platform(libs.spring.boot.dependencies))
  api(platform(libs.spring.security.bom))

  constraints {
    /* 授权服务器依赖 */
    api(libs.spring.authorization.server)
    api("com.baomidou:mybatis-plus-boot-starter:3.5.2")
    api("org.mapstruct:mapstruct:1.5.0.RC1")
    api("org.mapstruct:mapstruct-processor:1.5.0.RC1")
    api("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    api("org.springdoc:springdoc-openapi-ui:1.6.11")

    api(project(":spring-boot-starter:spring-boot-starter-result"))
//		api "com.nimbusds:nimbus-jose-jwt:9.24.4"
//		api "javax.servlet:javax.servlet-api:4.0.1"
//		api "junit:junit:4.13.2"
//		api "org.assertj:assertj-core:3.23.1"
//		api "org.mockito:mockito-core:4.8.0"
//		api "com.squareup.okhttp3:mockwebserver:4.10.0"
//		api "com.squareup.okhttp3:okhttp:4.10.0"
//		api "com.jayway.jsonpath:json-path:2.7.0"
//		api "org.hsqldb:hsqldb:2.5.2"
  }
}

publishing {
  repositories {
    maven {
      name = "local"
      url = uri("${project.rootProject.buildDir}/publications/repos")
    }
  }
  publications {
    create<MavenPublication>("myPlatform") {
      from(components["javaPlatform"])
    }
  }
}

