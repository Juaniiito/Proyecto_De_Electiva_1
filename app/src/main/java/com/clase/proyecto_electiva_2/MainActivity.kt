package com.clase.proyecto_electiva_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clase.proyecto_electiva_2.R

class MainActivity : AppCompatActivity() {

    // 1. Lista maestra con TODAS las tareas
    private val todasLasTareas = mutableListOf(
        Tarea(1, "Desayuno con Sofía", "8:10 am", "Hoy", false),
        Tarea(2, "Presentación proyecto", "10:00 am", "Mañana", false),
        Tarea(3, "Parcial de cálculo", "6:30 pm", "Mañana", false),
        Tarea(4, "Cita de Toby con Veterinario", "05/03/2026", "Durante la Semana", false)
    )

    // 2. El lanzador que espera la respuesta de la pantalla de Agregar Tarea
    private val lanzadorAgregarTarea = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val titulo = data?.getStringExtra("TITULO") ?: ""
            val horario = data?.getStringExtra("HORARIO") ?: ""
            val seccion = data?.getStringExtra("SECCION") ?: "Hoy"

            // Creamos la nueva tarea
            val nuevaTarea = Tarea(
                id = (todasLasTareas.maxOfOrNull { it.id } ?: 0) + 1,
                titulo = titulo,
                horario = horario,
                seccion = seccion,
                completada = false
            )
            todasLasTareas.add(nuevaTarea)

            // Refrescamos las listas
            actualizarListas()
        }
    }

    // 3. El onCreate (donde inicia la app)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Pintamos las listas por primera vez
        actualizarListas()

        // Botón agregar
        findViewById<Button>(R.id.btnAgregarTarea).setOnClickListener {
            val intent = Intent(this, AgregarTareaActivity::class.java)
            lanzadorAgregarTarea.launch(intent)
        }

        // Botón siguiente
        findViewById<Button>(R.id.btnSiguiente).setOnClickListener {
            Toast.makeText(this, "Próximamente: pantalla siguiente", Toast.LENGTH_SHORT).show()
        }
    }

    // 4. Función para configurar cada RecyclerView
    private fun configurarRecycler(recyclerId: Int, seccion: String) {
        val recycler = findViewById<RecyclerView>(recyclerId)
        recycler.layoutManager = LinearLayoutManager(this)

        // Filtramos las tareas de esta sección
        val tareasDeSeccion = todasLasTareas.filter { it.seccion == seccion }.toMutableList()

        // Creamos el adapter
        val adapter = TareaAdapter(tareasDeSeccion) { tarea ->
            val estado = if (tarea.completada) "completada" else "pendiente"

            // Toast simplificado
            Toast.makeText(this, "${tarea.titulo} marcada como $estado", Toast.LENGTH_SHORT).show()
        }

        recycler.adapter = adapter
    }

    // 5. Función para actualizar todas las listas (se llama al inicio y al agregar tarea)
    private fun actualizarListas() {
        configurarRecycler(R.id.recyclerHoy, "Hoy")
        configurarRecycler(R.id.recyclerManana, "Mañana")
        configurarRecycler(R.id.recyclerSemana, "Durante la Semana")
    }
}