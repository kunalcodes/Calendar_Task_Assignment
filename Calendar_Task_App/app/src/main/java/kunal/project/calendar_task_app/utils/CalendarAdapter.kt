package kunal.project.calendar_task_app.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_layout.view.*
import kunal.project.calendar_task_app.R

class CalendarAdapter(
    val daysList: ArrayList<String>,
    val today: String,
    val dateClickListener: DateClickListener
) :
    RecyclerView.Adapter<CalendarViewHolder>() {

    private var lastCheckedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.date_layout, parent, false)
        return CalendarViewHolder(view)
    }


    /*
    * binding data to the item views
    * handling the single item selection condition in the list
    * calling the date selected methods in the parent activity through the interface
     */
    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = daysList[position]
        holder.itemView.apply {
            tvDateItem.text = date.split("-")[2]
            if (position == lastCheckedPosition) {
                cvDateItem.setStrokeColor(Color.parseColor("#3B4FBF"))
                cvDateItem.setCardBackgroundColor(Color.parseColor("#3B4FBF"))
                tvDateItem.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                if (date == today) {
                    cvDateItem.strokeColor = (Color.parseColor("#717171"))
                    cvDateItem.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                    tvDateItem.setTextColor(Color.parseColor("#3B4FBF"))
                } else {
                    cvDateItem.setStrokeColor(Color.parseColor("#FFFFFF"))
                    cvDateItem.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                    tvDateItem.setTextColor(Color.parseColor("#000000"))
                }
            }
            cvDateItem.setOnClickListener {
                if (holder.adapterPosition == lastCheckedPosition) {
                    var newDateFormat = date
                    if (date.split("-")[2].length < 2) {
                        newDateFormat =
                            "${date.split("-")[0]}-${date.split("-")[1]}-0${date.split("-")[2]}"
                    }
                    lastCheckedPosition = -1
                    notifyItemChanged(holder.adapterPosition)
                    dateClickListener.onDateClicked(newDateFormat, false)
                } else {
                    var newDateFormat = date
                    if (date.split("-")[2].length < 2) {
                        newDateFormat =
                            "${date.split("-")[0]}-${date.split("-")[1]}-0${date.split("-")[2]}"
                    }
                    val previousPosition = lastCheckedPosition
                    lastCheckedPosition = holder.adapterPosition
                    notifyItemChanged(holder.adapterPosition)
                    notifyItemChanged(previousPosition)
                    dateClickListener.onDateClicked(newDateFormat, true)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return daysList.size
    }
}