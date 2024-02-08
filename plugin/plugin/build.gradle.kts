import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin)
    `java-gradle-plugin`
    alias(libs.plugins.pluginPublish)
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
    compileOnly(libs.dev.architectury.loom)
    compileOnly(libs.architectury.plugin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = sourceCompatibility

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

gradlePlugin {
    plugins {
        create("plugin") {
            id = "${
                libs.versions.maven.group.get()
            }.${
                libs.versions.archives.base.name.get()
            }.plugin"
            implementationClass = "io.github.dovehomemod.gradle.DveGradle"
            version = libs.versions.dove.kotlin.plugin.version.get()
            description = "dove home mod plugin"
            displayName = "Dove home mod Plugin"

            @Suppress("UnstableApiUsage")
            tags.set(listOf("plugin", "gradle", "dove-home-team", "architectury"))
        }
    }
}