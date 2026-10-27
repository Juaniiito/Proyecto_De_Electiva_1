package com.clase.proyecto_electiva_2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_tareas")
data class Tarea(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val horario: String,
    val seccion: String,
    var completada: Boolean = false
)