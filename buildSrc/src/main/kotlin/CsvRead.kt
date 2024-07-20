import cn.hutool.core.text.csv.CsvReadConfig
import cn.hutool.core.text.csv.CsvUtil
import java.nio.file.Path
import java.util.*
import kotlin.io.path.bufferedReader

class CsvRead {
    var modName: String? = null
    var englishName: String? = null
    var description: String? = null
    var modLabel: String? = null
    var isClient: Boolean? = null
    var mcmodUrl: String? = null
    var projectId: String? = null
    var source: String? = null
    var version: String? = null
}

var read: LinkedList<CsvRead> = LinkedList()

fun Path.read() = bufferedReader(Charsets.UTF_8).use {
    val csvRead =
        CsvUtil.getReader(
            it,
            CsvReadConfig()
                .setSkipEmptyRows(true)
                .setContainsHeader(true)
                .setTrimField(true)
        ).read()
    csvRead.rows.forEach {row ->
        val csv = CsvRead()
        csv.modName = row.getByName("modName")
        csv.englishName = row.getByName("englishName")
        csv.description = row.getByName("description")
        csv.modLabel = row.getByName("modLabel")
        csv.isClient = row.getByName("isClient").toBoolean()
        csv.mcmodUrl = row.getByName("mcmodUrl")
        csv.projectId = row.getByName("projectId")
        csv.source = row.getByName("source")
        csv.version = row.getByName("version")
        read.add(csv)
    }
    it.close()
}