package com.clase.proyecto_electiva_2
/**
 * Modelo que representa UNA tarea en la app.
 * Es la "forma" que tienen los datos antes de mostrarse en pantalla.
 */
data class Tarea(
    val id: Int,
    val titulo: String,
    val horario: String,
    val seccion: String,          // "Hoy", "Mañana", "Durante la Semana"
    var completada: Boolean = false
)