package com.example.mynoteapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mynoteapp.Models.Note
import com.example.mynoteapp.Models.Todo


@Dao
interface ToDo_Dao {
    @Insert
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("SELECT * FROM todo_table ORDER BY todoD_id DESC")
    fun getAllTodos(): LiveData<List<Todo>>

    @Query("UPDATE todo_table SET todoD_title = :title, todoD_date = :date, todoD_time = :time WHERE todoD_id = :id ")
    suspend fun update(id:Int?,title:String?,date:String?,time:String?)
}