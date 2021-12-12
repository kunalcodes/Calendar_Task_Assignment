package kunal.project.calendar_task_app.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_launch.*
import kunal.project.calendar_task_app.R

class LaunchActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)


        // setting defult text for the text fields
        tvSelectedYear.text = "2011"
        tvSelectedMonth.text = "08"


        // setting the spinner adapter for year spinner
        val yearAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.yearList,
            android.R.layout.simple_spinner_item
        )
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSelectYear.adapter = yearAdapter
        spinnerSelectYear.onItemSelectedListener = this


        // setting the spinner adapter for month spinner
        val monthAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.monthList,
            android.R.layout.simple_spinner_item
        )
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSelectMonth.adapter = monthAdapter
        spinnerSelectMonth.onItemSelectedListener = this


        // send the selected date to main activity when proceed btn clicked
        btnSelectDate.setOnClickListener {
            val intent = Intent(this@LaunchActivity, MainActivity::class.java)
            intent.putExtra("month", tvSelectedMonth.text.toString().toInt())
            intent.putExtra("year", tvSelectedYear.text.toString().toInt())
            startActivity(intent)
        }
    }


    // handling the spinner selections and updating the text fields
    override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
        val Text = p0.getItemAtPosition(p2).toString()
        if (Text.length > 2) {
            tvSelectedYear.text = Text
        } else {
            tvSelectedMonth.text = Text
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}