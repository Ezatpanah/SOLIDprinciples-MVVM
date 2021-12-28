package com.ezatpanah.solidprinciplesandmvvm.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezatpanah.solidprinciplesandmvvm.databinding.FragmentListBinding
import com.ezatpanah.solidprinciplesandmvvm.framework.ListViewModel


class ListFragment : Fragment(), ListAction {

    private val noteListAdapter = NoteListAdapter(arrayListOf(),this)

    private lateinit var viewModel: ListViewModel

    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.apply {

            noteListview.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = noteListAdapter
            }

            addNote.setOnClickListener { goToNoteDetails() }

        }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()

    }

    private fun observeViewModel() {

        viewModel.notes.observe(viewLifecycleOwner, Observer { notesList ->
            binding.apply {
                loadingView.visibility = View.GONE
                noteListview.visibility = View.VISIBLE
                noteListAdapter.updateNotes(notesList.sortedByDescending { it.updateTime })
            }

        })
    }


    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action: NavDirections = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(binding.noteListview).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }

}