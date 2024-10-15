////package com.example.mynoteapp.Adapter
////
////import android.annotation.SuppressLint
////import android.content.Context
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import android.widget.TextView
////import android.widget.Toast
////import androidx.cardview.widget.CardView
////import androidx.recyclerview.widget.RecyclerView
////import com.example.mynoteapp.Models.Note
////import com.example.mynoteapp.Models.NoteViewModel
////import com.example.mynoteapp.R
////
////
////class NotesRvAdapter(val context: Context, val listener: NotesItemClickListener,val viewModel:NoteViewModel, private val showMenuDelete:(Boolean)->Unit):RecyclerView.Adapter<NotesRvAdapter.notesViewHolder>() {
////    private val list = ArrayList<Note>()
////    private val fullList  = ArrayList<Note>()
////    private var isEnable = false
////    private val selectedNotes = mutableListOf<Int>()
////
////
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
////        return notesViewHolder(
////            LayoutInflater.from(context).inflate(R.layout.notes_item,parent,false)
////        )
////    }
////
////    override fun getItemCount(): Int {
////       return list.size
////    }
////
////    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
////        val currentnote = list[position]
////        holder.title.text = currentnote.title
////        holder.content.text = currentnote.content
////        holder.date.text = currentnote.date
////        holder.title.isSelected = true
////        holder.date.isSelected = true
////        holder.notesLayout.setOnClickListener{
////
////            if (isEnable) {
////                if (selectedNotes.contains(position)) {
////                    deselectItem(holder,currentnote, position)
////                } else {
////                    selectItem(holder, currentnote, position)
////                }
////            } else {
////                listener.onNoteClickListener(list[holder.adapterPosition])
////            }
////
////        }
////
////        holder.notesLayout.setOnLongClickListener{
//////            listener.onNoteLongClickListener(list[holder.adapterPosition],holder.notesLayout)
//////            true
////            selectItem(holder,currentnote,position)
////            true
////        }
////    }
////
////    private fun selectItem(holder: notesViewHolder, currentNote: Note, position: Int) {
////        isEnable = true
////        selectedNotes.add(position)
////        currentNote.selected = 1
////        showMenuDelete(true)
////       updateItemUI(holder, true)
////        Toast.makeText(context,"Seleceted Items : ${selectedNotes.size}",Toast.LENGTH_SHORT).show()
////    }
////
////    private fun deselectItem(holder: notesViewHolder,currentNote: Note, position: Int) {
////        selectedNotes.remove(position)
////        currentNote.selected = 0
////        updateItemUI(holder, false)
////        if (selectedNotes.isEmpty()) {
////
////
////            showMenuDelete(false)
////            isEnable = false
////
////        }
////        Toast.makeText(context,"Seleceted Items : ${selectedNotes.size}",Toast.LENGTH_SHORT).show()
////
////   }
////
////    private fun updateItemUI(holder: notesViewHolder, isSelected: Boolean) {
////        if (isSelected) {
////            holder.notesLayout.setCardBackgroundColor(
////                holder.itemView.context.resources.getColor(android.R.color.holo_blue_light)
////            )
////        }
//////        else if(isEnable) {
//////            holder.notesLayout.setCardBackgroundColor(
//////                holder.itemView.context.resources.getColor(android.R.color.white)
//////            )
//////        }
////        else
////        {
////            holder.notesLayout.setCardBackgroundColor(
////                holder.itemView.context.resources.getColor(android.R.color.white)
////            )
////        }
////    }
////
////
////    @SuppressLint("NotifyDataSetChanged")
////    fun updateList(newList:List<Note>){
////        fullList.clear()
////        fullList.addAll(newList)
////        list.clear()
////        list.addAll(fullList)
////        notifyDataSetChanged()
////
////    }
////
////
////    @SuppressLint("NotifyDataSetChanged")
////    fun filterList(search:String)
////    {
////        list.clear()
////        for (item in fullList)
////        {
////            if(item.title?.lowercase()?.contains(search.lowercase())==true  || item.content?.lowercase()?.contains(search.lowercase())==true)
////            {
////                list.add(item)
////            }
////        }
////        notifyDataSetChanged()
////    }
////
////    @SuppressLint("NotifyDataSetChanged")
////    fun deleteMultiple()
////    {
////
////        deleteSelectedItems()
////
////        notifyDataSetChanged()
////    }
////    fun getSelectedNotes(): List<Note> {
////        return selectedNotes.map { list[it] }
////    }
////
////    @SuppressLint("NotifyDataSetChanged")
////    fun deleteSelectedItems() {
////        val notesToDelete = getSelectedNotes()
////        viewModel.deleteMultiple(notesToDelete)
////        list.removeAll { it.selected == 1 }
////        fullList.removeAll { it.selected == 1 }
////        isEnable = false
////
////        selectedNotes.clear()
////
////    }
////
////
////
////
////
////
////    inner class notesViewHolder(view: View):RecyclerView.ViewHolder(view){
////        val notesLayout = view.findViewById<CardView>(R.id.notes_cardview)
////        val title = view.findViewById<TextView>(R.id.notes_title)
////        val content = view.findViewById<TextView>(R.id.notes_content)
////        val date = view.findViewById<TextView>(R.id.notes_date)
////    }
////
////    interface NotesItemClickListener{
////        fun onNoteClickListener(note: Note)
//////        fun onNoteLongClickListener(note: Note,cardView:CardView)
////
////    }
////}
//package com.example.mynoteapp.Adapter
//
//import android.annotation.SuppressLint
//import android.content.Context
//import android.graphics.Color
//import android.text.Spannable
//import android.text.SpannableString
//import android.text.style.BackgroundColorSpan
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import android.widget.Toast
//import androidx.cardview.widget.CardView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.mynoteapp.Models.Note
//import com.example.mynoteapp.Models.NoteViewModel
//import com.example.mynoteapp.R
//import java.util.Locale
//
//class NotesRvAdapter(
//    val context: Context,
//    val listener: NotesItemClickListener,
//    val viewModel: NoteViewModel,
//    private val showMenuDelete: (Boolean) -> Unit
//) : RecyclerView.Adapter<NotesRvAdapter.notesViewHolder>() {
//
//    private val list = ArrayList<Note>()
//    private val fullList = ArrayList<Note>()
//    private var isEnable = false
//    private val selectedNotes = mutableListOf<Int>()
//
//    override fun getItemId(position: Int): Long {
//        return list[position].id?.toLong() ?: -1
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
//        return notesViewHolder(
//            LayoutInflater.from(context).inflate(R.layout.notes_item, parent, false)
//        )
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
//        val currentNote = list[position]
//        holder.title.text = currentNote.title
//        holder.content.text = currentNote.content
//        holder.date.text = currentNote.date
//        holder.title.isSelected = true
//        holder.date.isSelected = true
//
//        updateItemUI(holder, currentNote.selected == 1)
//
//        holder.notesLayout.setOnClickListener {
//            if (isEnable) {
//                if (selectedNotes.contains(position)) {
//                    deselectItem(holder, currentNote, position)
//                } else {
//                    selectItem(holder, currentNote, position)
//                }
//            } else {
//                listener.onNoteClickListener(list[holder.adapterPosition])
//            }
//        }
//
//        holder.notesLayout.setOnLongClickListener {
//
//            selectItem(holder, currentNote, position)
//            true
//        }
//    }
//
//    private fun selectItem(holder: notesViewHolder, currentNote: Note, position: Int) {
//        isEnable = true
//        selectedNotes.add(position)
//        currentNote.selected = 1
//        showMenuDelete(true)
//        updateItemUI(holder, true)
//        Toast.makeText(context, "Selected Items: ${selectedNotes.size}", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun deselectItem(holder: notesViewHolder, currentNote: Note, position: Int) {
//        selectedNotes.remove(position)
//        currentNote.selected = 0
//        updateItemUI(holder, false)
//        if (selectedNotes.isEmpty()) {
//            showMenuDelete(false)
//            isEnable = false
//        }
//        Toast.makeText(context, "Selected Items: ${selectedNotes.size}", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun updateItemUI(holder: notesViewHolder, isSelected: Boolean) {
//        val context = holder.itemView.context
//        val defaultColor = context.resources.getColor(android.R.color.white)
//        val selectedColor = context.resources.getColor(android.R.color.holo_blue_light)
//
//        if (isSelected) {
//            holder.notesLayout.setCardBackgroundColor(selectedColor)
//        } else {
//            holder.notesLayout.setCardBackgroundColor(defaultColor)
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun updateList(newList: List<Note>) {
//        fullList.clear()
//        fullList.addAll(newList)
//        list.clear()
//        list.addAll(fullList)
//        notifyDataSetChanged()
//    }
//
////    @SuppressLint("NotifyDataSetChanged")
////    fun filterList(search: String) {
////        list.clear()
////        for (item in fullList) {
////            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
////                item.content?.lowercase()?.contains(search.lowercase()) == true) {
////                list.add(item)
////            }
////        }
////        notifyDataSetChanged()
////    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun filterList(search: String) {
//        list.clear()
//        for (item in fullList) {
//            if (item.title?.contains(search, ignoreCase = true) == true ||
//                item.content?.contains(search, ignoreCase = true) == true) {
//                val highlightedTitle = highlightText(item.title, search)
//                val highlightedContent = highlightText(item.content, search)
//                item.title = highlightedTitle.toString()
//                item.content = highlightedContent.toString()
//                list.add(item)
//            }
//        }
//        notifyDataSetChanged()
//    }
//
//    private fun highlightText(text: String?, search: String): SpannableString {
//        val spannableString = SpannableString(text)
//        if (!text.isNullOrEmpty() && search.isNotEmpty()) {
//            val startIndex = text.toLowerCase(Locale.ROOT).indexOf(search.toLowerCase(Locale.ROOT))
//            if (startIndex != -1) {
//                val endIndex = startIndex + search.length
//                spannableString.setSpan(BackgroundColorSpan(Color.YELLOW), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            }
//        }
//        return spannableString
//    }
//    @SuppressLint("NotifyDataSetChanged")
//    fun deleteMultiple() {
//        deleteSelectedItems()
//        notifyDataSetChanged()
//    }
//
//    fun getSelectedNotes(): List<Note> {
//        return selectedNotes.map { list[it] }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun deleteSelectedItems() {
//        val notesToDelete = getSelectedNotes()
//        viewModel.deleteMultiple(notesToDelete)
//        for (note in list) {
//            note.selected = 0 // Reset the selected property
//        }
//        list.removeAll { it.selected == 1 }
//        fullList.removeAll { it.selected == 1 }
//        isEnable = false
//        selectedNotes.clear()
//    }
//
//    fun cancelDelete(){
//
//        for (position in selectedNotes) {
//            list[position].selected = 0
//            notifyItemChanged(position)
//        }
//        selectedNotes.clear()
//        isEnable = false
//        showMenuDelete(false)
//
//    }
//
//    inner class notesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val notesLayout = view.findViewById<CardView>(R.id.notes_cardview)
//        val title = view.findViewById<TextView>(R.id.notes_title)
//        val content = view.findViewById<TextView>(R.id.notes_content)
//        val date = view.findViewById<TextView>(R.id.notes_date)
//    }
//
//    interface NotesItemClickListener {
//        fun onNoteClickListener(note: Note)
//    }
//}
package com.example.mynoteapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynoteapp.Models.Note
import com.example.mynoteapp.Models.NoteViewModel
import com.example.mynoteapp.R
import java.util.Locale

class NotesRvAdapter(
    val context: Context,
    val listener: NotesItemClickListener,
    val viewModel: NoteViewModel,
    private val showMenuDelete: (Boolean) -> Unit
) : RecyclerView.Adapter<NotesRvAdapter.notesViewHolder>() {

    private val list = ArrayList<Note>()
    private val fullList = ArrayList<Note>()
    private var isEnable = false
    private val selectedNotes = mutableListOf<Int>()
    private var searchQuery: String = ""

    override fun getItemId(position: Int): Long {
        return list[position].id?.toLong() ?: -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.notes_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val currentNote = list[position]
        holder.title.text = getHighlightedText(currentNote.title, searchQuery)
        holder.content.text = getHighlightedText(currentNote.content, searchQuery)
        holder.date.text = currentNote.date
        holder.title.isSelected = true
        holder.date.isSelected = true

        updateItemUI(holder, currentNote.selected == 1)

        holder.notesLayout.setOnClickListener {
            if (isEnable) {
                if (selectedNotes.contains(position)) {
                    deselectItem(holder, currentNote, position)
                } else {
                    selectItem(holder, currentNote, position)
                }
            } else {
                listener.onNoteClickListener(list[holder.adapterPosition])
            }
        }

        holder.notesLayout.setOnLongClickListener {
            selectItem(holder, currentNote, position)
            true
        }
    }

    private fun getHighlightedText(text: String?, search: String): SpannableString {
        val spannableString = SpannableString(text)
        if (!text.isNullOrEmpty() && search.isNotEmpty()) {
            val lowerCaseText = text.toLowerCase(Locale.ROOT)
            val lowerCaseSearch = search.toLowerCase(Locale.ROOT)
            var startIndex = lowerCaseText.indexOf(lowerCaseSearch)
            while (startIndex != -1) {
                val endIndex = startIndex + search.length
                spannableString.setSpan(BackgroundColorSpan(Color.YELLOW), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                startIndex = lowerCaseText.indexOf(lowerCaseSearch, endIndex)
            }
        }
        return spannableString
    }

    private fun selectItem(holder: notesViewHolder, currentNote: Note, position: Int) {
        isEnable = true
        selectedNotes.add(position)
        currentNote.selected = 1
        showMenuDelete(true)
        updateItemUI(holder, true)
        Toast.makeText(context, "Selected Items: ${selectedNotes.size}", Toast.LENGTH_SHORT).show()
    }

    private fun deselectItem(holder: notesViewHolder, currentNote: Note, position: Int) {
        selectedNotes.remove(position)
        currentNote.selected = 0
        updateItemUI(holder, false)
        if (selectedNotes.isEmpty()) {
            showMenuDelete(false)
            isEnable = false
        }
        Toast.makeText(context, "Selected Items: ${selectedNotes.size}", Toast.LENGTH_SHORT).show()
    }

    private fun updateItemUI(holder: notesViewHolder, isSelected: Boolean) {
        val context = holder.itemView.context
        val defaultColor = context.resources.getColor(android.R.color.white)
        val selectedColor = context.resources.getColor(android.R.color.holo_blue_light)

        if (isSelected) {
            holder.notesLayout.setCardBackgroundColor(selectedColor)
        } else {
            holder.notesLayout.setCardBackgroundColor(defaultColor)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        fullList.clear()
        fullList.addAll(newList)
        list.clear()
        list.addAll(fullList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(search: String) {
        searchQuery = search
        list.clear()
        for (item in fullList) {
            if (item.title?.contains(search, ignoreCase = true) == true ||
                item.content?.contains(search, ignoreCase = true) == true) {
                list.add(item)
            }
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteMultiple() {
        deleteSelectedItems()
        notifyDataSetChanged()
    }

    fun getSelectedNotes(): List<Note> {
        return selectedNotes.map { list[it] }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteSelectedItems() {
        val notesToDelete = getSelectedNotes()
        viewModel.deleteMultiple(notesToDelete)
        for (note in list) {
            note.selected = 0 // Reset the selected property
        }
        list.removeAll { it.selected == 1 }
        fullList.removeAll { it.selected == 1 }
        isEnable = false
        selectedNotes.clear()
    }

    fun cancelDelete() {
        for (position in selectedNotes) {
            list[position].selected = 0
            notifyItemChanged(position)
        }
        selectedNotes.clear()
        isEnable = false
        showMenuDelete(false)
    }

    inner class notesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val notesLayout = view.findViewById<CardView>(R.id.notes_cardview)
        val title = view.findViewById<TextView>(R.id.notes_title)
        val content = view.findViewById<TextView>(R.id.notes_content)
        val date = view.findViewById<TextView>(R.id.notes_date)
    }

    interface NotesItemClickListener {
        fun onNoteClickListener(note: Note)
    }
}
