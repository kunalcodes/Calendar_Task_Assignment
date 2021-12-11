package kunal.project.calendar_task_app.data.remote.request

import com.google.gson.annotations.SerializedName

data class StoreTaskRequestModel(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("task")
	val task: TaskRequestModel? = null
)