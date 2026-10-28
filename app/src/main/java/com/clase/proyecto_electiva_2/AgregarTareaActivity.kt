package com.clase.proyecto_electiva_2

import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class AgregarTareaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tarea)

        val editTitulo = findViewById<EditText>(R.id.editTitulo)
        val editHorario = findViewById<EditText>(R.id.editHorario)
        val spinnerSeccion = findViewById<Spinner>(R.id.spinnerSeccion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        // Opciones para el Spinner
        val opcionesSeccion = arrayOf("Hoy", "Mañana", "Durante la Semana")
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesSeccion)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSeccion.adapter = adapterSpinner

        // 🔥 AQUÍ ESTÁ LA MAGIA DEL RELOJ 🔥
        editHorario.setOnClickListener {
            val calendario = Calendar.getInstance()
            val hora = calendario.get(Calendar.HOUR_OF_DAY)
            val minuto = calendario.get(Calendar.MINUTE)

            // Creamos el diálogo del reloj
            val timePicker = TimePickerDialog(
                this,
                { _, horaSeleccionada, minutoSeleccionado ->
                    // Cuando el usuario elige la hora, la formateamos
                    val formatoHora = Calendar.getInstance().apply {
                        set(Calendar.HOUR_OF_DAY, horaSeleccionada)
                        set(Calendar.MINUTE, minutoSeleccionado)
                    }

                    // Formato de 12 horas (ej. 08:10 AM)
                    val formato = java.text.SimpleDateFormat("hh:mm a", Locale.getDefault())
                    editHorario.setText(formato.format(formatoHora.time))
                },
                hora,
                minuto,
                false // false para formato de 12 horas (AM/PM), true para 24 horas
            )
            timePicker.show()
        }

        btnGuardar.setOnClickListener {
            val titulo = editTitulo.text.toString()
            val horario = editHorario.text.toString()
            val seccionSeleccionada = spinnerSeccion.selectedItem.toString()

            if (titulo.isBlank() || horario.isBlank()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val intentDevolver = Intent()
                intentDevolver.putExtra("TITULO", titulo)
                intentDevolver.putExtra("HORARIO", horario)
                intentDevolver.putExtra("SECCION", seccionSeleccionada)

                setResult(Activity.RESULT_OK, intentDevolver)
                finish()
            }
        }
    }
}