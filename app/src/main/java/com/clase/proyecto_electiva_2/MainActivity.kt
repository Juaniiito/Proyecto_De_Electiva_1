package com.clase.electiva_1_proyecto

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Lista maestra con TODAS las tareas (datos de prueba)
    private val todasLasTareas = mutableListOf(
        Tarea(1, "Desayuno con Sofía", "8:10 am", "Hoy", false),
        Tarea(2, "Presentación proyecto", "10:00 am", "Mañana", false),
        Tarea(3, "Parcial de cálculo", "6:30 pm", "Mañana", false),
        Tarea(4, "Cita de Toby con Veterinario", "05/03/2026", "Durante la Semana", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar los 3 RecyclerViews para cada sección
        configurarRecycler(R.id.recyclerHoy, "Hoy")
        configurarRecycler(R.id.recyclerManana, "Mañana")
        configurarRecycler(R.id.recyclerSemana, "Durante la Semana")

        // Botón agregar (placeholder por ahora)
        findViewById<Button>(R.id.btnAgregarTarea).setOnClickListener {
            Toast.makeText(this, "Próximamente: agregar tarea", Toast.LENGTH_SHORT).show()
        }

        // Botón siguiente (placeholder por ahora)
        findViewById<Button>(R.id.btnSiguiente).setOnClickListener {
            Toast.makeText(this, "Próximamente: pantalla siguiente", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Filtra las tareas por sección y configura su RecyclerView.
     */
    private fun configurarRecycler(recyclerId: Int, seccion: String) {
        val recycler = findViewById<RecyclerView>(recyclerId)
        recycler.layoutManager = LinearLayoutManager(this)

        // Filtramos solo las tareas de esta sección
        val tareasDeSeccion = todasLasTareas.filter { it.seccion == seccion }.toMutableList()

        // Creamos el adapter con un callback (qué hacer al hacer clic)
        val adapter = TareaAdapter(tareasDeSeccion) { tarea ->
            val estado = if (tarea.completada) "completada" else "pendiente"
            Toast.makeText(
                this,
                "${tarea.titulo} marcada como $estado",
                Toast.LENGTH_SHORT
            ).show()
        }

        recycler.adapter = adapter
    }
}