package br.com.edu.ifpr.yasuda.room.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    var title: String,
    var description: String,
    var done:Boolean

){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    val all get() = "${title} ${description}"
    val titleAndDesc get() = all

    override fun toString() = titleAndDesc
}