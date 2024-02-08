
architectury {
    platformSetupLoomIde()
    forge()
}


loom {
    forge {
        mixinConfig(
            "${project.name.split("-")[0]}.mixins.json",
            "${project.name.split("-")[0]}-common.mixins.json",
        )
    }
}

val common: Configuration by configurations.creating
val shadowCommon: Configuration by configurations.creating
val developmentForge: Configuration by configurations.getting

configurations {
    compileClasspath.configure { extendsFrom(common) }
    runtimeClasspath.configure { extendsFrom(common) }
    developmentForge.extendsFrom(common)
}

var commonString = ":${project.name.split("-")[0]}-common"

dependencies {
    forge("net.minecraftforge:forge:${libs.versions.minecraft.version.get()}-${libs.versions.forge.version.get()}")
    modApi("dev.architectury:architectury-forge:${libs.versions.architectury.version.get()}")
    common(project(commonString, "namedElements")) {
        isTransitive = false
    }
    shadowCommon(project(commonString, "transformProductionFabric")) {
        isTransitive = false
    }
}

tasks.processResources {
    inputs.property("version", project.version)
    filesMatching("META-INF/mods.toml") {
        expand(
            Pair("version", project.version),
        )
    }
}

tasks.shadowJar {
    exclude("fabric.mod.json")
    configurations = listOf(project.configurations["shadowCommon"])

    archiveClassifier.set("dev-shadow")
}

tasks.remapJar {
    injectAccessWidener = true
    inputFile.set(tasks.shadowJar.get().archiveFile)
    dependsOn(tasks.shadowJar)
    archiveClassifier.set(null as String?)
}

tasks.jar {
    archiveClassifier.set("dev")
}

tasks.sourcesJar {
    val commonSources = project(commonString).tasks.sourcesJar
    dependsOn(commonSources)
    from(commonSources.get().archiveFile.map {
        zipTree(it)
    })
}

components.getByName("java") {
    this as AdhocComponentWithVariants
    this.withVariantsFromConfiguration(project.configurations.getByName("shadowRuntimeElements")) {
        skip()
    }
}

