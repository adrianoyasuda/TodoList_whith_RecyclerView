package br.com.edu.ifpr.yasuda.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import br.com.edu.ifpr.yasuda.room.db.AppDatabase
import br.com.edu.ifpr.yasuda.room.db.dao.TaskDao
import br.com.edu.ifpr.yasuda.room.entities.Task
import br.com.edu.ifpr.yasuda.room.ui.TasksAdapter
import br.com.edu.ifpr.yasuda.room.ui.TasksAdapterListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TasksAdapterListener {

    lateinit var taskDao: TaskDao
    lateinit var adapter: TasksAdapter
    var taskEditing: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db =
            Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
            "task.db")
            .allowMainThreadQueries()
            .build()
        taskDao = db.taskDao()
        taskDao.getAll()

        bt_save.setOnClickListener { saveTask() }


        loadData()
    }


    private fun editTask(task: Task) {
        tf_title.setText(task.title)
        tf_desc.setText(task.description)

        tf_title.requestFocus()

        taskEditing = task
    }

    private fun saveTask(){
        val title = tf_title.text.toString()
        val description = tf_desc.text.toString()


        if (taskEditing != null){
            taskEditing?.let { task ->
                task.title = title
                task.description = description
                taskDao.update(task)

                adapter.updateTask(task)
            }

        }
        else{
            var task = Task(title, description, false)
            val id = taskDao.insert(task).toInt()
            task = taskDao.findById(id)!!

            val position = adapter.addTask(task)
            list_people.scrollToPosition(position)

        }
        loadData()
    }

    private fun loadData(){
        val task = taskDao.getAll()

        adapter = TasksAdapter(task.toMutableList(), this)
        list_people.adapter = adapter

        list_people.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        cleanFields()
    }

    private fun cleanFields(){
        tf_title.text.clear()
        tf_desc.text.clear()
        tf_title.requestFocus()

        taskEditing = null
    }

    override fun taskRemoved(task: Task) {
        taskDao.remove(task)
    }

    override fun taskClicked(task: Task) {
        editTask(task)
    }

    override fun taskUpdate(task: Task) {
        taskDao.update(task)
        loadData()
    }
}
