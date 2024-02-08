package io.github.dovehomemod.gradle

import dev.architectury.plugin.ArchitectPluginExtension
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.util.Constants.Configurations.MAPPINGS
import net.fabricmc.loom.util.Constants.Configurations.MINECRAFT

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionContainer

/**
 * @author baka4n
 * @since dove home gradle plugin
 */
class DveGradle: Plugin<Project> {
    /**
     * @since override apply
     */
    override fun apply(target: Project) {
        val extensions = target.extensions
        val versionCatalogsExtension = extensions.getByType<VersionCatalogsExtension>()
        val architectury = extensions.named<ArchitectPluginExtension>("architectury")
        val libs = versionCatalogsExtension.find("libs").get()
        val minecraftVersion = libs.findVersion("minecraft-version").get()
        architectury.minecraft = minecraftVersion.toString()
        target.subprojects {
            libs.findVersion("${it.name.split(",")[0]}-version").ifPresentOrElse(
                {ver ->
                         it.version = ver
                },
                {
                    it.version = versionCatalogsExtension.find("libs").get().findVersion("mod-version").get()
                }
            )


        }
    }
}

/**
 * @since fast get extensions by name
 * @param name extensions name
 * @return T reified
 * @throws NullPointerException null
 */
inline fun <reified T> ExtensionContainer.named(name: String): T {
    val t = this.getByName(name)
    if (t is T) return t
    throw NullPointerException("don't find this")
}

/**
 * @since copy getByType
 * @return T reified
 */
inline fun <reified T> ExtensionContainer.getByType(): T {
    return this.getByType(T::class.java)
}
