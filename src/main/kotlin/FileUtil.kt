import kotlinx.serialization.json.Json
import java.io.File

val file: String = "tasks.json"

inline fun <reified T> saveToFile(items: List<T>) {
    val jsonString = Json.encodeToString(items)
    val file = File(file)
    if (!file.exists()) {
        file.createNewFile()
    }
    file.writeText(jsonString)
}

inline fun <reified T> loadFile(): List<T> {
    val file = File(file)
    if (!file.exists()) {
        file.createNewFile()
        file.writeText("[]")
        return emptyList()
    }
    val jsonString = file.readText()
    return Json.decodeFromString(jsonString)
}