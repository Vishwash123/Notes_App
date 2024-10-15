package com.example.mynoteapp.Database

import androidx.lifecycle.LiveData
import com.example.mynoteapp.Models.Todo

class TodoRepository(private val todoDao: ToDo_Dao) {
    val allTodos : LiveData<List<Todo>> = todoDao.getAllTodos()

    suspend fun insert(todo: Todo){
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo){
        todoDao.delete(todo)
    }

    suspend fun update(todo: Todo){
        todoDao.update(todo.id,todo.title,todo.date,todo.time)

    }
}