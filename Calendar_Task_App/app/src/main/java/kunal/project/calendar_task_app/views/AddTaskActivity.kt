package kunal.project.calendar_task_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_add_task.*
import kunal.project.calendar_task_app.R
import kunal.project.calendar_task_app.data.remote.request.StoreTaskRequestModel
import kunal.project.calendar_task_app.data.remote.request.TaskRequestModel
import kunal.project.calendar_task_app.viewmodel.TaskViewModel

@AndroidEntryPoint
class AddTaskActivity : AppCompatActivity() {

    private lateinit var sendDate: String
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        //receiving the date of the task sent from the main activity
        sendDate = intent.getStringExtra("date")!!

        btnAddTask.setOnClickListener {
            addTask()
        }
    }

    /*
    * check for validation of the edit text fields
    * if all fields are valid, then add the new task
    * finish the activity after done
     */
    private fun addTask() {
        if (isTitleValid() && isDescriptionValid() && sendDate != null) {
            val taskRequestModel = TaskRequestModel(
                etAddTaskTitle.text.toString(),
                etAddTaskDesc.text.toString(),
                sendDate
            )
            val storeTaskRequestModel = StoreTaskRequestModel(1014, taskRequestModel)
            viewModel.storeTask(storeTaskRequestModel)
            finish()
        }
    }

    // set error message if title field is empty
    private fun isTitleValid(): Boolean {
        return if (etAddTaskTitle.text.isNotEmpty()) {
            true
        } else {
            etAddTaskTitle.error = "Title can't be empty!"
            false
        }
    }

    // set error message if desc field is empty
    private fun isDescriptionValid(): Boolean {
        return if (etAddTaskDesc.text.isNotEmpty()) {
            true
        } else {
            etAddTaskDesc.error = "Add a description!"
            false
        }
    }


}