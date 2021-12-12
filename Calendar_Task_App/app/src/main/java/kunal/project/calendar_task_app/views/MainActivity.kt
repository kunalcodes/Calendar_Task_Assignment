package kunal.project.calendar_task_app.views

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_task_list.*
import kunal.project.calendar_task_app.R
import kunal.project.calendar_task_app.data.local.TaskModel
import kunal.project.calendar_task_app.data.remote.request.StoreTaskRequestModel
import kunal.project.calendar_task_app.data.remote.request.TaskRequestModel
import kunal.project.calendar_task_app.utils.CalendarAdapter
import kunal.project.calendar_task_app.utils.DateClickListener
import kunal.project.calendar_task_app.utils.TaskAdapter
import kunal.project.calendar_task_app.utils.TaskClickListener
import kunal.project.calendar_task_app.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DateClickListener, TaskClickListener {

    private var dateList = ArrayList<String>()
    lateinit var currentDate: LocalDate
    lateinit var todaysDate: LocalDate
    lateinit var currentMonth: YearMonth
    lateinit var adapter: CalendarAdapter
    lateinit var sendDate : String
    private var taskList = ArrayList<TaskModel>()
    lateinit var taskAdapter: TaskAdapter
    private val viewModel : TaskViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting the selected month and year from previous screen
        val monthVal = intent.getIntExtra("month", 11)
        val yearVal = intent.getIntExtra("year", 2011)

        // getting current date from the local date object to display in the calendar
        todaysDate = LocalDate.now()

        //setting the calendar date to selected dates from the previous screen
        currentDate = LocalDate.of(yearVal, monthVal, 1)
        currentMonth = YearMonth.from(currentDate)
        initViewsAndClickListeners()
        populateCalendar()
    }


    /*
    * generating the list for calendar and setting the adapter
    * updating the ui for previous/next months
    * disabling the addTask by default when no date is selected
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun populateCalendar() {
        btnAddNewTask.isEnabled = false
        btnAddNewTask.setBackgroundTintList(
            this.getResources().getColorStateList(R.color.grey)
        )
        val today = "${YearMonth.from(todaysDate)}-${MonthDay.from(todaysDate).dayOfMonth}"
        currentMonth = YearMonth.from(currentDate)
        tvCalendarMonth.text = DateTimeFormatter.ofPattern("MMMM, YYYY").format(currentDate)
        dateList.clear()
        for (i in 1..currentMonth.lengthOfMonth()) {
            dateList.add("${currentMonth}-$i")
        }
        adapter = CalendarAdapter(dateList, today, this)
        val layoutManager = GridLayoutManager(this, 7)
        recyclerViewCalendar.adapter = adapter
        recyclerViewCalendar.layoutManager = layoutManager
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViewsAndClickListeners() {
        // advance current month by 1
        btnNextMonth.setOnClickListener {
            currentDate = currentDate.plusMonths(1)
            populateCalendar()
        }
        // reduce value of current month by 1
        btnPrevMonth.setOnClickListener {
            currentDate = currentDate.minusMonths(1)
            populateCalendar()
        }
        // open new activity on clicking addTask btn
        btnAddNewTask.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            intent.putExtra("date", sendDate)
            startActivity(intent)
        }

        // go to task list activity
        btnSeeTaskList.setOnClickListener {
            val intent = Intent(this@MainActivity, TaskListActivity::class.java)
            startActivity(intent)
        }
    }

    /*
    * fetching the tasks on the current date if the date is selected
    * clear the list if no date is selected
    * enable/ disable the addTask btn when date selected/deselected
     */
    override fun onDateClicked(date: String, isSelected: Boolean) {
        if (isSelected) {
            tvTaskListCurrentDate.text = "Tasks on Date : $date"
            viewModel.showTaskListOnDate(date).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                taskList.clear()
                taskList = it as ArrayList<TaskModel>
                setTaskRecyclerViewAdapter()
            }
            btnAddNewTask.setBackgroundTintList(
                this.getResources().getColorStateList(R.color.blue)
            )
            sendDate = date
            btnAddNewTask.isEnabled = true
        } else {
            tvTaskListCurrentDate.text = "No Date Selected"
            taskList.clear()
            setTaskRecyclerViewAdapter()
            btnAddNewTask.setBackgroundTintList(
                this.getResources().getColorStateList(R.color.grey)
            )
            btnAddNewTask.isEnabled = false
        }
    }

    /*
    * setting the daily task adapter and layout manager
     */
    private fun setTaskRecyclerViewAdapter() {
        taskAdapter = TaskAdapter(taskList, this)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewDailyTask.adapter = taskAdapter
        recyclerViewDailyTask.layoutManager = layoutManager
    }

    /*
    * delete the task if the delete btn is clicked
    * this method is called from the task adapter when task item deleted btn is clicked
     */
    override fun onDeleteClicked(taskModel: TaskModel) {
        viewModel.deleteTask(taskModel)
    }
}