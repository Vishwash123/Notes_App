////package com.example.mynoteapp
////
////import android.app.Activity
////import android.content.Intent
////import android.os.Bundle
////import android.text.Spannable
////import android.text.SpannableStringBuilder
////import android.text.style.BackgroundColorSpan
////import android.widget.SearchView
////import android.widget.Toast
////import androidx.activity.enableEdgeToEdge
////import androidx.appcompat.app.AppCompatActivity
////import androidx.core.content.ContextCompat
////import androidx.core.view.ViewCompat
////import androidx.core.view.WindowInsetsCompat
////import com.example.mynoteapp.Models.Note
////import com.example.mynoteapp.databinding.ActivityAddEditNoteBinding
////import com.example.mynoteapp.databinding.FragmentNotesBinding
////import java.text.SimpleDateFormat
////import java.time.format.DateTimeFormatter
////import java.util.Date
////import java.util.Locale
////
////class AddEditNote : AppCompatActivity() {
////    private lateinit var binding: ActivityAddEditNoteBinding
////    private lateinit var note: Note
////    private lateinit var old_note:Note
////    var isUpdated = false
////    private var searchQuery: String = ""
////    private var currentMatchIndex = 0
////    private val matchIndices = mutableListOf<Int>()
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
////        setContentView(binding.root)
////
////        try{
////            old_note = intent.getSerializableExtra("current_note") as Note
////            binding.notesEditTitle.setText(old_note.title)
////            binding.notesEditContent.setText(old_note.content)
////            isUpdated=true
////
////        }catch (e:Exception)
////        {
////            e.printStackTrace()
////        }
////
////
////
////
////        binding.notesEditCheck.setOnClickListener{
////            val title = binding.notesEditTitle.text.toString()
////            val content = binding.notesEditContent.text.toString()
////            if(title.isNotEmpty()||content.isNotEmpty())
////            {
////                val formatter = SimpleDateFormat("EEE. d MMM yyyy MM:mm a")
////
////                if(isUpdated)
////                {
////                    note = Note(old_note.id,title,content,formatter.format(Date()),0)
////
////                }
////                else
////                {
////                    note = Note(null,title,content,formatter.format(Date()),0)
////                }
////
////
////                val intent = Intent()
////                intent.putExtra("note",note)
////                setResult(Activity.RESULT_OK,intent)
////                finish()
////            }
////            else
////            {
////
////                return@setOnClickListener
////            }
////
////        }
////
////        binding.notesEditCross.setOnClickListener{
////            onBackPressed()
////        }
////        setupSearchFunctionality()
////    }
////    private fun setupSearchFunctionality() {
////        binding.notesEditSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
////            override fun onQueryTextSubmit(query: String?): Boolean {
////                if (query != null) {
////                    searchQuery = query
////                    highlightSearchMatches()
////                    if (matchIndices.isNotEmpty()) {
////                        currentMatchIndex = 0
////                        highlightCurrentMatch()
////                    } else {
////                        Toast.makeText(this@AddEditNote, "No matches found", Toast.LENGTH_SHORT).show()
////                    }
////                }
////                return false
////            }
////
////            override fun onQueryTextChange(newText: String?): Boolean {
////                return false
////            }
////        })
////
////        binding.buttonPrev.setOnClickListener {
////            if (matchIndices.isNotEmpty()) {
////                currentMatchIndex = if (currentMatchIndex > 0) currentMatchIndex - 1 else matchIndices.size - 1
////                highlightCurrentMatch()
////            }
////        }
////
////        binding.buttonNext.setOnClickListener {
////            if (matchIndices.isNotEmpty()) {
////                currentMatchIndex = (currentMatchIndex + 1) % matchIndices.size
////                highlightCurrentMatch()
////            }
////        }
////    }
////
////    private fun highlightSearchMatches() {
////        val content = binding.notesEditContent.text.toString()
////        matchIndices.clear()
////        var startIndex = content.toLowerCase(Locale.getDefault()).indexOf(searchQuery.toLowerCase(Locale.getDefault()))
////        while (startIndex != -1) {
////            matchIndices.add(startIndex)
////            startIndex = content.toLowerCase(Locale.getDefault()).indexOf(searchQuery.toLowerCase(Locale.getDefault()), startIndex + searchQuery.length)
////        }
////        val spannable = SpannableStringBuilder(content)
////        matchIndices.forEach {
////            spannable.setSpan(
////                BackgroundColorSpan(ContextCompat.getColor(this, android.R.color.holo_blue_light)),
////                it,
////                it + searchQuery.length,
////                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
////            )
////        }
////        binding.notesEditContent.setText(spannable)
////    }
////
////    private fun highlightCurrentMatch() {
////        val content = binding.notesEditContent.text.toString()
////        val spannable = SpannableStringBuilder(content)
////        matchIndices.forEach {
////            spannable.setSpan(
////                BackgroundColorSpan(ContextCompat.getColor(this, android.R.color.holo_blue_light)),
////                it,
////                it + searchQuery.length,
////                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
////            )
////        }
////        if (matchIndices.isNotEmpty()) {
////            val currentPos = matchIndices[currentMatchIndex]
////            spannable.setSpan(
////                BackgroundColorSpan(ContextCompat.getColor(this, android.R.color.holo_green_light)),
////                currentPos,
////                currentPos + searchQuery.length,
////                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
////            )
////            binding.notesEditContent.setText(spannable)
////            binding.notesEditContent.setSelection(currentPos, currentPos + searchQuery.length)
////        }
////    }
////
////
////
////}
//
//package com.example.mynoteapp
//
//import android.app.Activity
//import android.content.Intent
//import android.graphics.Color
//import android.os.Bundle
//import android.text.Spannable
//import android.text.SpannableStringBuilder
//import android.text.style.BackgroundColorSpan
//import android.view.View
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.SearchView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.mynoteapp.Models.Note
//import com.example.mynoteapp.databinding.ActivityAddEditNoteBinding
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//class AddEditNote : AppCompatActivity() {
//    private lateinit var binding: ActivityAddEditNoteBinding
//    private lateinit var note: Note
//    private lateinit var oldNote: Note
//    private var isUpdated = false
//    private var currentIndex = 0
//    private var highlightedSpans = mutableListOf<IntRange>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        try {
//            oldNote = intent.getSerializableExtra("current_note") as Note
//            binding.notesEditTitle.setText(oldNote.title)
//            binding.notesEditContent.setText(oldNote.content)
//            isUpdated = true
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        binding.notesEditCheck.setOnClickListener {
//            saveNote()
//        }
//
//        binding.notesEditCross.setOnClickListener {
//            onBackPressed()
//        }
//
//
//
//        binding.notesEditSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let { searchInContent(it) }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let { searchInContent(it) }
//                return true
//            }
//
//
//
//        })
//
//        binding.buttonNext.setOnClickListener {
//            navigateToNextOccurrence()
//        }
//
//        binding.buttonPrev.setOnClickListener {
//            navigateToPreviousOccurrence()
//        }
//    }
//
//    private fun saveNote() {
//        val title = binding.notesEditTitle.text.toString()
//        val content = binding.notesEditContent.text.toString()
//        if (title.isNotEmpty() || content.isNotEmpty()) {
//            val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a", Locale.getDefault())
//
//            note = if (isUpdated) {
//                Note(oldNote.id, title, content, formatter.format(Date()), 0)
//            } else {
//                Note(null, title, content, formatter.format(Date()), 0)
//            }
//
//            val intent = Intent()
//            intent.putExtra("note", note)
//            setResult(Activity.RESULT_OK, intent)
//            finish()
//        }
//    }
//
//    private fun searchInContent(query: String) {
//        val content = binding.notesEditContent.text.toString()
//        val spannable = SpannableStringBuilder(content)
//        highlightedSpans.clear()
//        currentIndex = 0
//
//        if (query.isNotEmpty()) {
//            var start = content.indexOf(query, 0, true)
//            while (start != -1) {
//                val end = start + query.length
//                spannable.setSpan(
//                    BackgroundColorSpan(Color.YELLOW),
//                    start,
//                    end,
//                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//                )
//                highlightedSpans.add(IntRange(start, end))
//                start = content.indexOf(query, end, true)
//            }
//        }
//
//        binding.notesEditContent.setText(spannable)
//        updateNavigationButtons()
//    }
//
//    private fun navigateToNextOccurrence() {
//        if (highlightedSpans.isNotEmpty()) {
//            currentIndex = (currentIndex + 1) % highlightedSpans.size
//            scrollToCurrentOccurrence()
//        }
//    }
//
//    private fun navigateToPreviousOccurrence() {
//        if (highlightedSpans.isNotEmpty()) {
//            currentIndex = if (currentIndex - 1 < 0) highlightedSpans.size - 1 else currentIndex - 1
//            scrollToCurrentOccurrence()
//        }
//    }
//
//    private fun scrollToCurrentOccurrence() {
//        val currentSpan = highlightedSpans[currentIndex]
//        binding.notesEditContent.setSelection(currentSpan.start, currentSpan.endInclusive)
//        Toast.makeText(this, "Occurrence ${currentIndex + 1} of ${highlightedSpans.size}", Toast.LENGTH_SHORT).show()
//    }
//
//    private fun updateNavigationButtons() {
//        if (highlightedSpans.size > 1) {
//            binding.buttonNext.visibility = ImageView.VISIBLE
//            binding.buttonPrev.visibility = ImageView.VISIBLE
//        } else {
//            binding.buttonNext.visibility = ImageView.GONE
//            binding.buttonPrev.visibility = ImageView.GONE
//        }
//    }
//}
package com.example.mynoteapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mynoteapp.Models.Note
import com.example.mynoteapp.databinding.ActivityAddEditNoteBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddEditNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditNoteBinding
    private lateinit var note: Note
    private lateinit var oldNote: Note
    private var isUpdated = false
    private var currentIndex = 0
    private var highlightedSpans = mutableListOf<IntRange>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldNote = intent.getSerializableExtra("current_note") as Note
            binding.notesEditTitle.setText(oldNote.title)
            binding.notesEditContent.setText(oldNote.content)
            isUpdated = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.notesEditCheck.setOnClickListener {
            saveNote()
        }

        binding.notesEditCross.setOnClickListener {
            onBackPressed()
        }

        binding.notesEditSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchInContent(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchInContent(it) }
                return true
            }
        })





        binding.buttonNext.setOnClickListener {
            navigateToNextOccurrence()
        }

        binding.buttonPrev.setOnClickListener {
            navigateToPreviousOccurrence()
        }


    }

    override fun onBackPressed() {
        if(!binding.notesEditSearch.isIconified)
        {
            binding.notesEditSearch.isIconified=true
            binding.occurrenceCounter.text=""
        }
        else
        {
            super.onBackPressed()
        }
    }



    private fun saveNote() {
        val title = binding.notesEditTitle.text.toString()
        val content = binding.notesEditContent.text.toString()
        if (title.isNotEmpty() || content.isNotEmpty()) {
            val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a", Locale.getDefault())

            note = if (isUpdated) {
                Note(oldNote.id, title, content, formatter.format(Date()), 0)
            } else {
                Note(null, title, content, formatter.format(Date()), 0)
            }

            val intent = Intent()
            intent.putExtra("note", note)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun searchInContent(query: String) {
        val content = binding.notesEditContent.text.toString()
        val spannable = SpannableStringBuilder(content)
        highlightedSpans.clear()
        currentIndex = 0

        if (query.isNotEmpty()) {
            var start = content.indexOf(query, 0, true)
            while (start != -1) {
                val end = start + query.length
                spannable.setSpan(
                    BackgroundColorSpan(Color.YELLOW),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                highlightedSpans.add(IntRange(start, end))
                start = content.indexOf(query, end, true)
            }
        }

        binding.notesEditContent.setText(spannable)
        updateOccurenceCounter()
        updateNavigationButtons()
    }

    private fun navigateToNextOccurrence() {
        if (highlightedSpans.isNotEmpty()) {
            currentIndex = (currentIndex + 1) % highlightedSpans.size
            scrollToCurrentOccurrence()
        }
    }

    private fun navigateToPreviousOccurrence() {
        if (highlightedSpans.isNotEmpty()) {
            currentIndex = if (currentIndex - 1 < 0) highlightedSpans.size - 1 else currentIndex - 1
            scrollToCurrentOccurrence()
        }
    }


    private fun scrollToCurrentOccurrence() {
//        val currentSpan = highlightedSpans[currentIndex]
//        binding.notesEditContent.setSelection(currentSpan.start, currentSpan.endInclusive)
//        Toast.makeText(
//            this,
//            "Occurrence ${currentIndex + 1} of ${highlightedSpans.size}",
//            Toast.LENGTH_SHORT
//        ).show()

//        val currentSpan = highlightedSpans[currentIndex]
//        binding.notesEditContent.scrollTo(0, currentSpan.start)
//        binding.notesEditContent.setSelection(currentSpan.start, currentSpan.endInclusive)
//        Toast.makeText(
//            this,
//            "Occurrence ${currentIndex + 1} of ${highlightedSpans.size}",
//            Toast.LENGTH_SHORT
//        ).show()

//        val currentSpan = highlightedSpans[currentIndex]
//        val scrollView = findViewById<ScrollView>(R.id.scrollView)
//        scrollView.smoothScrollTo(0, currentSpan.start)
//        binding.notesEditContent.setSelection(currentSpan.start, currentSpan.endInclusive)
//        Toast.makeText(
//            this,
//            "Occurrence ${currentIndex + 1} of ${highlightedSpans.size}",
//            Toast.LENGTH_SHORT
//        ).show()

        val currentSpan = highlightedSpans[currentIndex]
        val editText = binding.notesEditContent
        val scrollView = findViewById<ScrollView>(R.id.scrollView)

        val layout = editText.layout
        val line = layout.getLineForOffset(currentSpan.start)
        val top = layout.getLineTop(line)

        scrollView.smoothScrollTo(0, top)
        editText.setSelection(currentSpan.start, currentSpan.endInclusive)
        updateOccurenceCounter()


    }


    @SuppressLint("SetTextI18n")
    private fun updateOccurenceCounter(){
        binding.occurrenceCounter.text = "${currentIndex+1}/${highlightedSpans.size}"

    }


    private fun updateNavigationButtons() {
        if (highlightedSpans.size > 1) {
            binding.bottomEditCard.visibility=View.VISIBLE
        } else {
            binding.bottomEditCard.visibility = View.GONE

        }
    }
}