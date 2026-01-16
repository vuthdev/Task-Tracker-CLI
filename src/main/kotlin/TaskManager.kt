import java.time.LocalDateTime

class TaskManager {
    var nextId = (getAllTasks().maxOfOrNull { it.id } ?: 0) + 1
    fun getAllTasks(): MutableList<Task> {
        val tasks = loadFile<Task>()
        return tasks.toMutableList()
    }

    fun findById(id: Int?): Task? {
        val tasks = getAllTasks().find { it.id == id }
        return tasks
    }

    fun addTask(description: String) {
        val tasks = getAllTasks()
        tasks.add(Task(id = nextId++, description = description))
        saveToFile(tasks)
    }

    fun deleteTask(id: Int?) {
        val tasks = getAllTasks()
        tasks.removeAll { it.id == id }
        saveToFile(tasks)
    }

    fun updateTask(id: Int, description: String) {
        val tasks = getAllTasks()
        val task = tasks.find { it.id == id }
        task?.description = description
        task?.updatedAt = LocalDateTime.now().toString()
        saveToFile(tasks)
    }

    fun markInProgress(id: Int?) {
        val todo = getAllTasks()
        val markTask = todo.find { it.id == id }
        markTask?.status = TaskStatus.IN_PROGRESS
        saveToFile(todo)
    }

    fun markDone(id: Int?) {
        val todo = getAllTasks()
        val markTask = todo.find { it.id == id }
        markTask?.status = TaskStatus.DONE
        saveToFile(todo)
    }

    fun listTasks(): List<Task> {
        val tasks = getAllTasks()
        return tasks
    }

    fun listTaskTodo(): List<Task> {
        val tasks = getAllTasks()
        return tasks.filter { it.status == TaskStatus.TODO }
    }

    fun listTaskInProgess(): List<Task> {
        val tasks = getAllTasks()
        return tasks.filter { it.status == TaskStatus.IN_PROGRESS }
    }

    fun listTaskDone(): List<Task> {
        val tasks = getAllTasks()
        return tasks.filter { it.status == TaskStatus.DONE }
    }
}