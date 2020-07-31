package br.com.edu.ifpr.yasuda.room.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @ColumnInfo(name = "title")
    var titl: String,
    @ColumnInfo(name = "description")
    var desc: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    val all get() = "${titl} ${desc}"
    val titleAndDesc get() = all

    override fun toString() = titleAndDesc
}