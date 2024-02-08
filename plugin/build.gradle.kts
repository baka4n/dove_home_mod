import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    alias(libs.plugins.kotlin).apply(false)
    alias(libs.plugins.pluginPublish).apply(false)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.versionCheck)
}

var lib: LibrariesForLibs = libs

allprojects {
    group = lib.versions.maven.group.get()
    version = lib.versions.dove.kotlin.plugin.version
    apply {

        plugin(rootProject.libs.plugins.detekt.get().pluginId)
        plugin(rootProject.libs.plugins.ktlint.get().pluginId)
    }

    ktlint {
        debug.set(false)
        verbose.set(false)
        android.set(false)
        outputToConsole.set(true)
        ignoreFailures.set(false)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }

    detekt {
        config.setFrom(rootProject.files("../config/detekt/detekt.yml"))
    }
}

tasks.withType<Detekt>()