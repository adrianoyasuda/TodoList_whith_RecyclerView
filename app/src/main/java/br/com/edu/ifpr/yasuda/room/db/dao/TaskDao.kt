package br.com.edu.ifpr.yasuda.room.db.dao

import androidx.room.*
import br.com.edu.ifpr.yasuda.room.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id = :id LIMIT 1")
    fun findById(id: Int): Task?

    @Query("SELECT * FROM task WHERE title LIKE :title AND description LIKE :description")
    fun findByName(title: String, description: String): List<Task>

    @Insert
    fun  insert(task: Task): Long

    @Update
    fun  update(task: Task)

    @Delete
    fun remove(task: Task)


}