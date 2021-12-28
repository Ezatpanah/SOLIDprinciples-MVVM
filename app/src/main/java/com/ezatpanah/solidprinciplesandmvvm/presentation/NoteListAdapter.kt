package com.ezatpanah.solidprinciplesandmvvm.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Constraints
import androidx.recyclerview.widget.RecyclerView
import com.ezatpanah.core.data.NoteModel
import com.ezatpanah.solidprinciplesandmvvm.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteListAdapter(var notes: ArrayList<NoteModel> , val action: ListAction) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateNotes(newNotes : List<NoteModel>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(inflater.inflate(R.layout.item_note, parent, false))

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount() = notes.size


    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val layout= itemView.findViewById<CardView>(R.id.noteLayout)
        private val noteTitle = itemView.findViewById<TextView>(R.id.title)
        private val noteContent = view.findViewById<TextView>(R.id.content)
        private val noteDate = view.findViewById<TextView>(R.id.date)

        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(noteModel: NoteModel) {
            noteTitle.text = noteModel.title
            noteContent.text = noteModel.content
            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(noteModel.updateTime)
            noteDate.text = "Last updated : ${sdf.format(resultDate)}"


            layout.setOnClickListener {
                action.onClick(noteModel.id)
            }



        }

    }


}