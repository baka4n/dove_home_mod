import org.gradle.api.Project
import org.gradle.api.plugins.BasePluginExtension
import org.gradle.kotlin.dsl.the

fun Project.named(modSettings: ModSettings) {
    group = mavenGroup
    version = modSettings.version

    the<BasePluginExtension>().archivesName.set(modSettings.modid)
}