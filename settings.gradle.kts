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

include("lib")
include("dovehome")
include("db")
include("god")
include("permissions")
include("datagen")
include("miner")
include("team")
include("quest")
include("map")
include("query")
include("command")
project(":lib").name = "dovelib"
project(":dovehome").name = "dovehomemod"
project(":db").name = "dovedb"
project(":god").name = "dovegod"
project(":permissions").name = "dovepermissions"
project(":datagen").name = "dovedatagen"
project(":miner").name = "doveminer"
project(":team").name = "doveteam"
project(":quest").name = "dovequest"
project(":map").name = "dovemap"
project(":query").name = "dovequery"
project(":command").name = "dovecommand"
