package kunal.project.calendar_task_app.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.date_layout.view.*
import kotlinx.android.synthetic.main.task_layout.view.*
import kunal.project.calendar_task_app.R
import kunal.project.calendar_task_app.data.local.TaskModel

class TaskAdapter(
    val taskList: ArrayList<TaskModel>,
    val taskClickListener: TaskClickListener
) :
    RecyclerView.Adapter<TaskViewHolder>() {

    private var lastCheckedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.itemView.apply {
            tvTaskItemName.text = task.title
            if (position == lastCheckedPosition) {
//                cvDateItem.setStrokeColor(Color.parseColor("#3B4FBF"))
//                cvDateItem.setCardBackgroundColor(Color.parseColor("#3B4FBF"))
//                tvDateItem.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
//                cvDateItem.setStrokeColor(Color.parseColor("#FFFFFF"))
//                cvDateItem.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
//                tvDateItem.setTextColor(Color.parseColor("#000000"))
            }
        }
        holder.itemView.setOnClickListener {
            if (holder.adapterPosition == lastCheckedPosition) {
                lastCheckedPosition = -1
                notifyItemChanged(holder.adapterPosition)
                taskClickListener.onTaskClicked(task, false)
            } else {
                val previousPosition = lastCheckedPosition
                lastCheckedPosition = holder.adapterPosition
                notifyItemChanged(holder.adapterPosition)
                notifyItemChanged(previousPosition)
                taskClickListener.onTaskClicked(task, true)
            }
        }
    }


    override fun getItemCount(): Int {
        return taskList.size
    }
}