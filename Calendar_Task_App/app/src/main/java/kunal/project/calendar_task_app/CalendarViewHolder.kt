package kunal.project.calendar_task_app

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_layout.view.*

class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(date: String, today: String) {
        itemView.apply {
            tvDateItem.text = date.toString()
            if (date == today) {
                cvDateItem.strokeColor = (Color.parseColor("#717171"))
                tvDateItem.setTextColor(Color.parseColor("#3B4FBF"))
            }
            cvDateItem.setOnClickListener {
                cvDateItem.setStrokeColor(Color.parseColor("#3B4FBF"))
                cvDateItem.setCardBackgroundColor(Color.parseColor("#3B4FBF"))
                tvDateItem.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

}