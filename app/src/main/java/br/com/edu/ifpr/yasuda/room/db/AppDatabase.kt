package br.com.edu.ifpr.yasuda.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.edu.ifpr.yasuda.room.db.dao.TaskDao
import br.com.edu.ifpr.yasuda.room.entities.Task

@Database(entities = arrayOf(Task::class),version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
