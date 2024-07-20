plugins {
    `kotlin-dsl`
    java
}

repositories {
    maven {
        url = uri("https://maven.aliyun.com/repository/public/")
    }
    gradlePluginPortal()
    mavenCentral()
    maven {
        name = "MinecraftForge"
        url = uri("https://maven.minecraftforge.net/")
    }
    maven {
        name = "Sponge Maven"
        url = uri("https://repo.spongepowered.org/repository/maven-public/")
    }
    maven {
        name = "ParchmentMC"
        url = uri("https://maven.parchmentmc.org")
    }
}

dependencies {
    implementation(gradleApi())
    implementation("net.minecraftforge.gradle:ForgeGradle:6.+")
    implementation("org.parchmentmc:librarian:1.+")
    implementation("cn.hutool:hutool-all:5.8.+")
    implementation("de.undercouch:gradle-download-task:5.6.+") {
        exclude("org.gradle.api")
    }

    implementation("org.spongepowered:mixingradle:0.7-SNAPSHOT") {
        exclude("org.gradle.api")
        exclude("org.codehaus.groovy", "groovy")
    }
    implementation("org.codehaus.groovy:groovy:3.0.22")

}