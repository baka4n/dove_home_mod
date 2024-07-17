import cn.hutool.core.text.csv.CsvReadConfig
import cn.hutool.core.text.csv.CsvUtil
import de.undercouch.gradle.tasks.download.Download
import net.minecraftforge.gradle.common.util.MinecraftExtension
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
        classpath("cn.hutool:hutool-all:5.8.29")
    }
}

plugins {
    id("net.minecraftforge.gradle").version("[6.0.16,6.2)").apply(false)
    id("org.parchmentmc.librarian.forgegradle").version("1.+").apply(false)
    java

    id("de.undercouch.download").version("5.6.0")
}

class CsvRead {
    var modName: String? = null
    var englishName: String? = null
    var description: String? = null
    var modLabel: String? = null
    var isClient: Boolean? = null
    var mcmodUrl: String? = null
    var projectId: String? = null
    var source: String? = null
    var version: String? = null
}
var read: LinkedList<CsvRead> = LinkedList()
file("modlist-1.20.1.csv").bufferedReader(Charsets.UTF_8).use {
    val csvRead =
        CsvUtil.getReader(
            it,
            CsvReadConfig()
                .setSkipEmptyRows(true)
                .setContainsHeader(true)
                .setTrimField(true)
        ).read()
    csvRead.rows.forEach {row ->
        val csv = CsvRead()
        csv.modName = row.getByName("modName")
        csv.englishName = row.getByName("englishName")
        csv.description = row.getByName("description")
        csv.modLabel = row.getByName("modLabel")
        csv.isClient = row.getByName("isClient").toBoolean()
        csv.mcmodUrl = row.getByName("mcmodUrl")
        csv.projectId = row.getByName("projectId")
        csv.source = row.getByName("source")
        csv.version = row.getByName("version")
        read.add(csv)
    }
    it.close()
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


tasks {
    val downloadCMCL = create<Download>("downloadCMCL") {
        dest(file("cmcl.jar"))
        src("https://github.com/MrShieh-X/console-minecraft-launcher/releases/download/2.2.1/cmcl.jar")
    }
    val taskDownloadClient = create<Exec>("downloadMinecraftClient") {
        commandLine(
            if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "cmd" else "sh",
            "/c","java", "-Dfile.encoding=UTF-8", "-jar", "cmcl.jar",
            "install", minecraftVersion,
            "-n", "dovehomemodpacks", "-s", "--forge=${forgeVersion}"
        )
    }

    build {
        dependsOn(downloadCMCL, taskDownloadClient)
    }

    read.forEachIndexed { index, it ->
        when(it.source) {
            "cf", "mr" -> {
                val tasksDownload = create<Exec>("downloadNo%03d".format(index)) {
                    setGroup("download")
                    commandLine(
                        if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "cmd" else "sh",
                        "/c","java", "-Dfile.encoding=UTF-8", "-jar", "cmcl.jar", "mod", "--install",
                        "--source=${it.source}",
                        "--id=${it.projectId}",
                        "--game-version=${minecraftVersion}",
                        "--version=${it.version}"
                    )
                }
                build {
                    dependsOn(tasksDownload)
                }
            }
        }

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
