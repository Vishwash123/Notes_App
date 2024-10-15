package com.example.mynoteapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
//import android.widget.Toolbar
import androidx.appcompat.widget.Toolbar

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynoteapp.Adapter.NotesRvAdapter
import com.example.mynoteapp.Database.NoteDatabase
import com.example.mynoteapp.Models.Note
import com.example.mynoteapp.Models.NoteViewModel
import com.example.mynoteapp.databinding.FragmentNotesBinding


class NotesFragment : Fragment(R.layout.fragment_notes),NotesRvAdapter.NotesItemClickListener {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NotesRvAdapter
    private lateinit var toolbar: Toolbar
    lateinit var noteDatabase: NoteDatabase
    lateinit var notesViewModel: NoteViewModel

    private val updateNote = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if(result.resultCode == Activity.RESULT_OK)
        {
            val note = result.data?.getSerializableExtra("note") as? Note
            if(note!=null)
            {
                notesViewModel.updateNote(note)
            }
        }

    }


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        setHasOptionsMenu(true)
    }

//    @Deprecated("Deprecated in Java")
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//
//        deleteMenu = menu
//        inflater.inflate(R.menu.delete_menui,deleteMenu)
//
//        showDeleteMenu(false)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    @Deprecated("Deprecated in Java")
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.delete_icon->{
//                delete()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = Toolbar(requireContext())
        toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.title = "Notes"

        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        binding.root.addView(toolbar, 0, layoutParams)

        toolbar.inflateMenu(R.menu.delete_menui)
        // Initially hide the toolbar
        showDeleteMenu(false)
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete_icon -> {
                    delete()
                    true
                }
                R.id.cancel_delete->{
                    adapter.cancelDelete()
                    true
                }
                else -> false
            }
        }





        notesViewModel  = ViewModelProvider(this).get(NoteViewModel::class.java)
        initUI()
        notesViewModel.allNotes.observe(viewLifecycleOwner){list->
            list.apply {
                adapter.updateList(list)
            }

        }

        noteDatabase = NoteDatabase.getNoteDatabase(requireContext())








    }



    private fun initUI(){
        binding.NotesRV.setHasFixedSize(true)
        binding.NotesRV.layoutManager = StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)
        adapter = NotesRvAdapter(requireContext(),this@NotesFragment, notesViewModel){show->showDeleteMenu(show)}
        binding.NotesRV.adapter = adapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode == Activity.RESULT_OK)
            {
                val note = result.data?.getSerializableExtra("note") as? Note
                if(note!=null)
                {
                    notesViewModel.insertNote(note)
                }
            }

        }

        binding.addNoteButton.setOnClickListener{
            val intent = Intent(requireContext(),AddEditNote::class.java)
            getContent.launch(intent)
        }


        binding.notesSearch.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               if(newText!=null)
               {
                   adapter.filterList(newText)
               }
                return true
            }
        })


    }






    private fun delete(){
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Delete")
        alertDialog.setMessage("Do you want to delete the items?")
        alertDialog.setPositiveButton("Delete") { _, _ ->

            adapter.deleteMultiple()
            showDeleteMenu(false)

        }
        alertDialog.setNeutralButton("Cancel", null)
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
        }

    override fun onNoteClickListener(note: Note) {
        val intent = Intent(requireContext(),AddEditNote::class.java)
        intent.putExtra("current_note",note)
        updateNote.launch(intent)

    }



    private fun showDeleteMenu(show:Boolean)
    {

    if (show) {
        binding.addNoteButton.visibility = View.GONE

        toolbar.visibility = View.VISIBLE
    } else {
        binding.addNoteButton.visibility = View.VISIBLE

        toolbar.visibility = View.GONE
    }


    }
}