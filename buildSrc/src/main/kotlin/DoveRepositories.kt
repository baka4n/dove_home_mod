import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.alibabaMavenCenter() = maven {
    setUrl("https://maven.aliyun.com/repository/public/")
}

fun RepositoryHandler.forgeMaven() = maven {
    name = "forge"
    setUrl("https://maven.minecraftforge.net/")
}

fun RepositoryHandler.parchmentMaven() = maven {

    name = "ParchmentMC"

    setUrl("https://maven.parchmentmc.org")

}