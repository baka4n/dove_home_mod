pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "MinecraftForge"
            url = uri("https://maven.minecraftforge.net/")
        }
        maven {
            name = "ParchmentMC"
            url = uri("https://maven.parchmentmc.org")
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention").version("0.7.0")
}

rootProject.name = "dove-modules"

//include("dovehome")
//include("db")
//include("god")
//project(":dovehome").name = "dovehomemod"
//project(":db").name = "dovedb"
//project(":god").name = "dovegod"
