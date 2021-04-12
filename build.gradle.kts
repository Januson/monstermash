import org.gradle.nativeplatform.platform.internal.DefaultOperatingSystem

plugins {
    application
    id("org.beryx.jlink") version "2.23.6"
}

group = "org.example"
version = "0.0.3"

repositories {
    mavenCentral()
}

val currentOS: DefaultOperatingSystem =
    org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
val platform: String = when {
    currentOS.isWindows -> "win"
    currentOS.isLinux -> "linux"
    currentOS.isMacOsX -> "mac"
    else -> throw BuildCancelledException("Unsupported platform! $currentOS")
}

val javafxVersion = "16"
val log4jVersion = "2.14.0"
val junitVersion = "5.7.0"
dependencies {
    implementation("org.openjfx:javafx-base:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-controls:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:$platform")
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testImplementation("org.assertj:assertj-core:3.19.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
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
val imageDirPath = "$buildDir/${launcherName}"
val imageZipPath = "$buildDir/${launcherName}.zip"

jlink {
    imageDir.set(file(imageDirPath))
    imageZip.set(file(imageZipPath))
    options.addAll("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    launcher {
        name = launcherName
        jvmArgs.addAll(
            listOf(
                "-Dfile.encoding=UTF-8"
            )
        )
        windowsScriptTemplate = file("windowsScriptTemplate.txt")
    }
    forceMerge("log4j-api")
    jpackage {
        val imgType = when {
            currentOS.isWindows -> "ico"
            else -> "png"
        }
        icon = "src/main/resources/images/icon.$imgType"
        if (currentOS.isWindows) {
            installerOptions.addAll(
                listOf(
                    "--win-per-user-install",
                    "--win-dir-chooser",
                    "--win-menu",
                    "--win-shortcut"
                )
            )
        } else {
            installerType = "deb" // "rpm"
        }
//        installerOptions.addAll(listOf("--verbose"))
    }
}

val zipLinuxRelease by tasks.registering(Tar::class) {
    dependsOn(tasks["jpackage"])

    archiveFileName.set("monstermash-${project.version}-linux.tar.gz")
    destinationDirectory.set(file("$buildDir/dist"))
    compression = Compression.GZIP

    from("$buildDir/jpackage/monstermash")
    into("monstermash")
}

val zipWinRelease by tasks.registering(Zip::class) {
    dependsOn(tasks["jpackage"])

    archiveFileName.set("monstermash-${project.version}-win.zip")
    destinationDirectory.set(file("$buildDir/dist"))

    from("$buildDir/jpackage/monstermash")
    into("monstermash")
}
val copyWinInstaller by tasks.registering(Copy::class) {
    dependsOn(tasks["jpackage"])

    from("$buildDir/jpackage/monstermash")
    include("*.exe")
    into("$buildDir/dist")
}

val zipIt by tasks.registering {
    description = "Prepares artefacts for relese"
    when {
        currentOS.isWindows -> {
            dependsOn(zipWinRelease)
            dependsOn(copyWinInstaller)
        }
        // FIXME
//        currentOS.isLinux -> {
//            dependsOn(zipLinuxRelease)
//        }
        else -> throw BuildCancelledException("Unsupported platform! $currentOS")
    }
}
