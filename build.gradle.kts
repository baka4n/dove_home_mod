import de.undercouch.gradle.tasks.download.Download
import net.minecraftforge.gradle.common.util.MinecraftExtension
import org.spongepowered.asm.gradle.plugins.MixinExtension
import java.util.*

plugins {
    java
}
apply(plugin = "de.undercouch.download")

file("modlist-1.20.1.csv").toPath().read()

val mappingChannel1: String by rootProject
val mappingVersion1: String by rootProject

tasks {
    val downloadCMCL = create<Download>("downloadCMCL") {
        group = "dove"
        dest(file("cmcl.jar"))
        src("https://github.com/MrShieh-X/console-minecraft-launcher/releases/download/2.2.1/cmcl.jar")
    }
    val downloadHMCL = create<Download>("downloadHMCL") {
        group = "dove"
        dest(file("hmcl.jar"))
        src("https://github.com/HMCL-dev/HMCL/releases/download/v3.5.8.249/HMCL-3.5.8.249.jar")
    }
    val taskDownloadClient = create<Exec>("downloadMinecraftClient") {
        dependsOn(downloadCMCL, downloadHMCL)
        group = "dove"
        commandLine(
            if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "cmd" else "sh",
            "/c","java", "-Dfile.encoding=UTF-8", "-jar", "cmcl.jar",
            "install", minecraft_version,
            "-n", "dovehomemodpacks", "-s", "--forge=${forgeVersion}"
        )
    }



    read.forEachIndexed { index, it ->
        when(it.source) {
            "cf", "mr" -> {
                val tasksDownload = create<Exec>("downloadNo%03d".format(index)) {
                    dependsOn(taskDownloadClient)
                    group = "dove/download"
                    val modPath = file(".minecraft/mods/${it.version}")
                    if (modPath.exists().not()) {
                        commandLine(
                            if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "cmd" else "sh",
                            "/c","java", "-Dfile.encoding=UTF-8", "-jar", "cmcl.jar", "mod", "--install",
                            "--source=${it.source}",
                            "--id=${it.projectId}",
                            "--game-version=${minecraft_version}",
                            "--version=${it.version}"
                        )
                    } else {
                        commandLine(
                            if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "cmd" else "sh",
                            "/c"
                        )
                    }

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

    val modSettings = ModSettings.valueOf(project.name)

    group = mavenGroup
    version = modSettings.version



    configure<MinecraftExtension> {

        mappings(mappingChannel1, mappingVersion1)
        copyIdeResources.set(true)
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

    copyResources()

    val mixinConfig = file("src/main/resources/${modSettings.modid}.mixins.json")
    if (mixinConfig.exists().not()) {
        mixinConfig.bufferedWriter(Charsets.UTF_8).use {
            it.write(modSettings.mixin())
            it.close()
        }
    }

    configure<MixinExtension> {
        add(sourceSets.main.get(), "${modSettings.modid}.refmap.json")
        config("${modSettings.modid}.mixins.json")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }


    apply(from = rootProject.file("gradle/repositories.gradle.kts"))



    sourceSets.main.configure {
        resources.srcDirs("src/generated/resources")
        resources.exclude(".cache/")
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    base {
        archivesName.set(modSettings.modid)
    }


    minecraft()
    annotation()

    tasks {
        jar.configure {
            manifest {
                attributes(
                    "Specification-Title" to modSettings.modid,
                    "Specification-Vendor" to modSettings.authors,
                    "Specification-Version" to "1",
                    "Implementation-Title" to modSettings.modid,
                    "Implementation-Version" to project.tasks.jar.get().archiveVersion,
                    "Implementation-Vendor" to modSettings.authors,
                    // "Implementation-Timestamp": new Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
                )
            }
            finalizedBy( "reobfJar")
        }
        processResources.processResources(modSettings)
    }

    autoProjectDependencies(modSettings)

}

