import java.time.LocalDateTime


plugins {
    alias(libs.plugins.dev.architectury.loom)
//        .apply(false)
    `maven-publish`
    `java-library`
}

base.archivesName = "creative_generation"

version = libs.versions.creative.generation.get()
group = libs.versions.maven.group.get()

java {

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = sourceCompatibility

}

loom {
    silentMojangMappingsLicense()
    forge {
        mixinConfigs = listOf(
            "${project.name}.mixins.json"
        )
    }
}



repositories {
    maven {
        url = uri("https://maven.parchmentmc.org")
        name = "parchment mc"
    }
    maven {
        // location of the maven that hosts JEI files before January 2023
        name = "Progwml6's maven"
        url = uri("https://dvs1.progwml6.com/files/maven/")
    }
    maven {
        // location of the maven that hosts JEI files since January 2023
        name = "Jared's maven"
        url = uri("https://maven.blamejared.com/")
    }
    maven {
        // location of a maven mirror for JEI files, as a fallback
        name = "ModMaven"
        url = uri("https://modmaven.dev")
    }
    maven { url = uri("https://maven.theillusivec4.top/") } // Curios
    maven { // Create Forge and Registrate Forge
        url = uri("https://maven.tterrag.com/")
        content {
            includeGroup("com.tterrag.registrate")
            includeGroup("com.simibubi.create")
        }
    }
    maven {
        // Shedaniel's maven (Architectury API)
        url = uri("https://maven.architectury.dev")
        content {
            includeGroupByRegex("me.shedaniel.*")
            includeGroup("dev.architectury")
            includeGroup("me.shedaniel")
        }
    }

    maven {
        // saps.dev Maven (KubeJS and Rhino)
        url = uri("https://maven.saps.dev/releases")
        content {
            includeGroup("dev.latvian.mods")
        }
    }
}

dependencies {
    // to change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${libs.versions.minecraft.version.get()}")

    // choose what mappings you want to use here
    // leave this uncommented if you want to use
    // mojang's official mappings, or feel free
    // to add your own mappings here (how about
    // mojmap layered with parchment, for example?)
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${libs.versions.minecraft.version.get()}:${libs.versions.parchment.get()}@zip")
    })

    // uncomment this if you want to use yarn mappings
    // mappings "net.fabricmc:yarn:${project.yarn_mappings}:v2"

    // your forge dependency, this is **required** when using Forge Loom in forge mode!
    forge("net.minecraftforge:forge:${libs.versions.minecraft.version.get()}-${libs.versions.forge.version.get()}")

    // additional dependencies can be specified using loom's regular format
    // specifying a "mod" dependency (like modImplementation or modApi)
    // will cause loom to remap the file to your specified mappings

    // in this example, we'll be adding JEI as a dependency
    // according to their developer example on GitHub
    // see: https://github.com/mezz/JustEnoughItems/wiki/Getting-Started
    // compile against the JEI API but do not include it at runtime
    // don't worry about loom "not finding a forge mod" here,
    // JEI's api just doesn't have any class with an @Mod annotation
    modImplementation(libs.flywheel)
    modImplementation(libs.registrate)
    modImplementation(libs.versions.create.get()) {
        isTransitive = false
    }
    modImplementation(libs.kubejs.forge)
    implementation(libs.mixin.extras)
    annotationProcessor(libs.mixin.extras)
    implementation(libs.mixin.extras.forge)
    include(libs.mixin.extras.forge)
    modCompileOnly(libs.rei.forge)
    modCompileOnly(libs.rei.api.forge)
    modLocalRuntime(libs.rei.forge)
    modCompileOnlyApi("mezz.jei:jei-${libs.versions.minecraft.version.get()}-common-api:${libs.versions.jei.version.get()}")
    modCompileOnlyApi("mezz.jei:jei-${libs.versions.minecraft.version.get()}-forge-api:${libs.versions.jei.version.get()}")
    modLocalRuntime("mezz.jei:jei-${libs.versions.minecraft.version.get()}-forge:${libs.versions.jei.version.get()}")
    implementation(libs.gson.overwrite)
    forgeRuntimeLibrary(libs.gson.overwrite)
    include(libs.gson.overwrite)
}

tasks.getByName<ProcessResources>("processResources") {
    inputs.property("version", project.version)
    filesMatching("META-INF/mods.toml") {
        expand(Pair("version", project.version))
    }
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
}


java {
    withSourcesJar()
}

tasks.getByName<Jar>("jar") {
    manifest {
        attributes(
            mapOf(
                Pair("Specification-Title", project.name),
                Pair("Specification-Vendor", "1"),
                Pair("Implementation-Title", project.name),
                Pair("Implementation-Version", project.version),
                Pair("Implementation-Vendor", "baka4n, dove-home-team"),
                Pair("Implementation-Timestamp", LocalDateTime.now()),
            )
        )

    }
}



