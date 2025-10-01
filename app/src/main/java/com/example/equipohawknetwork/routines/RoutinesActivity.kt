package com.example.equipohawknetwork.routines

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.equipohawknetwork.databinding.ActivityRoutinesBinding
import java.util.Calendar

class RoutinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutinesBinding
    private val vm by viewModels<RoutinesViewModel>()

    private val cal = Calendar.getInstance()

    private val adapter by lazy {
        ExerciseAdapter(
            onComplete = { vm.markDone(it) },
            onEdit = { openEditDialog(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvExercises.layoutManager = LinearLayoutManager(this)
        binding.rvExercises.adapter = adapter

        vm.exercises.observe(this) { list ->
            val pending = list.filter { !it.done }
            adapter.submit(pending)
            val empty = pending.isEmpty()
            binding.rvExercises.visibility = if (empty) View.GONE else View.VISIBLE
            binding.tvEmpty.visibility = if (empty) View.VISIBLE else View.GONE
        }

        // Cargar hoy
        vm.loadToday()

        binding.btnAdd.setOnClickListener { openAddDialog() }
        binding.btnPickDate.setOnClickListener { pickDate() }
    }

    private fun pickDate() {
        val y = cal.get(Calendar.YEAR)
        val m = cal.get(Calendar.MONTH)
        val d = cal.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, { _, yy, mm, dd ->
            cal.set(yy, mm, dd)
            val key = RoutinesRepository.dayKey(cal)
            vm.setDay(key)
        }, y, m, d).show()
    }

    private fun openAddDialog() {
        AddExerciseDialog(
            context = this,
            initial = null
        ) { name, group, sets, reps, weight, rest ->
            vm.addExercise(
                Exercise(
                    name = name,
                    muscleGroup = group,
                    sets = sets,
                    reps = reps,
                    weightKg = weight,
                    restSec = rest
                )
            )
        }.show()
    }

    private fun openEditDialog(ex: Exercise) {
        AddExerciseDialog(
            context = this,
            initial = ex
        ) { name, group, sets, reps, weight, rest ->
            vm.updateExercise(
                ex.copy(
                    name = name,
                    muscleGroup = group,
                    sets = sets,
                    reps = reps,
                    weightKg = weight,
                    restSec = rest
                )
            )
        }.show()
    }
}
