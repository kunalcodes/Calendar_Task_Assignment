package kunal.project.calendar_task_app

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var dateList = ArrayList<String>()
    lateinit var currentDate : LocalDate
    lateinit var currentMonth : YearMonth
    lateinit var currentYear : Year
    lateinit var adapter : CalendarAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentDate = LocalDate.now()
        currentMonth = YearMonth.from(currentDate)
        initViewsAndClickListeners()
        populateCalendar()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun populateCalendar() {
        tvCalendarMonth.text = DateTimeFormatter.ofPattern("MMMM, YYYY").format(currentDate)
        dateList.clear()
        for (i in 1..currentMonth.lengthOfMonth()){
            dateList.add(i.toString())
        }
        adapter = CalendarAdapter(dateList)
        val layoutManager = GridLayoutManager(this, 7)
        recyclerViewCalendar.adapter = adapter
        recyclerViewCalendar.layoutManager = layoutManager
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViewsAndClickListeners() {
        btnNextMonth.setOnClickListener {
            currentMonth = currentMonth.plusMonths(1)
            currentDate = currentDate.plusMonths(1)
            populateCalendar()
        }
        btnPrevMonth.setOnClickListener {
            currentMonth = currentMonth.minusMonths(1)
            currentDate = currentDate.minusMonths(1)
            populateCalendar()
        }
    }
}