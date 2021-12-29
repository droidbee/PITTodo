package com.pittodo.repository

import androidx.lifecycle.LiveData
import com.pittodo.db.ToDoDao
import com.pittodo.model.ToDoItem
import com.pittodo.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDoRepository(private val dao: ToDoDao) {

    val todos:LiveData<List<ToDoItem>> =dao.getAllTasks()

    /**
     * Method to fetch data from the network and then
     * store it in a local database
     */
    suspend fun getToDosFromNetworkAndCacheIt() {
        withContext(Dispatchers.IO){
            val todos=RetrofitInstance.api.getToDos()
            dao.insertAll(todos)
        }
    }

    fun insert(todo:ToDoItem) = dao.insert(todo)
}