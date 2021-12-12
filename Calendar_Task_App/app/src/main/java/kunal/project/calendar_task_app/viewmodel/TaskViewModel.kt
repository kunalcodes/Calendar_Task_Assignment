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


    // calling the storeTask method in the repo
    fun storeTask(storeTaskRequestModel: StoreTaskRequestModel) {
        repo.storeTask(storeTaskRequestModel)
    }

    // fetching the all Task list from repo as flowable
    fun showTaskList() : Flowable<List<TaskModel>> {
        return repo.showTaskList()
    }

    // calling the delete task method in the repo
    fun deleteTask(taskModel: TaskModel){
        repo.deleteTask(taskModel)
    }

    // fetching the daily Task list from repo as flowable
    fun showTaskListOnDate(date: String): Flowable<List<TaskModel>> {
        return repo.showTaskListOnDate(date)
    }

}