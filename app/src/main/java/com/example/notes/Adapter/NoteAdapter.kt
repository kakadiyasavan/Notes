package com.example.notes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notes.DataBase.RoomDB
import com.example.notes.MainActivity
import com.example.notes.Models.Notes
import com.example.notes.R

class NoteAdapter(list: List<Notes>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<NoteAdapter.Noteholder>() {

    var list = list
    lateinit var db:RoomDB
    lateinit var context :Context

    class Noteholder(itemView: View) : ViewHolder(itemView){
        var txttitle = itemView.findViewById<TextView>(R.id.txttitle)
        var txtnote = itemView.findViewById<TextView>(R.id.txtnote)
        var cardnote = itemView.findViewById<TextView>(R.id.cardnote)
        var imgpin = itemView.findViewById<TextView>(R.id.imgpin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Noteholder {
        context = parent.context
        return Noteholder(LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Noteholder, position: Int) {

        db = RoomDB.getInteance(context)

        holder.txttitle.text = list.get(position).title
        holder.txtnote.text = list.get(position).note
        holder.cardnote.setCardBackgroundColor(list.get(position).color)

        if (list.get(position).pinned){
            holder.imgpin.setImageResource(R.drawable.pin_fill)
        }else{
            holder.imgpin.setImageResource(R.drawable.pin)
        }

        holder.imgpin.setOnClickListener {

            if (list.get(position).pinned){
                var data = Notes(list.get(position).title,list.get(position).notes.data,list.get(position).color,false)
                data.id = list.get(position).id
                db.notes().updateNote(data)
            }else{
                var data = Notes(list.get(position).title,list.get(position).notes.data,list.get(position).color,true)
                data.id = list.get(position).id
                db.notes().updateNote(data)
            }
            MainActivity.update()
        }

    }

    override fun getItemId(): Int {
        return list.size
    }
}