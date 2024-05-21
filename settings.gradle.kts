
pluginManagement {
    fun RepositoryHandler.mavens(vararg mavens: String) {
        mavens.forEach { maven { url = uri(it) } }
    }
    repositories {
        mavens(
            "https://maven.fabricmc.net/",
            "https://maven.architectury.dev/",
            "https://maven.minecraftforge.net/",
        )
        gradlePluginPortal()
    }
}

includeBuild("plugin")


val doveprojects: String by settings

doveprojects
    .split(",")
    .forEach {
    include("${it}-common", "${it}-fabric", "${it}-forge")
    project(":${it}-common").projectDir = file("${it}/common")
    project(":${it}-fabric").projectDir = file("${it}/fabric")
    project(":${it}-forge").projectDir = file("${it}/forge")
}

