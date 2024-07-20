import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.copyResources() = run {
    val rootResources = rootProject.file("src/main/resources")
    val resources = this.file("src/main/resources")
    if (resources.exists().not()) {
        rootResources.copyRecursively(resources)
    }
}