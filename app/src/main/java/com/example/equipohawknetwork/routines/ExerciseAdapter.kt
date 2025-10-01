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

    fun itemAt(position: Int): Exercise = items[position]

    fun removeAt(position: Int): Exercise {
        val removed = items.removeAt(position)
        notifyItemRemoved(position)
        return removed
    }

    inner class VH(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ex: Exercise) {
            binding.tvTitle.text = ex.name
            val subtitle = buildString {
                append(ex.muscleGroup)
                append(" • ${ex.sets}x${ex.reps}")
                if (ex.weightKg > 0) append(" • ${ex.weightKg}kg")
                if (ex.restSec > 0) append(" • ${ex.restSec}s")
            }
            binding.tvSubtitle.text = subtitle

            binding.btnComplete.setOnClickListener { onComplete(ex) }
            binding.btnEdit.setOnClickListener { onEdit(ex) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inf = LayoutInflater.from(parent.context)
        val binding = ItemExerciseBinding.inflate(inf, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount(): Int = items.size
}
