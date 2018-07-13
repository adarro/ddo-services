import com.sun.org.apache.xml.internal.security.utils.XMLUtils

version = "0.0.1"

object VersionInfo {
    const val camel: String = "2.22.0"
    const val slf4j = "1.7.13"
    const val log4j2 = "2.4.1"
    const val weld = "2.3.1.Final"
    const val deltaspike = "1.5.1"
    const val springBoot = "2.0.3.RELEASE"
    const val springVersion = "4.3.17.RELEASE"
    const val vertx = "3.5.2"
    const val scalaTest = "3.0.5"
    val javaSource = JavaVersion.VERSION_1_8
}


val versions: VersionInfo by extra { VersionInfo }
val scalaVersion by extra { "2.12" }
val scalaMinorVersion by extra { "5" }
val scalaFullVersion by extra { "$scalaVersion.$scalaMinorVersion" }



buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.3.RELEASE")
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.2")
    }
}


plugins {
    `java-library`
//    idea
    scala
    id ("com.github.maiflai.scalatest") version "0.22"
//    eclipse
}

apply {
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
    plugin("org.junit.platform.gradle.plugin")

}

repositories {
    mavenCentral()
}

group = "io.truthencode.spring"
version = "0.0.1-SNAPSHOT"

val scalaCompilerPlugin by configurations.creating



dependencies {
    implementation("org.scala-lang:scala-library:$scalaFullVersion")
    //  implementation("org.springframework.boot:spring-boot-starter-jersey")
    implementation("org.springframework.boot:spring-boot-starter-web")  {
        exclude(module = "spring-boot-starter-tomcat")
    }
    implementation("org.apache.camel:camel-spring-boot-starter:2.22.0")
    implementation("org.apache.camel:camel-vertx:${versions.camel}")
    implementation("org.apache.camel:camel-flatpack-starter:${versions.camel}")
    implementation(group = "org.apache.camel", name = "camel-spring-javaconfig", version = versions.camel)
    implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3")

    implementation(group = "io.vertx", name = "vertx-core", version = "3.5.2")

    implementation(group = "io.vertx", name = "vertx-web", version = "3.5.2")
    scalaCompilerPlugin(group = "org.scalamacros", name = "paradise_$scalaFullVersion", version = "2.1.0")
    annotationProcessor("org.projectlombok:lombok")
    testCompile(group = "org.springframework.boot", name = "spring-boot-starter-test")
    testCompile("org.junit.jupiter:junit-jupiter-api")

    testRuntime("org.junit.jupiter:junit-jupiter-engine")

    testRuntime("org.junit.vintage:junit-vintage-engine:4.12.2")
    // CAMEL Testing from example (https://dev.to/matthieusb/integration-testing-on-existing-routes-with-apache-camel-and-spring-and-dbunit)
    //  testImplementation(group="org.apache.camel",name="camel-test",version = versions.camel)
    testImplementation(group = "org.apache.camel", name = "camel-test-spring", version = versions.camel)
    testImplementation("org.scalatest", "scalatest_$scalaVersion", versions.scalaTest)
    testRuntime ("org.pegdown:pegdown:1.4.2")

}

/**
 * Configure java compiler options.
 */
java {
    sourceCompatibility = versions.javaSource
    targetCompatibility = versions.javaSource
}

tasks.withType(ScalaCompile::class.java) {
    scalaCompileOptions.additionalParameters = listOf(
        "-Xplugin:${scalaCompilerPlugin.asPath}",
        "-feature",
        "-Xlint"
    )
    sourceCompatibility = versions.javaSource.toString()
    targetCompatibility = versions.javaSource.toString()

}

tasks.getByName("check").dependsOn("scalatest")


configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

//test {
//    maxParallelForks = 1
//}