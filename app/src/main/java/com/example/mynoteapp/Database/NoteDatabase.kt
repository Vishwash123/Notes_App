package com.example.mynoteapp.Database

import MIGRATION
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynoteapp.Models.Note
import com.example.mynoteapp.Utilities.noteDatabaseName


@Database(entities = arrayOf(Note::class), version = 3, exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun getNoteDao():Note_Dao

    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase? = null

        fun getNoteDatabase(context: Context):NoteDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java, noteDatabaseName).build()
                INSTANCE=instance
                instance
            }
        }
    }


}