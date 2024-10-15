package com.example.mynoteapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynoteapp.Models.Todo
import com.example.mynoteapp.Utilities.todoDatabaseName


@Database(entities = arrayOf(Todo::class), version = 1, exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun getTodoDao():ToDo_Dao

    companion object{
        @Volatile
        private var INSTANCE:TodoDatabase? = null

        fun getTodoDatabase(context: Context):TodoDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,TodoDatabase::class.java,
                    todoDatabaseName).build()
                INSTANCE=instance
                instance
            }
        }
    }
}