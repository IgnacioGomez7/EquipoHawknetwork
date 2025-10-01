package com.example.equipohawknetwork.routines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.equipohawknetwork.databinding.ItemExerciseBinding

class ExerciseAdapter(
    private val onComplete: (Exercise) -> Unit,
    private val onEdit: (Exercise) -> Unit
) : RecyclerView.Adapter<ExerciseAdapter.VH>() {

    private var items: List<Exercise> = emptyList()

    fun submit(newItems: List<Exercise>) {
        items = newItems
        notifyDataSetChanged()
    }

    class VH(val binding: ItemExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inf = LayoutInflater.from(parent.context)
        return VH(ItemExerciseBinding.inflate(inf, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = items[position]
        holder.binding.tvTitle.text = e.name
        holder.binding.tvSubtitle.text =
            "${e.muscleGroup} • ${e.sets} x ${e.reps} • ${e.weightKg} kg • descanso ${e.restSec}s"

        holder.binding.btnComplete.setOnClickListener { onComplete(e) }
        holder.binding.btnEdit.setOnClickListener { onEdit(e) }
    }

    override fun getItemCount(): Int = items.size
}
