package kunal.project.calendar_task_app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kunal.project.calendar_task_app.data.local.TaskModel
import kunal.project.calendar_task_app.utils.TaskClickListener
import kunal.project.calendar_task_app.viewmodel.TaskViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_task_list.*
import kunal.project.calendar_task_app.R
import kunal.project.calendar_task_app.utils.TaskAdapter


@AndroidEntryPoint
class TaskListActivity : AppCompatActivity(), TaskClickListener {

    private var taskList = ArrayList<TaskModel>()
    lateinit var adapter: TaskAdapter
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        /*
        * get the list of all tasks from the db wrapped as rxjava flowable
        * update the list and the ui when there is any change observed
        */
        viewModel.showTaskList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                taskList.clear()
                taskList = it as ArrayList<TaskModel>
                setRecyclerViewAdapter()
            }
    }

    // set the recyclerview adapter and layout manager
    private fun setRecyclerViewAdapter() {
        adapter = TaskAdapter(taskList, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewTask.adapter = adapter
        recyclerViewTask.layoutManager = layoutManager
    }


    // call viewModel delete method when delete is clicked for any item in the list
    override fun onDeleteClicked(taskModel: TaskModel) {
        viewModel.deleteTask(taskModel)
    }
}