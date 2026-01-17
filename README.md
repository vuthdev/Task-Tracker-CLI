---

## 📝 Task Tracker CLI (Kotlin)

This project is a **Command Line Task Tracker** built with **Kotlin**, inspired by the
**[Task Tracker project from roadmap.sh](https://roadmap.sh/projects/task-tracker)**.

It allows users to manage tasks directly from the terminal, supporting task creation, updates, deletion, and status tracking with persistent storage.

---

## 🚀 Features

* Add new tasks with a description
* Update task descriptions
* Delete tasks by ID
* Mark tasks as:

  * **TODO**
  * **IN_PROGRESS**
  * **DONE**
* List all tasks or filter by status
* Display tasks in a formatted table
* Track task creation and update timestamps
* Persistent file-based storage

---

## 📌 Commands

```text
add "[description]"
update [id] "[description]"
delete [id]
mark-in-progress [id]
mark-done [id]
list
list todo
list in-progress
list done
exit
```

---

## 📊 Output Format

Tasks are displayed in a table showing:

* ID
* Description
* Status
* Created date
* Updated date

This makes task management simple and readable in the terminal.

---

## 🛠 Tech Stack

* **Kotlin**
* **Java Time API**
* **kformat** (table rendering)
* File-based storage (no database)

---

## 🎯 Learning Goal

This project was built as a learning exercise to:

* Practice Kotlin fundamentals
* Understand CLI application design
* Implement CRUD operations
* Follow a real-world project specification

Project idea source:
👉 [https://roadmap.sh/projects/task-tracker](https://roadmap.sh/projects/task-tracker)

---
