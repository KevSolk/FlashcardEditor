package com.example.flashcardeditor.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcardeditor.R
import com.example.flashcardeditor.models.StacksListAdapterModel


class StacksListAdapter (private val dataSet: ArrayList<StacksListAdapterModel>, private val listener: (StacksListAdapterModel, position: Int) -> Unit) :
        RecyclerView.Adapter<StacksListAdapter.StackViewHolder>() {



    class StackViewHolder(view: View) : RecyclerView.ViewHolder(view){
        /*val chapView: TextView = view.findViewById(R.id.chapterText)
        val noteView: TextView = view.findViewById(R.id.noteText)
        val titleView: TextView = view.findViewById(R.id.titleText)*/
        fun bind(stack: StacksListAdapterModel) {
            /*noteView.text = note.noteView
            titleView.text = note.titleView
            chapView.text =  note.chapView*/

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StacksListAdapter.StackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stacks_list_item, parent, false)

        return StackViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: StacksListAdapter.StackViewHolder, position: Int) {
        val note = dataSet[position]

        if(note != null){
            holder.bind(note)
        }

        holder.itemView.setOnClickListener{ listener(dataSet[position], position)}
    }
}