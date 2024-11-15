package com.zack.bukucatatan.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zack.bukucatatan.Note
import com.zack.bukucatatan.R

class adapterNote (
    private var Notes : List<Note>,
    context: Context
): RecyclerView.Adapter<adapterNote.NoteViewHolder>() {

    class NoteViewHolder(itemview : View): RecyclerView.ViewHolder(itemview) {
        val judul : TextView = itemview.findViewById(R.id.judul)
        val isi : TextView = itemview.findViewById(R.id.isi)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,
            parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val noteItem = Notes[position]
        holder.judul.text = noteItem.title
        holder.isi.text = noteItem.content
    }

    fun refreshData(newNote : List<Note>){
        Notes = newNote
        notifyDataSetChanged()
    }

}