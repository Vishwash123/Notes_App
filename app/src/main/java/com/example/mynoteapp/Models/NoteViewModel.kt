package com.example.mynoteapp.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mynoteapp.Database.NoteDatabase
import com.example.mynoteapp.Database.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {

    private val notesRepository:NoteRepository
    val allNotes : LiveData<List<Note>>

    init{
        val dao = NoteDatabase.getNoteDatabase(application).getNoteDao()
        notesRepository = NoteRepository(dao)
        allNotes = notesRepository.allNotes
    }


//    fun deleteNote(note: Note) = viewModelScope.launch ( Dispatchers.IO ){
//        notesRepository.delete(note)
//    }

    fun deleteMultiple(notes:List<Note>) = viewModelScope.launch(Dispatchers.IO){
        notesRepository.deleteMultiple(notes)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        notesRepository.insert(note)
    }


    fun updateNote(note:Note) = viewModelScope.launch(Dispatchers.IO){
        notesRepository.update(note)
    }

}