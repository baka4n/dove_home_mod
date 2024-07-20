import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies


fun Project.minecraft() = dependencies {
    "minecraft"("net.minecraftforge:forge:${minecraft_version}-${forgeVersion}")
}

fun Project.annotation() = dependencies {
    "annotationProcessor"("org.spongepowered:mixin:0.8.5:processor")
    "annotationProcessor"("org.projectlombok:lombok:1.18.32")
    "compileOnly"("org.projectlombok:lombok:1.18.32")
}
fun Project.botania() = dependencies {

}


fun Project.autoProjectDependencies(modSettings: ModSettings) = project(":${modSettings.modid}") {
    dependencies {
        for (root in modSettings.roots) {
            "implementation"(root.apply(this@autoProjectDependencies))
        }
    }
}