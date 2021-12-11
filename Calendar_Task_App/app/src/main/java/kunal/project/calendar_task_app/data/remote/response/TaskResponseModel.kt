package kunal.project.calendar_task_app.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TaskResponseModel(

	@field:SerializedName("task_id")
	val taskId: Int? = null,

	@field:SerializedName("task_detail")
	val taskDetail: TaskDetailModel? = null
)