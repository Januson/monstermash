plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.9"
    id("org.beryx.jlink") version "2.23.3"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

application {
    mainModule.set("monstermash")
    mainClass.set("org.monstermash.MonsterMash")
}

javafx {
    version = "15.0.1"
    modules("javafx.controls", "javafx.fxml")
}

//mainClassName = "$moduleName/org.openjfx.MainApp"
//
//jlink {
//    options = ['--strip-debug', '--compress', '2', '--no-header-files', '--no-man-pages']
//    launcher {
//        name = 'hellofx'
//    }
//}
