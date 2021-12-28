package com.ezatpanah.solidprinciplesandmvvm.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.ezatpanah.core.data.NoteModel
import com.ezatpanah.solidprinciplesandmvvm.R
import com.ezatpanah.solidprinciplesandmvvm.databinding.FragmentNoteBinding
import com.ezatpanah.solidprinciplesandmvvm.framework.NoteViewModel


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding

    private lateinit var viewModel: NoteViewModel
    private var currentNote = NoteModel("", "", 0L, 0L)
    private var noteId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }
        if (noteId != 0L) {
            viewModel.getNote(noteId)
        }

        binding.apply {
            checkButton.setOnClickListener {
                if (titleView.text.toString() != "" || contentView.text.toString() != "") {
                    val time: Long = System.currentTimeMillis()
                    currentNote.title = titleView.text.toString()
                    currentNote.content = contentView.text.toString()
                    currentNote.updateTime = time
                    if (currentNote.id == 0L) {
                        currentNote.creationTime = time
                    }
                    viewModel.saveNote(currentNote)
                } else {
                    Navigation.findNavController(it).popBackStack()
                }
            }

            observeViewModel()
        }


    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(binding.titleView).popBackStack()
            } else {
                Toast.makeText(context, "Somthing went wrong,Please try again!", Toast.LENGTH_SHORT)
                    .show()
            }

        })
        viewModel.currentNote.observe(viewLifecycleOwner, Observer { note ->

            note?.let {
                currentNote = it
                binding.apply {
                    titleView.setText(it.title, TextView.BufferType.EDITABLE)
                    contentView.setText(it.content, TextView.BufferType.EDITABLE)

                }
            }
        })
    }

    private fun hidekeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.titleView.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteNote -> {
                if (context != null && noteId != 0L) {
                    AlertDialog.Builder(context)
                        .setTitle("Delete Note")
                        .setMessage("Are you sure you Want delete this note?")
                        .setPositiveButton("Yes") { dialogInterface, i ->
                            viewModel.deleteNote(
                                currentNote
                            )
                        }
                        .setNegativeButton("Cancel") { dialogInterface, i ->  }
                        .create()
                        .show()
                }

            }
        }
        return true
    }

}