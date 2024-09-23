import net.minecraftforge.gradle.userdev.tasks.JarJar

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

    tasks.named<JarJar>("jarJar").configure {
        enabled = true
    }

    val modSettings = ModSettings.valueOf(project.name)

//    group = mavenGroup
//    version = modSettings.version
    named(modSettings)
    initMinecraft(modSettings)
    copyResources()
    autoGenMixinConfig(modSettings)

    initMixin(modSettings)


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

    minecraft()
    annotation()

    tasks {
        jar.jar(modSettings)

        processResources.processResources(modSettings)
    }

    autoProjectDependencies(modSettings)

}

