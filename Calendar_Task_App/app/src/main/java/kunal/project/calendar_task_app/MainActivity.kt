package kunal.project.calendar_task_app

import android.content.res.ColorStateList
import android.content.res.Resources
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kunal.project.calendar_task_app.utils.CalendarAdapter
import kunal.project.calendar_task_app.utils.DateClickListener
import java.time.LocalDate
import java.time.MonthDay
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), DateClickListener {

    private var dateList = ArrayList<String>()
    lateinit var currentDate: LocalDate
    lateinit var todaysDate: LocalDate
    lateinit var currentMonth: YearMonth
    lateinit var adapter: CalendarAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todaysDate = LocalDate.now()
        currentDate = todaysDate
        currentMonth = YearMonth.from(currentDate)
        initViewsAndClickListeners()
        populateCalendar()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun populateCalendar() {
        btnAddNewTask.isEnabled = false
        btnAddNewTask.setBackgroundTintList(
            this.getResources().getColorStateList(R.color.grey)
        )
        val today = "${YearMonth.from(todaysDate)}-${MonthDay.from(currentDate).dayOfMonth}"
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
        btnNextMonth.setOnClickListener {
            currentDate = currentDate.plusMonths(1)
            populateCalendar()
        }
        btnPrevMonth.setOnClickListener {
            currentDate = currentDate.minusMonths(1)
            populateCalendar()
        }
        btnAddNewTask.setOnClickListener {
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDateClicked(date: String, isSelected: Boolean) {
        if (isSelected) {
            Toast.makeText(this, date, Toast.LENGTH_SHORT).show()
            btnAddNewTask.setBackgroundTintList(
                this.getResources().getColorStateList(R.color.blue)
            )
            btnAddNewTask.isEnabled = true
        } else {
            Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show()
            btnAddNewTask.setBackgroundTintList(
                this.getResources().getColorStateList(R.color.grey)
            )
            btnAddNewTask.isEnabled = false
        }
    }
}