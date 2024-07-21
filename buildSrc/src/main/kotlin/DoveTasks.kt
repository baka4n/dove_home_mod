
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.attributes
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

fun TaskProvider<Jar>.jar(modSettings: ModSettings) = configure {
    manifest {
        attributes(
            "Specification-Title" to modSettings.modid,
            "Specification-Vendor" to modSettings.authors,
            "Specification-Version" to "1",
            "Implementation-Title" to modSettings.modid,
            "Implementation-Version" to project.tasks.named("jar", Jar::class.java).get().archiveVersion,
            "Implementation-Vendor" to modSettings.authors,
            // "Implementation-Timestamp": new Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
        )
    }
    finalizedBy( "reobfJar")
}