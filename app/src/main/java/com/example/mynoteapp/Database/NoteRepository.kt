package com.example.mynoteapp.Database

import androidx.lifecycle.LiveData
import com.example.mynoteapp.Models.Note

class NoteRepository(private val noteDao: Note_Dao) {
    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

//    suspend fun delete(note: Note){
//        noteDao.delete(note)
//    }
    suspend fun deleteMultiple(notes:List<Note>)
    {
        noteDao.deleteMultiple(notes)
    }

    suspend fun update(note: Note)
    {
        noteDao.update(note.id,note.title,note.content)
    }
}