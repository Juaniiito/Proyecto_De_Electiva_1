package com.metodo.cosecha.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val description: String,
    val dueDate: String,
    val priority: String, // "Alta", "Media", "Baja"
    val isCompleted: Boolean = false
)
