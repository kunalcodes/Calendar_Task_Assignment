package kunal.project.calendar_task_app.repo

import android.util.Log
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kunal.project.calendar_task_app.data.local.TaskDAO
import kunal.project.calendar_task_app.data.local.TaskModel
import kunal.project.calendar_task_app.data.remote.ApiService
import kunal.project.calendar_task_app.data.remote.request.StoreTaskRequestModel
import kunal.project.calendar_task_app.data.remote.response.StoreTaskResponseModel
import javax.inject.Inject


class TaskRepository @Inject constructor(val dao: TaskDAO, val apiService: ApiService) {

    private val authKey = "d95a5f11-13ef-419a-be7e-5a64cac73624"


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
                        val task = storeTaskRequestModel.task!!
                        val taskModel = TaskModel(1, task.title!!, task.description!!, task.date!!)
                        dao.storeTaskToDB(taskModel)
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
}