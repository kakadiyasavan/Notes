package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import com.example.notes.databinding.ActivityMainBinding

class AddNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddNoteBinding
    var selectColor = 0

    lateinit var db: RoomDB

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.getInstance(this)

        binding.cardAdd.setOnClickListener {

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a")
            val current = LocalDateTime.now().format(formatter)

            var note = Notes(
                binding.edtTitles.text.toString(),
                binding.edtNotes.text.toString(), current, selectColor, false
            )
            db.notes().addNote(note)
            finish()
        }

        binding.cardColors.setOnClickListener {

            MaterialColorPickerDialog
                .Builder(this)
                .setTitle("Pick Theme")
                .setColorShape(ColorShape.SQAURE)
                .setColorListener { color, colorHex ->
                    binding.cardColors.setCardBackgroundColor(color)
                    selectColor = color
                }
                .show()

        }

    }
}
