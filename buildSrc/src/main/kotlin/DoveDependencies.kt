import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.botania() = dependencies {

}

fun Project.autoProjectDependencies(modSettings: ModSettings) = dependencies {
    for (root in modSettings.roots) {
        dependencies {
            
        }
        implementation(root.apply(this@autoProjectDependencies))
    }
}