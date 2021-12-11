package kunal.project.calendar_task_app.data.remote

import io.reactivex.Observable
import kunal.project.calendar_task_app.data.remote.request.DeleteTaskRequestModel
import kunal.project.calendar_task_app.data.remote.request.GetTasksRequestModel
import kunal.project.calendar_task_app.data.remote.request.StoreTaskRequestModel
import kunal.project.calendar_task_app.data.remote.response.DeleteTaskResponseModel
import kunal.project.calendar_task_app.data.remote.response.GetTasksResponseModel
import kunal.project.calendar_task_app.data.remote.response.StoreTaskResponseModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("api/storeCalendarTask")
    fun storeTaskToAPI(
        @Header("Authorization") Authorization: String,
        @Body storeTaskRequestModel: StoreTaskRequestModel
    ): Observable<StoreTaskResponseModel>

    @POST("api/getCalendarTaskList")
    fun getTaskListFromAPI(
        @Header("Authorization") Authorization: String,
        @Body getTasksRequestModel: GetTasksRequestModel
    ): Observable<GetTasksResponseModel>


    @POST("api/deleteCalendarTask")
    fun deleteTaskFromAPI(
        @Header("Authorization") Authorization: String,
        @Body deleteTaskRequestModel: DeleteTaskRequestModel
    ): Observable<DeleteTaskResponseModel>

}