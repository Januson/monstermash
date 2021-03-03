plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.9"
    id("org.beryx.jlink") version "2.23.3"
    id("org.mikeneck.graalvm-native-image") version "v1.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val log4jVersion = "2.14.0"
val junitVersion = "5.7.0"
dependencies {
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    modularity.inferModulePath.set(true)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainModule.set("monstermash")
    mainClass.set("org.monstermash.MonsterMash")
}

javafx {
    version = "15.0.1"
    modules("javafx.controls", "javafx.fxml", "javafx.swing")
}

//mainClassName = "$moduleName/org.openjfx.MainApp"
//
//jlink {
//    options = ['--strip-debug', '--compress', '2', '--no-header-files', '--no-man-pages']
//    launcher {
//        name = 'hellofx'
//    }
//}

nativeImage {
    graalVmHome = System.getenv("JAVA_HOME")
    mainClass = "org.monstermash.MonsterMash"
    executableName = "MonsterMash!"
    outputDirectory = file("$buildDir/bin")
    arguments(
        "--no-fallback",
        "--enable-all-security-services",
        "--initialize-at-run-time=com.example.runtime",
        "--report-unsupported-elements-at-runtime"
    )
}