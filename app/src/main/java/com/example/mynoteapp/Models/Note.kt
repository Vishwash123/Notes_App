package com.example.mynoteapp.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "noteD_id") val id:Int?,
    @ColumnInfo(name = "noteD_title") var title:String?,
    @ColumnInfo(name = "noteD_content") var content:String?,
    @ColumnInfo(name = "noteD_date")val date: String?,
    @ColumnInfo(name = "noteD_selected")var selected:Int
    ):Serializable
