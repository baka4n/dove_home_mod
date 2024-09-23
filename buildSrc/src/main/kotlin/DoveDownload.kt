import de.undercouch.gradle.tasks.download.Download
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.TaskContainerScope
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getting
import java.util.*

fun TaskContainer.init() = run {
    val downloadCMCL = create<Download>("downloadCMCL") {
        group = "dove"
        dest(project.file("cmcl.jar"))
        src("https://github.com/MrShieh-X/console-minecraft-launcher/releases/download/2.2.1/cmcl.jar")
    }
    val downloadHMCL = create<Download>("downloadHMCL") {
        group = "dove"
        dest(project.file("hmcl.jar"))
        src("https://github.com/HMCL-dev/HMCL/releases/download/v3.5.8.249/HMCL-3.5.8.249.jar")
    }
    val taskDownloadClient = create<Exec>("downloadMinecraftClient") {
        dependsOn(downloadCMCL, downloadHMCL)
        group = "dove"
        commandLine(
            if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "cmd" else "sh",
            "/c","java", "-Dfile.encoding=UTF-8", "-jar", "cmcl.jar",
            "install", minecraft_version,
            "-n", "dovehomemodpacks", "-s", "--forge=${forgeVersion}"
        )
    }

    read.forEachIndexed { index, it ->
        when(it.source) {
            "cf", "mr" -> {
                val tasksDownload = create<Exec>("downloadNo%03d".format(index)) {
                    dependsOn(taskDownloadClient)
                    group = "dove/download"
                    val modPath = project.file(".minecraft/mods/${it.version}")
                    if (modPath.exists().not()) {
                        commandLine(
                            if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "cmd" else "sh",
                            "/c","java", "-Dfile.encoding=UTF-8", "-jar", "cmcl.jar", "mod", "--install",
                            "--source=${it.source}",
                            "--id=${it.projectId}",
                            "--game-version=${minecraft_version}",
                            "--version=${it.version}"
                        )
                    } else {
                        commandLine(
                            if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "cmd" else "sh",
                            "/c"
                        )
                    }

                }
                named("build").configure {
                    dependsOn(tasksDownload)
                }
            }
        }

    }
}