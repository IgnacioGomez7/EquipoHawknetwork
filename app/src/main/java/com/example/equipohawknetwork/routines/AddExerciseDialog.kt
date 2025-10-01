package com.example.equipohawknetwork.routines

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import com.example.equipohawknetwork.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddExerciseDialog(
    context: Context,
    private val initial: Exercise? = null,
    private val onSubmit: (name: String, group: String, sets: Int, reps: Int, weight: Float, rest: Int) -> Unit
) : Dialog(context) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_add_exercise, null, false)

        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)

        val tilName = view.findViewById<TextInputLayout>(R.id.tilName)
        val etName = view.findViewById<TextInputEditText>(R.id.etName)

        val tilGroup = view.findViewById<TextInputLayout>(R.id.tilGroup)
        val actvGroup = view.findViewById<AutoCompleteTextView>(R.id.actvGroup)

        val tilSets = view.findViewById<TextInputLayout>(R.id.tilSets)
        val etSets = view.findViewById<TextInputEditText>(R.id.etSets)

        val tilReps = view.findViewById<TextInputLayout>(R.id.tilReps)
        val etReps = view.findViewById<TextInputEditText>(R.id.etReps)

        val tilWeight = view.findViewById<TextInputLayout>(R.id.tilWeight)
        val etWeight = view.findViewById<TextInputEditText>(R.id.etWeight)

        val tilRest = view.findViewById<TextInputLayout>(R.id.tilRest)
        val etRest = view.findViewById<TextInputEditText>(R.id.etRest)

        // Dropdown de grupos
        val groups = context.resources.getStringArray(R.array.muscle_groups)
        actvGroup.setAdapter(ArrayAdapter(context, android.R.layout.simple_list_item_1, groups))

        // Título por defecto (sin strings.xml)
        tvTitle.text = "Nuevo ejercicio"

        // Si es edición, prellenar
        initial?.let { ex ->
            tvTitle.text = "Editar ejercicio"
            etName.setText(ex.name)
            actvGroup.setText(ex.muscleGroup, false)
            etSets.setText(ex.sets.toString())
            etReps.setText(ex.reps.toString())
            ex.weightKg?.let { etWeight.setText(it.toString()) }
            ex.restSec?.let { etRest.setText(it.toString()) }
        }

        val dialog = MaterialAlertDialogBuilder(context)
            .setView(view)
            .create()

        // Botones
        view.findViewById<MaterialButton>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
        }

        view.findViewById<MaterialButton>(R.id.btnSave).setOnClickListener {
            // Validación simple
            val name = etName.text?.toString()?.trim().orEmpty()
            val group = actvGroup.text?.toString()?.trim().orEmpty()
            val sets = etSets.text?.toString()?.toIntOrNull()
            val reps = etReps.text?.toString()?.toIntOrNull()
            val weight = etWeight.text?.toString()?.toFloatOrNull()
            val rest = etRest.text?.toString()?.toIntOrNull()

            var ok = true
            if (name.isEmpty()) { tilName.error = "Requerido"; ok = false } else tilName.error = null
            if (group.isEmpty()) { tilGroup.error = "Selecciona un grupo"; ok = false } else tilGroup.error = null
            if (sets == null || sets <= 0) { tilSets.error = "Número válido"; ok = false } else tilSets.error = null
            if (reps == null || reps <= 0) { tilReps.error = "Número válido"; ok = false } else tilReps.error = null

            if (!ok) return@setOnClickListener

            // ¡OJO! lambda sin argumentos nombrados
            onSubmit(
                name,
                group,
                sets!!,
                reps!!,
                weight ?: 0f,
                rest ?: 60
            )
            dialog.dismiss()
        }

        setContentView(view)
        setCancelable(true)
    }
}
