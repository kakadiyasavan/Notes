package com.example.notes

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.notes.DataBase.RoomDB
import com.example.notes.Models.Notes
import com.example.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    companion object {
        lateinit var binding: ActivityMainBinding
        lateinit var db: RoomDB
        var noteList = ArrayList<Notes>()

        fun update() {
            var list = db.notes().getNotes()
            list = list.reversed()
            noteList.clear()
            for (l in list) {
                if (l.pinned) {
                    noteList.add(l)
                }
            }
            for (l in list) {
                if (!l.pinned) {
                    noteList.add(l)
                }
            }
            binding.rcvNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.rcvNotes.adapter = NotesAdapter(noteList)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.getInstance(this)

        binding.fabtnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddNoteActivity::class.java))
        }

        update()
    }

    override fun onResume() {
        super.onResume()
        update()
    }

}

