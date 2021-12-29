package com.pittodo.ui.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pittodo.R
import com.pittodo.databinding.FragmentToDoBinding


class ToDoFragment : Fragment() {

    private lateinit var adapter: ToDoAdapter


    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onActivityCreated, which we
     * do in this Fragment.
     */
    private val viewModel: ToDoViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, ToDoViewModelProviderFactory(activity.application))
            .get(ToDoViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_to_do, container, false)
        val binding = FragmentToDoBinding.inflate(inflater)

        binding.viewModel = viewModel


        //Navigate to [CreateToDoFragment] on clicking Floating Action Button to add new task
        binding.fab.setOnClickListener {
            this.findNavController()
                .navigate(ToDoFragmentDirections.actionToDoFragmentToCreateToDoFragment())
        }

        //Setting adapter to recyclerview
        adapter = ToDoAdapter(ToDoAdapter.OnClickListener {
            findNavController().navigate(
                ToDoFragmentDirections.actionToDoFragmentToToDoDetailFragment(
                    it
                )
            )
        })
        adapter.setHasStableIds(true)
        binding.recyclerView.adapter = adapter


        //Observing the todoList variable and updating the adapter

        viewModel.todoList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        //Observing the networkStatus variable and displaying a Snackbar if no
        //internet connection.

        viewModel.networkStatus.observe(viewLifecycleOwner, Observer {
            if (it == R.string.cached_content.toString()) {
                Snackbar.make(binding.root, R.string.no_internet, Snackbar.LENGTH_LONG).show()
            }
        })

        return binding.root
    }
}
