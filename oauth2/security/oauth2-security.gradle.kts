val common = "common"
val starter = "spring-plus"

dependencies {

    implementation(project(":$common:$common-api"))

    implementation("org.springframework.security:spring-security-crypto")
    implementation("org.bouncycastle:bcprov-jdk18on:1.76")
    implementation("org.springframework.security:spring-security-oauth2-core")

    api(project(":$starter:$starter-result"))

    compileOnly("org.springframework.data:spring-data-commons")
    compileOnly("org.springframework.boot:spring-boot-starter-security")
    compileOnly("org.springframework.security:spring-security-oauth2-client")
    compileOnly("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    compileOnly("org.springframework.security:spring-security-oauth2-authorization-server")
}

