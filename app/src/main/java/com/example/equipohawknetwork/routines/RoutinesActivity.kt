package com.example.equipohawknetwork.routines

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.equipohawknetwork.databinding.ActivityRoutinesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RoutinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutinesBinding
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }

    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private var selectedCal = Calendar.getInstance()
    private var listenerReg: com.google.firebase.firestore.ListenerRegistration? = null

    private val adapter by lazy {
        ExerciseAdapter(
            onComplete = { ex -> markExerciseDone(ex) },
            onEdit = { ex -> openEditDialog(ex) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lista
        binding.rvExercises.layoutManager = LinearLayoutManager(this)
        binding.rvExercises.adapter = adapter

        // Fecha actual
        updateDateLabel()
        subscribeToDay()

        // Cambiar fecha
        binding.btnPickDate.setOnClickListener { openDatePicker() }

        // Agregar ejercicio
        binding.btnAdd.setOnClickListener { openAddDialog() }
    }

    override fun onDestroy() {
        super.onDestroy()
        listenerReg?.remove()
    }

    private fun openDatePicker() {
        val y = selectedCal.get(Calendar.YEAR)
        val m = selectedCal.get(Calendar.MONTH)
        val d = selectedCal.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, { _, yy, mm, dd ->
            selectedCal.set(yy, mm, dd)
            updateDateLabel()
            subscribeToDay()
        }, y, m, d).show()
    }

    private fun updateDateLabel() {
        binding.tvDate.text = "Rutinas de ${sdf.format(selectedCal.time)}"
    }

    private fun dayExercisesCollection(uid: String) =
        db.collection("users").document(uid)
            .collection("routines").document(sdf.format(selectedCal.time))
            .collection("exercises")

    private fun subscribeToDay() {
        listenerReg?.remove()

        val uid = auth.currentUser?.uid
        if (uid == null) {
            Toast.makeText(this, "Inicia sesión", Toast.LENGTH_SHORT).show()
            finish(); return
        }

        // Sin WHERE para evitar índices: filtramos done=false en cliente
        listenerReg = dayExercisesCollection(uid)
            .orderBy("order", Query.Direction.ASCENDING)
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    Toast.makeText(this, "Error: ${err.localizedMessage}", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }
                val all = snap?.documents?.map { d ->
                    Exercise(
                        id = d.id,
                        name = d.getString("name") ?: "",
                        muscleGroup = d.getString("muscleGroup") ?: "",
                        sets = (d.getLong("sets") ?: 0L).toInt(),
                        reps = (d.getLong("reps") ?: 0L).toInt(),
                        weightKg = (d.getDouble("weightKg") ?: 0.0),
                        restSec = (d.getLong("restSec") ?: 0L).toInt(),
                        order = (d.getLong("order") ?: 0L).toInt(),
                        done = (d.getBoolean("done") ?: false)
                    )
                } ?: emptyList()

                val pending = all.filter { !it.done }
                adapter.submit(pending)

                // Mostrar “Día de descanso” si no hay ejercicios (o todos completados)
                val showEmpty = pending.isEmpty()
                binding.rvExercises.visibility = if (showEmpty) View.GONE else View.VISIBLE
                binding.tvEmpty.visibility = if (showEmpty) View.VISIBLE else View.GONE
            }
    }

    private fun openAddDialog() {
        AddExerciseDialog(
            context = this,
            initial = null
        ) { name, group, sets, reps, weight, rest ->
            saveExercise(name, group, sets, reps, weight, rest)
        }.show()
    }

    private fun openEditDialog(ex: Exercise) {
        AddExerciseDialog(
            context = this,
            initial = ex
        ) { name, group, sets, reps, weight, rest ->
            updateExercise(ex, name, group, sets, reps, weight, rest)
        }.show()
    }

    private fun saveExercise(
        name: String, muscleGroup: String, sets: Int, reps: Int, weightKg: Double, restSec: Int
    ) {
        val uid = auth.currentUser?.uid ?: return
        val col = dayExercisesCollection(uid)
        col.get().addOnSuccessListener { snap ->
            val nextOrder = (snap?.size() ?: 0) + 1
            val data = mapOf(
                "name" to name,
                "muscleGroup" to muscleGroup,
                "sets" to sets,
                "reps" to reps,
                "weightKg" to weightKg,
                "restSec" to restSec,
                "order" to nextOrder,
                "done" to false,
                "createdAt" to FieldValue.serverTimestamp()
            )
            col.add(data)
                .addOnSuccessListener { Toast.makeText(this, "Ejercicio agregado", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { e -> Toast.makeText(this, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun updateExercise(
        ex: Exercise, name: String, muscleGroup: String, sets: Int, reps: Int, weightKg: Double, restSec: Int
    ) {
        val uid = auth.currentUser?.uid ?: return
        dayExercisesCollection(uid).document(ex.id)
            .update(
                mapOf(
                    "name" to name,
                    "muscleGroup" to muscleGroup,
                    "sets" to sets,
                    "reps" to reps,
                    "weightKg" to weightKg,
                    "restSec" to restSec
                )
            )
            .addOnSuccessListener { Toast.makeText(this, "Ejercicio actualizado", Toast.LENGTH_SHORT).show() }
            .addOnFailureListener { e -> Toast.makeText(this, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show() }
    }

    private fun markExerciseDone(ex: Exercise) {
        val uid = auth.currentUser?.uid ?: return
        dayExercisesCollection(uid).document(ex.id)
            .update(
                mapOf(
                    "done" to true,
                    "completedAt" to FieldValue.serverTimestamp()
                )
            )
            .addOnSuccessListener { Toast.makeText(this, "¡Completado!", Toast.LENGTH_SHORT).show() }
            .addOnFailureListener { e -> Toast.makeText(this, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show() }
    }
}
