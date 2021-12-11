package kunal.project.calendar_task_app.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TaskDetailModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)