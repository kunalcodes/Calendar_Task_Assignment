package kunal.project.calendar_task_app.repo

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kunal.project.calendar_task_app.data.local.TaskDAO
import kunal.project.calendar_task_app.data.local.TaskModel
import kunal.project.calendar_task_app.data.remote.ApiService
import kunal.project.calendar_task_app.data.remote.request.DeleteTaskRequestModel
import kunal.project.calendar_task_app.data.remote.request.GetTasksRequestModel
import kunal.project.calendar_task_app.data.remote.request.StoreTaskRequestModel
import kunal.project.calendar_task_app.data.remote.response.DeleteTaskResponseModel
import kunal.project.calendar_task_app.data.remote.response.GetTasksResponseModel
import kunal.project.calendar_task_app.data.remote.response.StoreTaskResponseModel
import javax.inject.Inject
import kotlin.random.Random


class TaskRepository @Inject constructor(val dao: TaskDAO, val apiService: ApiService) {

    private val authKey = "d95a5f11-13ef-419a-be7e-5a64cac73624"
    private val userID = 1014
    private var taskList = ArrayList<TaskModel>()

    fun storeTask(storeTaskRequestModel: StoreTaskRequestModel) {
        apiService.storeTaskToAPI(authKey, storeTaskRequestModel).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(object : Observer<StoreTaskResponseModel> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("Kunal", "onSubscribe: Storing to Api Subscribe")
                }

                override fun onNext(t: StoreTaskResponseModel) {
                    if (t.status == "Success") {
                        Log.d("Kunal", "onNext: Storing to Api Success")
                        syncDBWithApi()
                    } else {
                        Log.d("Kunal", "onNext: Storing to Api Error")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("Kunal", "onError: Storing to Api Error")
                }

                override fun onComplete() {
                    Log.d("Kunal", "onComplete: Storing to Api Completed")
                }

            })
    }

    fun deleteTask(taskModel: TaskModel) {
        val deleteTaskRequestModel = DeleteTaskRequestModel(userID, taskModel.id)
        apiService.deleteTaskFromAPI(authKey, deleteTaskRequestModel).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe(object : Observer<DeleteTaskResponseModel>{
                override fun onSubscribe(d: Disposable) {
                    Log.d("Kunal", "onSubscribe: Deleting from Api Subscribe")
                }

                override fun onNext(t: DeleteTaskResponseModel) {
                    if (t.status == "Success") {
                        Log.d("Kunal", "onNext: Deleting from Api Success")
                        dao.deleteTaskFromDB(taskModel)
                    } else {
                        Log.d("Kunal", "onNext: Deleting from Api Error")
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("Kunal", "onError: Deleting from Api Error")
                }

                override fun onComplete() {
                    Log.d("Kunal", "onComplete: Deleting from Api Completed")
                }

            })
    }

    fun syncDBWithApi() {
        val getTaskRequestModel = GetTasksRequestModel(userID)
        apiService.getTaskListFromAPI(authKey, getTaskRequestModel).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe(object : Observer<GetTasksResponseModel> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("Kunal", "onSubscribe: Getting from Api Subscribe")
                }

                override fun onNext(t: GetTasksResponseModel) {
                    t.let {
                        taskList.clear()
                        val tasksResponseModelList = t.tasks
                        tasksResponseModelList?.forEach {
                            val taskModel = TaskModel(
                                it!!.taskId!!,
                                it.taskDetail!!.title!!,
                                it.taskDetail.description!!,
                                it.taskDetail.date!!
                            )
                            taskList.add(taskModel)
                        }
                        dao.clearDB()
                        dao.storeTaskListToDB(taskList)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("Kunal", "onError: Getting from Api Error")
                }

                override fun onComplete() {
                    Log.d("Kunal", "onComplete: Getting from Api Completed")
                }

            })
    }

    fun showTaskList(): Flowable<List<TaskModel>> {
        return dao.getTaskListFromDB()
    }

    fun showTaskListOnDate(date: String): Flowable<List<TaskModel>> {
        return dao.getDailyTaskListFromDB(date)
    }

}