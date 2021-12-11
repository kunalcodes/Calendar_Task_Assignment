package kunal.project.calendar_task_app

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var dateList = ArrayList<String>()
    @RequiresApi(Build.VERSION_CODES.O)
    val month : YearMonth = YearMonth.from(LocalDate.now())
    @RequiresApi(Build.VERSION_CODES.O)
    val year : Year = Year.from(LocalDate.now())

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvCalendarTitle.setOnClickListener {
            Log.d("Kunal", "onCreate: ${DateTimeFormatter.ofPattern("MMMM, YYYY").format(LocalDate.now())}")
            Log.d("Kunal", "onCreate: ${month}")
        }

    }
}