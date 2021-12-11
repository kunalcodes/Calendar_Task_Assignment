package kunal.project.calendar_task_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kunal.project.calendar_task_app.repo.TaskRepository

class TaskViewModelFactory(val repo: TaskRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(repo) as T
    }
}