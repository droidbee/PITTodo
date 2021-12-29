package com.pittodo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pittodo.model.ToDoItem

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toDoItem: ToDoItem)

    @Delete
    fun delete(toDoItem: ToDoItem)

    @Query("SELECT * FROM todos")
    fun getAllTasks(): LiveData<List<ToDoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(todos:List<ToDoItem>)
}