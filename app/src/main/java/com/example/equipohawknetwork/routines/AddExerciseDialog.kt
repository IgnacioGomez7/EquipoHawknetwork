package com.example.equipohawknetwork.routines

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.equipohawknetwork.databinding.DialogAddExerciseBinding

class AddExerciseDialog(
    context: Context,
    private val initial: Exercise? = null,
    private val onSave: (name: String, group: String, sets: Int, reps: Int, weightKg: Double, restSec: Int) -> Unit
) {
    private val binding = DialogAddExerciseBinding.inflate(LayoutInflater.from(context))
    private val dialog = AlertDialog.Builder(context)
        .setTitle(if (initial == null) "Agregar ejercicio" else "Editar ejercicio")
        .setView(binding.root)
        .setPositiveButton(if (initial == null) "Guardar" else "Guardar cambios", null)
        .setNegativeButton("Cancelar", null)
        .create()

    fun show() {
        val groups = listOf("Pecho", "Espalda", "Hombro", "Pierna", "Bíceps", "Tríceps", "Core")
        binding.spGroup.adapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_dropdown_item,
            groups
        )

        // Precargar valores si es edición
        initial?.let { e ->
            binding.etName.setText(e.name)
            val idx = groups.indexOfFirst { it.equals(e.muscleGroup, ignoreCase = true) }.coerceAtLeast(0)
            binding.spGroup.setSelection(idx)
            binding.etSets.setText(e.sets.toString())
            binding.etReps.setText(e.reps.toString())
            binding.etWeight.setText(e.weightKg.toString())
            binding.etRest.setText(e.restSec.toString())
        }

        dialog.setOnShowListener {
            val btn = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            btn.setOnClickListener {
                val name = binding.etName.text?.toString()?.trim().orEmpty()
                val group = binding.spGroup.selectedItem?.toString() ?: ""
                val sets = binding.etSets.text?.toString()?.toIntOrNull() ?: 0
                val reps = binding.etReps.text?.toString()?.toIntOrNull() ?: 0
                val weight = binding.etWeight.text?.toString()?.toDoubleOrNull() ?: 0.0
                val rest = binding.etRest.text?.toString()?.toIntOrNull() ?: 0

                if (name.isEmpty() || sets <= 0 || reps <= 0) {
                    Toast.makeText(binding.root.context, "Completa nombre, sets y reps", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                onSave(name, group, sets, reps, weight, rest)
                dialog.dismiss()
            }
        }

        dialog.show()
    }
}
