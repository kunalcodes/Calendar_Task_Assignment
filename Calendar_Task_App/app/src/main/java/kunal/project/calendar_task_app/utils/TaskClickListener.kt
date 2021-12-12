package kunal.project.calendar_task_app.utils

import kunal.project.calendar_task_app.data.local.TaskModel

interface TaskClickListener {
    fun onDeleteClicked(taskModel: TaskModel)
}