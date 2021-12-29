package com.pittodo.network

import com.pittodo.model.ToDoItem
import retrofit2.http.GET

interface ToDoApi {

    @GET("todos")
    suspend fun getToDos():List<ToDoItem>
}