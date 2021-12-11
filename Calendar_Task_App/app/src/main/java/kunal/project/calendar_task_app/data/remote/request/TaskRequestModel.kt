package kunal.project.calendar_task_app.data.remote.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TaskRequestModel(

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("date")
	val date: String? = null
)