package kunal.project.calendar_task_app.data.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DeleteTaskResponseModel(

	@field:SerializedName("status")
	val status: String? = null
)