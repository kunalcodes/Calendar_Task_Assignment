package kunal.project.calendar_task_app.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetTasksResponseModel(

	@field:SerializedName("tasks")
	val tasks: List<TaskResponseModel?>? = null
)