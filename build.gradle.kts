import net.minecraftforge.gradle.common.util.MinecraftExtension
import org.spongepowered.asm.gradle.plugins.MixinExtension

plugins {
    java
}
apply(plugin = "de.undercouch.download")

file("modlist-1.20.1.csv").read()
tasks.init()

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
        mappings(Minecraft.channel, Minecraft.version)
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
    autoGenMixinConfig(modSettings)


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
        jar.jar(modSettings)

        processResources.processResources(modSettings)
    }

    autoProjectDependencies(modSettings)

}

