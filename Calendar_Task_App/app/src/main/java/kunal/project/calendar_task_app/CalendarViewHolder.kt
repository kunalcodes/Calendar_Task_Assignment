package kunal.project.calendar_task_app

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_layout.view.*

class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(date: String) {
        itemView.tvDateItem.text = date.toString()
    }

}