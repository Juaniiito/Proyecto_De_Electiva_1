package com.clase.proyecto_electiva_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(
    private val listaTareas: MutableList<Tarea>,
    private val onTareaClick: (Tarea) -> Unit
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    /**
     * ViewHolder: guarda las referencias a las vistas de UN item.
     * Así no tenemos que llamar findViewById cada vez que se recicla.
     */
    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkTarea: CheckBox = itemView.findViewById(R.id.checkTarea)
        val txtTituloTarea: TextView = itemView.findViewById(R.id.txtTituloTarea)
        val txtHorarioTarea: TextView = itemView.findViewById(R.id.txtHorarioTarea)
        val imgEditar: View = itemView.findViewById(R.id.imgEditar)
    }

    /**
     * Se llama cuando el RecyclerView necesita crear un nuevo item visual.
     * Infla el XML item_tarea.xml.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    /**
     * Se llama para "pintar" los datos de una tarea en el ViewHolder.
     * Aquí conectamos datos ↔ vistas.
     */
    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = listaTareas[position]

        holder.txtTituloTarea.text = tarea.titulo
        holder.txtHorarioTarea.text = tarea.horario

        // Importante: quitar listener antes de setear el estado
        // para que no se dispare al reciclar
        holder.checkTarea.setOnCheckedChangeListener(null)
        holder.checkTarea.isChecked = tarea.completada

        // Listener del checkbox
        holder.checkTarea.setOnCheckedChangeListener { _, isChecked ->
            tarea.completada = isChecked
            onTareaClick(tarea)
        }

        // Listener del ícono editar
        holder.imgEditar.setOnClickListener {
            onTareaClick(tarea)
        }
    }

    override fun getItemCount(): Int = listaTareas.size
}