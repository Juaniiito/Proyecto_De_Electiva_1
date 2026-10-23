package com.clase.proyecto_electiva_2


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clase.proyecto_electiva_2.R
class AgregarTareaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tarea)

        val editTitulo = findViewById<EditText>(R.id.editTitulo)
        val editHorario = findViewById<EditText>(R.id.editHorario)
        val spinnerSeccion = findViewById<Spinner>(R.id.spinnerSeccion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        val opcionesSeccion = arrayOf("Hoy", "Mañana", "Durante la Semana")
        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesSeccion)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSeccion.adapter = adapterSpinner

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