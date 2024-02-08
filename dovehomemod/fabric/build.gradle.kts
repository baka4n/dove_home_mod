import org.gradle.jvm.component.internal.DefaultJvmSoftwareComponent

architectury {
    platformSetupLoomIde()
    fabric()
}

val common: Configuration by configurations.creating
val shadowCommon: Configuration by configurations.creating
val developmentFabric: Configuration by configurations.getting

configurations {
    compileClasspath.configure { extendsFrom(common) }
    runtimeClasspath.configure { extendsFrom(common) }
    developmentFabric.extendsFrom(common)
}

var commonString = ":${project.name.split("-")[0]}-common"

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${libs.versions.fabric.loader.version.get()}")
    modApi("net.fabricmc.fabric-api:fabric-api:${libs.versions.fabric.api.version.get()}+${libs.versions.minecraft.version.get()}")
    modApi("dev.architectury:architectury-fabric:${libs.versions.architectury.version.get()}")
    common(project(commonString, "namedElements")) {
        isTransitive = false
    }
    shadowCommon(project(commonString, "transformProductionFabric")) {
        isTransitive = false
    }

}


tasks.processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand(
            Pair("version", project.version),
        )
    }
}
tasks.shadowJar {
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
    from(commonSources.get().archiveFile.map { zipTree(it) })
}

components.getByName("java") {
    this as AdhocComponentWithVariants
    this.withVariantsFromConfiguration(project.configurations.getByName("shadowRuntimeElements")) {
        skip()
    }
}