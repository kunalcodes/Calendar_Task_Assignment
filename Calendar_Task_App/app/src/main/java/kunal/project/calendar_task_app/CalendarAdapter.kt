package kunal.project.calendar_task_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_layout.view.*

class CalendarAdapter(val daysList : ArrayList<String>, val today : String) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.date_layout, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = daysList[position]
        holder.setData(date, today)
    }

    override fun getItemCount(): Int {
        return daysList.size
    }
}