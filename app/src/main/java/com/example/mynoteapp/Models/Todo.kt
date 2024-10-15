package com.example.mynoteapp.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date


@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "todoD_id") val id:Int?,
    @ColumnInfo(name = "todoD_title") val title:String?,
    @ColumnInfo(name = "todoD_date") val date: String?,
    @ColumnInfo(name = "todoD_time") val time: String?
)
