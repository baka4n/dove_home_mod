pluginManagement {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.fabricmc.net/")
        }
        maven {
            url = uri("https://maven.architectury.dev/")
        }
        maven {
            url = uri("https://files.minecraftforge.net/maven/")
        }
        maven {
            url = uri("https://maven.parchmentmc.org")
            name = "parchment mc"
        }
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.architectury.dev/")
        }
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}