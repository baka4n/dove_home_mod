
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `java-library`
    alias(libs.plugins.architectury.plugin)
    alias(libs.plugins.dev.architectury.loom).apply(false)
    alias(libs.plugins.dove.home.plugin)
    alias(libs.plugins.shadow).apply(false)
    `maven-publish`

}

var lib: LibrariesForLibs = libs
var lib__ = extensions.getByType<VersionCatalogsExtension>().named("libs")

subprojects {

    apply(plugin = lib.plugins.dev.architectury.loom.get().pluginId)
    apply(plugin = lib.plugins.shadow.get().pluginId)
    apply(plugin = lib.plugins.maven.publish.get().pluginId)
//    val findVersion =
//        lib__.findVersion("${project.name.split("-")[0]}-version")
//    version = if (findVersion.isPresent) {
//        findVersion.get()
//    } else {
//        lib.versions.mod.version.get()
//    }


    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
    loom.silentMojangMappingsLicense()

    dependencies {
        "minecraft"("com.mojang:minecraft:${lib.versions.minecraft.version.get()}")
        @Suppress("UnstableApiUsage")
        "mappings"(loom.layered {
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${lib.versions.minecraft.version.get()}:${lib.versions.parchment.get()}@zip")
        })
    }

    publishing {
        publications.create<MavenPublication>(project.name.split("-")[1] + "Maven") {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
        repositories {
            mavenLocal()
        }
    }
}


allprojects {
    apply(plugin = "java-library")
    apply(plugin = lib.plugins.architectury.plugin.get().pluginId)


    repositories {
        maven {
            url = uri("https://maven.parchmentmc.org")
            name = "parchment mc"
        }
    }

    base.archivesName.set(project.name.split("-")[0])

    group = lib.versions.maven.group.get()
    repositories {

    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    java {
        withSourcesJar()
    }
}




val doveprojects: String by project
