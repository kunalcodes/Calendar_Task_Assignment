package kunal.project.calendar_task_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [TaskModel::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getDAO() : TaskDAO

}