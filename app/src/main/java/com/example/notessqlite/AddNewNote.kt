package com.example.notessqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqlite.databinding.ActivityAddNewNoteBinding

class AddNewNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewNoteBinding
    private  lateinit var db: NoteDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDBHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val desc = binding.contentEditText.text.toString()
            val note = NoteDB(0, title, desc)

            db.insertNote(note)
            finish()

            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
        }

    }
}