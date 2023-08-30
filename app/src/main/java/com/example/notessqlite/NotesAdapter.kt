package com.example.notessqlite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class NotesAdapter(private var notes: List<NoteDB>, context: Context):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
        private val db:NoteDBHelper = NoteDBHelper(context)
        class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
            val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
            val editButton: ImageView = itemView.findViewById(R.id.editNoteBtn)
            val deleteButton: ImageView = itemView.findViewById(R.id.deleteNoteBtn)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
            return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            val note = notes[position]
            holder.titleTextView.text = note.title
            holder.contentTextView.text = note.content

            holder.editButton.setOnClickListener {
                val intent = Intent(holder.itemView.context, UpdateNote::class.java).apply{
                    putExtra("note_id", note.id)
                }
                holder.itemView.context.startActivity(intent)
            }
            holder.deleteButton.setOnClickListener {
                db.deleteNote(note.id)
                refreshData(db.getAllNotes())
                Toast.makeText(holder.itemView.context, "Note deleted", Toast.LENGTH_SHORT).show()
            }

    }

    fun refreshData(newNote: List<NoteDB>){
        notes = newNote
        notifyDataSetChanged()
    }
}