import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.copyResources() = run {
    val rootResources = rootProject.file("src/main/resources")
    val resources = this.file("src/main/resources")
    if (resources.exists().not()) {
        rootResources.copyRecursively(resources)
    }
}

fun Project.autoGenMixinConfig(modSettings: ModSettings) = run {
    val mixinConfig = file("src/main/resources/${modSettings.modid}.mixins.json")
    if (mixinConfig.exists().not()) {
        mixinConfig.bufferedWriter(Charsets.UTF_8).use {
            it.write(modSettings.mixin())
            it.close()
        }
    }
}