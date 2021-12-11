package kunal.project.calendar_task_app.repo

import kunal.project.calendar_task_app.data.local.TaskDAO
import kunal.project.calendar_task_app.data.remote.ApiService
import javax.inject.Inject


class TaskRepository @Inject constructor(val dao: TaskDAO, val apiService: ApiService) {

    private val authKey = "d95a5f11-13ef-419a-be7e-5a64cac73624"


}