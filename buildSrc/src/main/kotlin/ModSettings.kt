import org.gradle.api.Project
import java.util.function.Function

val author = "baka4n, dove-home-team, Nitian-Studio"
enum class ModSettings(
    val version: String,
    val display: String,
    val description: String,
    vararg roots: Function<Project, Project>
) {
    dovedb("1.0.0.0", "dove home mod database", "minecraft mod dove bd"),
    dovelib("1.0.0.0", "dove home mod lib", "minecraft mod dove lib"),
    dovedatagen("1.0.0.0", "dove home mod generation", "minecraft mod dove generation",
        {
            it.project(":${dovehomemod.modid}")
        }
    ),
    doveviafix("1.0.0.0", "dove home mod viafix", "minecraft mod dove viafix"),
    dovepermissions("1.0.0.0", "dove home mod permissions", "minecraft mod dove permissions",
        {
            it.project(":${dovedb.modid}")
        }
    ),
    doveminer("1.0.0.0", "dove home mod miner", "minecraft mod dove miner",
        {
            it.project(":${dovedb.modid}")
        }
    ),
    dovegod("1.0.0.0", "dove home mod god", "minecraft mod dove god",
        {
            it.project(":${dovedb.modid}")
        }
    ),
    doveteam("1.0.0.0", "dove home mod team", "minecraft mod dove team",
        {
            it.project(":${dovedb.modid}")
        }
    ),
    dovequest("1.0.0.0", "dove home mod quest", "minecraft mod dove quest",
        {
            it.project(":${dovedb.modid}")
        }
    ),
    dovemap("1.0.0.0", "dove home mod map", "minecraft mod dove map",
        {
            it.project(":${dovedb.modid}")
        }
    ),
    dovequery("1.0.0.0", "dove home mod query", "minecraft mod dove query",
        {
            it.project(":${dovedb.modid}")
        }
    ),
    dovecommand("1.0.0.0", "dove home mod command", "minecraft mod dove command",
        {
            it.project(":${dovedb.modid}")
        }
    ),
    dovehomemod("1.0.0.0", "dove home mod", "minecraft mod dove home mod",
        {
            it.project(":${dovedb.modid}")
        },
        {
            it.project(":${dovelib.modid}")
        },
        {
            it.project(":${doveviafix.modid}")
        },
        {
            it.project(":${dovepermissions.modid}")
        },
        {
            it.project(":${doveminer.modid}")
        },
        {
            it.project(":${dovegod.modid}")
        },
        {
            it.project(":${doveteam.modid}")
        },
        {
            it.project(":${dovequest.modid}")
        },
        {
            it.project(":${dovemap.modid}")
        },
        {
            it.project(":${dovequery.modid}")
        },
        {
            it.project(":${dovecommand.modid}")
        },

    ),

    ;


    val roots: Array<out Function<Project, Project>>

    val modid: String
    val authors:String = author

    init {
        this.modid = name.lowercase()
        this.roots = roots
    }


}