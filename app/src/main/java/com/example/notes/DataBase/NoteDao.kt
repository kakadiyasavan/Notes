package com.example.notes.DataBase

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes.Models.Notes

@Dao
interface NoteDao {

    @Insert
    fun addNote(notes:Notes)

    @Query("SELECT * FROM notes")
    fun getNote() : List<Notes>

    @Update
    fun updateNote(note:Notes)
}