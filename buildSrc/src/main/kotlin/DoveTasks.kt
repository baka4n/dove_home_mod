
import org.gradle.api.tasks.TaskProvider
import org.gradle.language.jvm.tasks.ProcessResources


@Suppress("UnstableApiUsage")
fun TaskProvider<ProcessResources>.processResources(modSettings: ModSettings) = this.configure {
    val replaceProperties = mapOf(
        "minecraft_version" to minecraft_version,
        "minecraft_version_range" to mcRange,
        "forge_version" to forgeVersion,
        "forge_version_range" to forgeRange,
        "loader_version_range" to forgeRange,
        "mod_id" to modSettings.modid,
        "mod_name" to modSettings.display,
        "mod_license" to license,
        "mod_version" to modSettings.version,
        "mod_authors" to modSettings.authors,
        "mod_description" to modSettings.description,
    )
    inputs.properties(replaceProperties)
    filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) {
        expand(replaceProperties)
    }
}