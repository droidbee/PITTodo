package com.pittodo.ui.createtodo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pittodo.R
import com.pittodo.databinding.FragmentCreateToDoBinding
import com.pittodo.model.ToDoItem
import com.pittodo.ui.todolist.ToDoFragmentDirections
import com.pittodo.ui.todolist.ToDoViewModel
import com.pittodo.ui.todolist.ToDoViewModelProviderFactory

/**
 * Fragment class to insert new tasks
 */

class CreateToDoFragment : Fragment() {


    /**
     * Lazy initializing [ToDoViewModel] class.
     */
    private val todoViewModel: ToDoViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, ToDoViewModelProviderFactory(activity.application))
            .get(ToDoViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment

        val binding = FragmentCreateToDoBinding.inflate(inflater)


        binding.apply {
            viewmodel = todoViewModel
            addButton.setOnClickListener {
                if (TextUtils.isEmpty((todoEdittext.text))) {
                    Toast.makeText(
                        requireContext(),
                        R.string.task_cannot_be_empty,
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                val todo = ToDoItem(0, 1, todoEdittext.text.toString(), false)
                todoViewModel.onAddButtonClick(todo)
            }
        }

        //Observing the insertionSuccess variable . If the variable is true , then
        //navigate to [ToDoFragment] page.

        todoViewModel.insertionSuccess.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                findNavController().navigate(CreateToDoFragmentDirections.actionCreateToDoFragmentToToDoFragment())
                todoViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}