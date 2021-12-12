package kunal.project.calendar_task_app.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            tvTaskItemTitle.text = task.title
            tvTaskItemDate.text = "Task on date: ${(task.date)}"
            tvTaskItemDesc.text = task.desc
            if (position == lastCheckedPosition) {
                layoutTaskMenu.setBackgroundColor(Color.parseColor("#3B4FBF"))
                tvTaskItemTitle.setTextColor(Color.parseColor("#FFFFFF"))
//                tvTaskItemTitle.setTextColor(Color.parseColor("#FFFFFF"))
                layoutTaskDetail.visibility = View.VISIBLE
            } else {
                layoutTaskMenu.setBackgroundColor(Color.parseColor("#FFFFFF"))
                tvTaskItemTitle.setTextColor(Color.parseColor("#000000"))
                layoutTaskDetail.visibility = View.GONE
            }
        }
        holder.itemView.layoutTaskMenu.setOnClickListener {
            if (holder.adapterPosition == lastCheckedPosition) {
                lastCheckedPosition = -1
                notifyItemChanged(holder.adapterPosition)
            } else {
                val previousPosition = lastCheckedPosition
                lastCheckedPosition = holder.adapterPosition
                notifyItemChanged(holder.adapterPosition)
                notifyItemChanged(previousPosition)
            }
        }
        holder.itemView.tvTaskItemDelete.setOnClickListener {
            taskClickListener.onDeleteClicked(task)
        }
    }


    override fun getItemCount(): Int {
        return taskList.size
    }
}