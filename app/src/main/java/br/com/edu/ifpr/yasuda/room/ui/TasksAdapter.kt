package br.com.edu.ifpr.yasuda.room.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.edu.ifpr.yasuda.room.R
import br.com.edu.ifpr.yasuda.room.entities.Task
import kotlinx.android.synthetic.main.item_person.view.*

class TasksAdapter(private var tasks: MutableList<Task>, private  var listener: TasksAdapterListener):
        RecyclerView.Adapter<TasksAdapter.ViewHolder>(){

    fun addTask(task: Task): Int {
        tasks.add(0, task)
        notifyItemInserted(0)
        return 0
    }

    fun updateTask(task: Task){
        val position = tasks.indexOf(task)
        notifyItemChanged(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_person, parent, false)
        )

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.fillUI(task)
    }

    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        fun fillUI(task: Task){
            itemView.txt_title.text = task.titl
            itemView.txt_desc.text = task.desc

            itemView.bt_remove.setOnClickListener{
                with(this@TasksAdapter){
                    val position = tasks.indexOf(task)
                    tasks.removeAt(position)
                    notifyItemRemoved(position)
                    listener.taskRemoved(task)
                }
            }

            itemView.setOnClickListener{
                with(this@TasksAdapter){
                    tasks.indexOf(task)
                    listener.taskClicked(task)
                }
            }


        }
    }

}