plugins {
    application
//    id("org.openjfx.javafxplugin") version "0.0.9"
    id("org.beryx.jlink") version "2.23.3"
//    id("org.mikeneck.graalvm-native-image") version "v1.2.0"
//    id("com.palantir.graal") version "0.7.1"
}

group = "org.example"
version = "0.0.1"

repositories {
    mavenCentral()
}

//def currentOS = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.currentOperatingSystem;
//def platform
//        if (currentOS.isWindows()) {
//            platform = 'win'
//        } else if (currentOS.isLinux()) {
//            platform = 'linux'
//        } else if (currentOS.isMacOsX()) {
//            platform = 'mac'
//        }

val platform = "win"
val javafxVersion = "15.0.1"
val log4jVersion = "2.14.0"
val junitVersion = "5.7.0"
dependencies {
    implementation("org.openjfx:javafx-base:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-controls:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-swing:$javafxVersion:$platform")
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
    modularity.inferModulePath.set(true)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainModule.set("monstermash")
    mainClass.set("org.monstermash.MonsterMash")
}

val launcherName = "monstermash"
val imageDirPath = "$buildDir/dist/${launcherName}"
val imageZipPath = "$buildDir/dist/${launcherName}.zip"

////tasks.withType<Jar> {
////    manifest {
////        attributes["Main-Class"] = "org.monstermash.MonsterMash"
////    }
////    from(sourceSets.main.get().output)
////
////    dependsOn(configurations.runtimeClasspath)
////    from({
////        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
////    })
////}

jlink {
    imageDir.set(file(imageDirPath))
    imageZip.set(file(imageZipPath))
    options.addAll("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    launcher {
        name = launcherName
        jvmArgs.addAll(listOf(
            "--enable-preview",
            "-Dfile.encoding=UTF-8"
        ))
        windowsScriptTemplate = file("windowsScriptTemplate.txt")
    }
    forceMerge("log4j-api")
    jpackage {
        val os = org.gradle.internal.os.OperatingSystem.current()
        if(os.isWindows) {
            installerOptions.addAll(listOf("--win-per-user-install", "--win-dir-chooser", "--win-menu", "--win-shortcut"))
        } else {
            installerType = "deb" // "rpm"
        }
        installerOptions.addAll(listOf("--verbose"))
    }
}

//tasks.jlink {
//    doLast {
//        copy {
//            from("src/main/resources")
//            into("$imageDirPath/bin")
//        }
//    }
//}
