package com.example.equipohawknetwork.routines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.equipohawknetwork.databinding.ItemExerciseBinding

class ExerciseAdapter(
    private val onComplete: (Exercise) -> Unit,
    private val onEdit: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.VH>() {

    private val items = mutableListOf<Exercise>()

    fun submit(list: List<Exercise>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class VH(val b: ItemExerciseBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemExerciseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(b)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val ex = items[position]

        holder.b.tvTitle.text = ex.name

        val subtitle = buildString {
            if (ex.muscleGroup.isNotBlank()) append(ex.muscleGroup)
            if (ex.sets > 0 && ex.reps > 0) {
                if (isNotEmpty()) append(" • ")
                append("${ex.sets}x${ex.reps}")
            }
            if (ex.weightKg > 0.0) {
                if (isNotEmpty()) append(" • ")
                append("${ex.weightKg} kg")
            }
            if (ex.restSec > 0) {
                if (isNotEmpty()) append(" • ")
                append("${ex.restSec}s")
            }
        }
        holder.b.tvSubtitle.text = subtitle

        holder.b.btnComplete.isEnabled = !ex.done
        holder.b.btnComplete.setOnClickListener { onComplete(ex) }
        holder.b.btnEdit.setOnClickListener { onEdit(ex) }
    }
}
