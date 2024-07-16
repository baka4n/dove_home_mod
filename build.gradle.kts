import net.minecraftforge.gradle.common.util.MinecraftExtension
import org.csveed.annotations.CsvHeaderName
import org.csveed.api.CsvClient
import org.csveed.api.CsvClientImpl
import org.spongepowered.asm.gradle.plugins.MixinExtension
import java.util.*

buildscript {
    repositories {
        maven {
            name = "Sponge Maven"
            url = uri("https://repo.spongepowered.org/repository/maven-public/")
        }
        mavenCentral()
    }
    dependencies {
        classpath("org.spongepowered:mixingradle:0.7-SNAPSHOT")
        classpath("org.csveed:csveed:0.8.1")
    }
}

plugins {
    id("net.minecraftforge.gradle").version("[6.0.16,6.2)").apply(false)
    id("org.parchmentmc.librarian.forgegradle").version("1.+").apply(false)
    java
}

val modGroupId: String by rootProject
val modVersion: String by rootProject
val mappingChannel: String by rootProject
val mappingVersion: String by rootProject
val modAuthors: String by rootProject
val minecraftVersion: String by rootProject
val minecraftVersionRange: String by rootProject
val forgeVersion: String by rootProject
val forgeVersionRange: String by rootProject
val modName: String by rootProject
val loaderVersionRange: String by rootProject
val modLicense: String by rootProject
val modDescription: String by rootProject

val modList = file("modlist-1.20.1.csv")

class Bean {
    var `mod-name`: String? = null
    var `english-name`: String? = null
    var description: String? = null
    var `mod-label`: String? = null
    var `is-client`: Int = 0
    var `mcmod-url`: String? = null
    var `project-id`: String? = null
    var source: String? = null
    var `download-id`: String? = null
}

modList.bufferedReader(Charsets.UTF_8).use {
    val csvClient = CsvClientImpl<Bean>(it)
    val beans = csvClient.readBeans()
    for (bean in beans) {
        println(bean.`mod-name`)
    }
}

subprojects {
    apply(plugin = "net.minecraftforge.gradle")
    apply(plugin = "org.parchmentmc.librarian.forgegradle")
    apply(plugin = "org.spongepowered.mixin")
    apply(plugin = "eclipse")
    apply(plugin = "idea")

    group = modGroupId
    version = modVersion

    java {
        toolchain.languageVersion = JavaLanguageVersion.of(17)
    }

    apply(from = rootProject.file("gradle/repositories.gradle"))

    sourceSets.main.configure {
        resources.srcDirs("src/generated/resources")
        resources.exclude(".cache/")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    base {
        archivesName = project.name
    }

    configure<MinecraftExtension> {
        mappings(mappingChannel, mappingVersion)
        copyIdeResources = true
        accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))
        runs {
            configureEach {
                workingDirectory(file("run"))
                property("forge.logging.markers", "REGISTRIES")
                property("forge.logging.console.level", "debug")
                mods {
                    create(project.name) {
                        source(sourceSets.main.get())
                    }
                }
            }
            create("client") {
                property("forge.enabledGameTestNamespaces", project.name)
            }
            create("server") {

                property("forge.enabledGameTestNamespaces", project.name)
                args("--nogui")
            }
            create("gameTestServer") {
                property("forge.enabledGameTestNamespaces", project.name)

            }
            create("data") {
                workingDirectory(project.file("run-data"))
                args("--mod", project.name, "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/"))
            }
        }
    }
    configure<MixinExtension> {
        add(sourceSets.main.get(), "${project.name}.refmap.json")
        config("${project.name}.mixins.json}")
    }

    apply(from = rootProject.file("gradle/dependenciesPath/minecraft.gradle"))
    apply(from = rootProject.file("gradle/dependenciesPath/annotation.gradle"))
    tasks {
        named<Jar>("jar").configure {
            manifest {
                attributes(
                    "Specification-Title" to project.name,
                    "Specification-Vendor" to modAuthors,
                    "Specification-Version" to "1",
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to project.tasks.jar.get().archiveVersion,
                    "Implementation-Vendor" to modAuthors,
                    // "Implementation-Timestamp": new Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
                )
            }
            finalizedBy( "reobfJar")
        }

        named<ProcessResources>("processResources") {
            val replaceProperties = mapOf(
                "minecraft_version" to minecraftVersion,
                "minecraft_version_range" to minecraftVersionRange,
                "forge_version" to forgeVersion,
                "forge_version_range" to forgeVersionRange,
                "loader_version_range" to loaderVersionRange,
                "mod_id" to project.name,
                "mod_name" to modName,
                "mod_license" to modLicense,
                "mod_version" to modVersion,
                "mod_authors" to modAuthors,
                "mod_description" to modDescription,
            )
            inputs.properties(replaceProperties)
            filesMatching(listOf("MTEA-INF/mods.toml", "pack.mcmeta")) {
                expand (replaceProperties)
                expand(mapOf("project" to project))
            }
        }
    }
}
