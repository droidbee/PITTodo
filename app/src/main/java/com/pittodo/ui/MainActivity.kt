package com.pittodo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.pittodo.R
import com.pittodo.repository.ToDoRepository
import com.pittodo.ui.todolist.ToDoViewModel
import com.pittodo.ui.todolist.ToDoViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
    }
}