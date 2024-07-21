import net.minecraftforge.gradle.common.util.MinecraftExtension
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.the
import org.spongepowered.asm.gradle.plugins.MixinExtension

fun Project.initMinecraft(modSettings: ModSettings) = configure<MinecraftExtension> {
    mappings(Minecraft.channel, Minecraft.version)
    copyIdeResources.set(true)
    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))
    runs.run {
        configureEach {
            workingDirectory(file("run"))
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            mods.create(modSettings.modid) {
                source(the<SourceSetContainer>().getByName("main"))
            }
        }
        create("client") {
            property("forge.enabledGameTestNamespaces", modSettings.modid)
        }
        create("server") {

            property("forge.enabledGameTestNamespaces", modSettings.modid)
            args("--nogui")
        }
        create("gameTestServer") {
            property("forge.enabledGameTestNamespaces", modSettings.modid)
        }
        create("data") {
            workingDirectory(file("run-data"))
            args("--mod", modSettings.modid, "--all", "--output", file("src/generated/resources/"), "--existing", file("src/main/resources/"))
        }
    }
}

fun Project.initMixin(modSettings: ModSettings) = configure<MixinExtension> {
    add(the<SourceSetContainer>().getByName("main"), "${modSettings.modid}.refmap.json")
    config("${modSettings.modid}.mixins.json")
}