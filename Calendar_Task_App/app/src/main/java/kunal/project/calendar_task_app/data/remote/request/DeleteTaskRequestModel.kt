package kunal.project.calendar_task_app.data.remote.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DeleteTaskRequestModel(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("task_id")
	val taskId: Int? = null
)