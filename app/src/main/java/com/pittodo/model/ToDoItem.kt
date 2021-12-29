package com.pittodo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "todos"
)
data class ToDoItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean,
):Serializable