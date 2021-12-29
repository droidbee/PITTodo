package com.pittodo.ui.tododetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pittodo.R
import com.pittodo.databinding.FragmentToDoDetailBinding

class ToDoDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentToDoDetailBinding.inflate(inflater)
        val todoArgs = ToDoDetailFragmentArgs.fromBundle(requireArguments())
        binding.todoitem=todoArgs.todoitem

        return binding.root
    }

}