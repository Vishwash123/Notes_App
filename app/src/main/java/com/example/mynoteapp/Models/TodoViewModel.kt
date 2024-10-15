package com.example.mynoteapp.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynoteapp.Database.NoteRepository
import com.example.mynoteapp.Database.TodoDatabase
import com.example.mynoteapp.Database.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application):AndroidViewModel(application) {
    private val todoRepository : TodoRepository

    val allTodos:LiveData<List<Todo>>

    init{
        val dao = TodoDatabase.getTodoDatabase(application).getTodoDao()
        todoRepository = TodoRepository(dao)
        allTodos = todoRepository.allTodos
    }


    fun delete(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        todoRepository.delete(todo)
    }

    fun insert(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        todoRepository.insert(todo)
    }

    fun update(todo: Todo) = viewModelScope.launch(Dispatchers.IO){
        todoRepository.update(todo)
    }

}