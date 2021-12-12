package kunal.project.calendar_task_app.data.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TaskDAO {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun storeTaskToDB(taskModel: TaskModel)

    @Delete
    fun deleteTaskFromDB(taskModel: TaskModel)

    @Query ("Select * from tasks")
    fun getTaskListFromDB() : Flowable<List<TaskModel>>

    @Query("delete from tasks")
    fun clearDB()

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun storeTaskListToDB(taskModelList: ArrayList<TaskModel>)

    @Query ("Select * from tasks where date = :date")
    fun getDailyTaskListFromDB(date: String): Flowable<List<TaskModel>>

}