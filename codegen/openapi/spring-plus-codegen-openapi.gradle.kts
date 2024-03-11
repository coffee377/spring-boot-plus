plugins {
  id("org.openapi.generator") version "7.2.0"
}

devtools {
  autoService(true)
}

dependencies {
  implementation("org.openapitools:openapi-generator") {
//    isTransitive = false
  }
  runtimeOnly("org.openapitools:openapi-generator-cli") {
//    isTransitive = false
  }
//  implementation(files("${layout.buildDirectory.get().asFile}/libs/spring-plus-codegen-openapi-0.1.4-SNAPSHOT.jar"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
  useJUnitPlatform()
  testLogging {
    events("passed", "skipped", "failed")
  }
}

tasks.build {
  dependsOn("copyCli")
}

tasks.register<Copy>("copyCli") {
  from(project.configurations.runtimeClasspath)
  include("openapi-generator-cli-*.jar")
  into(File(layout.buildDirectory.get().asFile, "libs"))
}

openApiMeta {
  generatorName.set("Sample")
  outputFolder.set("${layout.buildDirectory.get().asFile}/generators/my-codegen")
}


openApiValidate {
  inputSpec.set(File(layout.buildDirectory.get().asFile, "petstore-v3.0-invalid.yaml").path)
  recommend.set(true)
}

openApiGenerate {
  verbose.set(true)
  skipValidateSpec.set(true)
//  engine.set("handlebars")
  generatorName.set("java")
  cleanupOutput.set(true)
  generateApiDocumentation.set(false)
  inputSpec.set("${layout.projectDirectory.asFile}/openapi.json")
  outputDir.set("${layout.buildDirectory.get().asFile}/generated")
//  apiPackage.set("org.openapi.example.api")
//  invokerPackage.set("org.openapi.example.invoker")
//  modelPackage.set("org.openapi.example.model")
//  configOptions.put("dateLibrary", "java8")
//  configOptions.set([
//    dateLibrary: "java8"
//  ])
}


