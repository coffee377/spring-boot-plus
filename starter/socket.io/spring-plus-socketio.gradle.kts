plugins {
  `java-library`
}
sourceSets {
  main {
    java {
      srcDir("src/main/engine.io")
      srcDir("src/main/socket.io")
    }
    resources {

    }

  }
  test {
    java {
      srcDir("src/test/engine.io")
      srcDir("src/test/socket.io")
    }
  }
}


configurations.implementation.get().extendsFrom(configurations.runtimeOnly.get())


dependencies {
  compileOnly("org.springframework.boot:spring-boot-starter-web")

  //  implementation(libs.socket.io.server)
  implementation("io.netty:netty-buffer")
  implementation("io.netty:netty-codec-http")
  implementation("io.netty:netty-transport-native-epoll")

  runtimeOnly(dependencyNotation = "org.redisson:redisson:3.24.3")
  runtimeOnly(dependencyNotation = "com.hazelcast:hazelcast-client:3.12.13")

  implementation("org.json:json:20231013")

  testImplementation("org.junit.vintage:junit-vintage-engine")
  testImplementation("org.jmockit:jmockit:1.49")
}

