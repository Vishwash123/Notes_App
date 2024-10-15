package com.example.mynoteapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mynoteapp.Adapter.NotesRvAdapter
import com.example.mynoteapp.Adapter.TodosRvAdapter
import com.example.mynoteapp.Models.Note
import com.example.mynoteapp.Models.Todo
import com.example.mynoteapp.databinding.FragmentNotesBinding
import com.example.mynoteapp.databinding.FragmentTodosBinding
import java.util.ArrayList


class Todos : Fragment(R.layout.fragment_todos) {
    private var _binding: FragmentTodosBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TodosRvAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTodosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.TodosRV.setHasFixedSize(true)
        binding.TodosRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = TodosRvAdapter(requireContext() )
        binding.TodosRV.adapter = adapter

        binding.addTodoButton.setOnClickListener {
            showAddTodoDialog()
        }
    }

    private fun showAddTodoDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.todo_alert_dialog, null)
        val builder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)

        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val cancelBtn = dialogView.findViewById<TextView>(R.id.todo_cancel)
        val saveBtn = dialogView.findViewById<TextView>(R.id.todo_save)

        if (cancelBtn != null && saveBtn != null) {
            cancelBtn.setOnClickListener {
                alertDialog.dismiss()
            }

            saveBtn.setOnClickListener {
                
                alertDialog.dismiss()
            }
        }

        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}