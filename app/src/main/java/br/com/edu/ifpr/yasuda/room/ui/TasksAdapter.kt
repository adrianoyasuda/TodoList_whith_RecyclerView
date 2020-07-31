package br.com.edu.ifpr.yasuda.room.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
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
            itemView.txt_title.text = task.title
            itemView.txt_desc.text = task.description

            if (task.done == true){
                var card = itemView as CardView
                card.setCardBackgroundColor(Color.parseColor("#67ff59"))
            }
            else{
                var card = itemView as CardView
                card.setCardBackgroundColor(Color.parseColor("#ff3838"))
            }

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


            itemView.setOnLongClickListener {
                if(task.done == false) {
                    var card = itemView as CardView
                    card.setCardBackgroundColor(Color.parseColor("#67ff59"))
                    task.done = true
                    task.title = "[FEITO] ${task.title}"
                    listener.taskUpdate(task)
                    notifyItemChanged(tasks.indexOf(task))
                }
                else{
                    var card = itemView as CardView
                    card.setCardBackgroundColor(Color.parseColor("#ff3838"))
                    task.done = false
                    var titl = task.title.removePrefix("[FEITO]")
                    task.title = titl
                    listener.taskUpdate(task)
                    notifyItemChanged(tasks.indexOf(task))
                }
                true
            }


        }
    }

}