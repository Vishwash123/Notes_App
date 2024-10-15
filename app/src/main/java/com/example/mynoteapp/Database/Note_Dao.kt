package com.example.mynoteapp.Database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mynoteapp.Models.Note

@Dao
interface Note_Dao {

    @Insert
    suspend fun insert(note: Note)

//    @Delete
//    suspend fun delete(note: Note)

    @Delete
    suspend fun deleteMultiple(notes:List<Note>)

    @Query("SELECT * FROM notes_table ORDER BY noteD_id DESC")
    fun getAllNotes():LiveData<List<Note>>

    @Query("UPDATE notes_table SET noteD_title = :title, noteD_content = :content WHERE noteD_id = :id ")
    suspend fun update(id:Int?,title:String?,content:String?)

}