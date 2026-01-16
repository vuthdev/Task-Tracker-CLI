import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Task(
    val id: Int = 0,
    var description: String,
    var status: TaskStatus = TaskStatus.TODO,
    var createdAt: String = LocalDateTime.now().toString(),
    var updatedAt: String? = ""
)
