package com.pittodo.ui.todolist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.pittodo.R
import com.pittodo.db.ToDoDatabase
import com.pittodo.model.ToDoItem
import com.pittodo.repository.ToDoRepository
import com.pittodo.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG=ToDoViewModel::class.java.simpleName

    //Initializing repository and NetworkHelper class
    private val repository=ToDoRepository(ToDoDatabase(application).toDoDao)
    private val networkHelper=NetworkHelper(application)

    //Populating todoList with the todos from Repository
    val todoList = repository.todos

    //LiveData variable which will be set according to internet availability
    private val _networkStatus=MutableLiveData<String>()
    val networkStatus:LiveData<String>
        get()=_networkStatus


    //LiveData variable which will be set to true on successful
    //insertion of new task
    private val _insertionSuccess=MutableLiveData<Boolean>()
    val insertionSuccess:LiveData<Boolean>
        get()=_insertionSuccess


    init {
        getToDos()
    }


    /**
     * Method fetching data from network if internet is available,
     * else displaying the cached contents which were previously
     * stored in the local database
     */
    private fun getToDos(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                try {
                    repository.getToDosFromNetworkAndCacheIt()
                } catch (exception: Exception) {
                    Log.d(TAG, exception.toString())
                }
            }else{
                _networkStatus.value= R.string.cached_content.toString()
            }
        }

    }


    /**
     * Method to insert new task into the local database
     */
    fun onAddButtonClick(todo: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                insert(todo)
                _insertionSuccess.postValue(true)
            } catch (exception: Exception) {
                Log.d(TAG, exception.message.toString())
            }
        }
    }

    private fun insert(todo: ToDoItem) {
        repository.insert(todo)
    }

    /**
     * Method to be called soon after navingating to[ToDoFragment] page
     */
    fun doneNavigating(){
        _insertionSuccess.value=null
    }

}

/**
 * Factory method to initialize [ToDoViewModel] class
 */
class ToDoViewModelProviderFactory(
    val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  ToDoViewModel(application) as T
    }
}