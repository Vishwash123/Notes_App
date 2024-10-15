package com.example.mynoteapp.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.Models.Note
import com.example.mynoteapp.Models.Todo
import com.example.mynoteapp.R



class TodosRvAdapter(val context: Context):RecyclerView.Adapter<TodosRvAdapter.todosViewHolder>() {

    private val list = ArrayList<Todo>()
    inner class todosViewHolder(view: View):RecyclerView.ViewHolder(view){
        val todoLayout = view.findViewById<CardView>(R.id.todo_cardview)
        val title = view.findViewById<TextView>(R.id.todo_title)
        val date = view.findViewById<TextView>(R.id.todo_date)
        val time = view.findViewById<TextView>(R.id.todo_time)
        val checkbox = view.findViewById<CheckBox>(R.id.todo_Checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todosViewHolder {
        return todosViewHolder(
            LayoutInflater.from(context).inflate(R.layout.todos_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: todosViewHolder, position: Int) {
        val currenttodo = list[position]
        holder.title.text = currenttodo.title
        holder.date.text = currenttodo.date
        holder.time.text = currenttodo.time

        holder.checkbox.setOnClickListener{
            if(holder.checkbox.isChecked)
            {
                holder.todoLayout.setCardBackgroundColor(ContextCompat.getColor(context,R.color.grey))
            }
            else
            {
                holder.todoLayout.setCardBackgroundColor(Color.WHITE)
            }
        }
    }


    interface NotesItemClickListener{
        fun onItemClicked(note:Note)
        fun onItemLongClicked(note:Note,cardView: CardView)
    }
}