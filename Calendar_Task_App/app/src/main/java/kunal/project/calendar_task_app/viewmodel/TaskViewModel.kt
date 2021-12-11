package kunal.project.calendar_task_app.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kunal.project.calendar_task_app.repo.TaskRepository
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(val repo : TaskRepository) : ViewModel() {

}