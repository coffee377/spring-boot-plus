group = "com.voc.codegen"

subprojects {
  group = "com.voc.codegen"
  dependencies {
    implementation(enforcedPlatform(project(":spring-plus-dependencies")))
  }
}
