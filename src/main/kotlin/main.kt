import de.m3y.kformat.Table
import de.m3y.kformat.table
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val taskManager = TaskManager()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    while (true) {
        printUsage()
        print("> ")
        val input = readln()
        val parts = input.split(" ", limit = 2)
        val command = parts[0].lowercase()
        when (command) {
            "add" -> {
                if (parts.size > 2) {
                    printUsage()
                    continue
                }
                val text = parts[1].trim('"')
                taskManager.addTask(description = text)
                println("Successfully added a task")
            }
            "update" -> {
                if (parts.size < 2) {
                    printUsage()
                    continue
                }
                val tokens = parts[1].split(" ", limit = 2)
                if (tokens.size < 2) {
                    printUsage()
                    continue
                }
                val id = tokens[0].toIntOrNull()
                val getTask = taskManager.findById(id)
                if (getTask == null) {
                    println("Could not find task with id: $id")
                    continue
                }
                val description = tokens[1].trim('"')
                if (id != null) {
                    taskManager.updateTask(getTask.id, description)
                } else printUsage()
            }
            "delete" -> {
                if (parts.size > 2) {
                    printUsage()
                    continue
                }
                val id = parts[1].toIntOrNull()
                val getTask = taskManager.findById(id)
                if (getTask == null) {
                    println("Could not find task with id: $id")
                    continue
                }

                if (id != null) {
                    taskManager.deleteTask(getTask.id)
                    println("Successfully deleted a task")
                } else printUsage()
            }
            "mark-in-progress" -> {
                if (parts.size > 2) {
                    printUsage()
                    continue
                }
                val id = parts[1].toIntOrNull()
                val getTask = taskManager.findById(id)
                if (getTask == null) {
                    println("Could not find task with id: $id")
                    continue
                }

                if (id != null) {
                    taskManager.markInProgress(id)
                    println("Successfully marked in progress")
                } else printUsage()
            }
            "mark-done" -> {
                if (parts.size > 2) {
                    printUsage()
                    continue
                }
                val id = parts[1].toIntOrNull()
                val getTask = taskManager.findById(id)
                if (getTask == null) {
                    println("Could not find task with id: $id")
                    continue
                }
                if (id != null) {
                    taskManager.markDone(id)
                    println("Successfully marked done")
                } else printUsage()
            }
            "list" -> {
                val filter = if (parts.size > 1) parts[1].lowercase() else ""
                val tasks = when (filter) {
                    "todo" -> taskManager.listTaskTodo()
                    "in-progress" -> taskManager.listTaskInProgess()
                    "done" -> taskManager.listTaskDone()
                    else -> taskManager.listTasks()
                }

                println(
                    table {
                        header("ID", "Description", "Status", "Created at", "Updated at")
                        tasks.forEach {
                            row(
                                it.id,
                                it.description,
                                it.status,
                                LocalDateTime.parse(it.createdAt).format(formatter),
                                if (it.updatedAt != "")
                                    LocalDateTime.parse(it.updatedAt).format(formatter).toString()
                                else ""
                            )
                        }
                        hints {
                            defaultAlignment = Table.Hints.Alignment.CENTER
                            borderStyle = Table.BorderStyle.SINGLE_LINE
                        }
                    }.render(StringBuilder())
                )
            }
            "exit" -> {
                break
            }
        }
    }
}

fun printUsage() {
    println("===================[ List commands ]===================")
    println("- add \"[description]\"")
    println("- update [id] \"[description]\"")
    println("- delete [id]")
    println("- mark-in-progress [id]")
    println("- mark-done [id]")
    println("- list")
    println("- list todo")
    println("- list done")
    println("- list in-progress")
    println("- exit")
    println("========================================================")
}
