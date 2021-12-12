package kunal.project.calendar_task_app.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import kunal.project.calendar_task_app.data.local.TaskModel
import kunal.project.calendar_task_app.data.remote.request.StoreTaskRequestModel
import kunal.project.calendar_task_app.repo.TaskRepository
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(val repo : TaskRepository) : ViewModel() {


    fun storeTask(storeTaskRequestModel: StoreTaskRequestModel) {
        repo.storeTask(storeTaskRequestModel)
    }

    fun showTaskList() : Flowable<List<TaskModel>> {
        return repo.showTaskList()
    }

    fun deleteTask(taskModel: TaskModel){
        repo.deleteTask(taskModel)
    }

}