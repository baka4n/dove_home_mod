import org.gradle.api.Project
import java.util.function.Function

val author = "baka4n, dove-home-team, Nitian-Studio"
enum class ModSettings(
    val version: String,
    val display: String,
    val description: String,
    val authors:String = author,
    vararg val roots: Function<Project, Project>
) {
    dovedb("1.0.0.0", "dove home mod database", "minecraft mod dove bd"),
    dovelib("1.0.0.0", "dove home mod lib", "minecraft mod dove lib"),
    dovedatagen("1.0.0.0", "dove home mod generation", "minecraft mod dove generation",
        roots = arrayOf(
            Function {
                it.project(":${dovehomemod.modid}")
            }
        )
    ),
    doveviafix("1.0.0.0", "dove home mod viafix", "minecraft mod dove viafix"),
    dovepermissions("1.0.0.0", "dove home mod permissions", "minecraft mod dove permissions",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            }
        )
    ),
    doveminer("1.0.0.0", "dove home mod miner", "minecraft mod dove miner",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            }
        )
    ),
    dovegod("1.0.0.0", "dove home mod god", "minecraft mod dove god",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            }
        )
    ),
    doveteam("1.0.0.0", "dove home mod team", "minecraft mod dove team",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            }
        )
    ),
    dovequest("1.0.0.0", "dove home mod quest", "minecraft mod dove quest",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            }
        )
    ),
    dovemap("1.0.0.0", "dove home mod map", "minecraft mod dove map",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            }
        )
    ),
    dovequery("1.0.0.0", "dove home mod query", "minecraft mod dove query",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            }
        )
    ),
    dovecommand("1.0.0.0", "dove home mod command", "minecraft mod dove command",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            }
        )
    ),
    dovehomemod("1.0.0.0", "dove home mod", "minecraft mod dove home mod",
        roots = arrayOf(
            Function {
                it.project(":${dovedb.modid}")
            },
            Function {
                it.project(":${dovelib.modid}")
            },
            Function {
                it.project(":${doveviafix.modid}")
            },
            Function {
                it.project(":${dovepermissions.modid}")
            },
            Function {
                it.project(":${doveminer.modid}")
            },
            Function {
                it.project(":${dovegod.modid}")
            },
            Function {
                it.project(":${doveteam.modid}")
            },
            Function {
                it.project(":${dovequest.modid}")
            },
            Function {
                it.project(":${dovemap.modid}")
            },
            Function {
                it.project(":${dovequery.modid}")
            },
            Function {
                it.project(":${dovecommand.modid}")
            },
        )

    ),
    ;

    val modid: String = name.lowercase()

    fun mixin(): String {
        return """
                    {
                      "required": true,
                      "minVersion": "0.8",
                      "package": "io.github.dovehometeam.${modid}.mixin",
                      "compatibilityLevel": "JAVA_8",
                      "refmap": "${modid}.refmap.json",
                      "mixins": [
                      ],
                      "client": [
                      ],
                      "server": [
                      ],
                      "injectors": {
                        "defaultRequire": 1
                      }
                    }
                """.trimIndent()
    }

}