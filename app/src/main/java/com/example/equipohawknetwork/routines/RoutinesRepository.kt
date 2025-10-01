package com.example.equipohawknetwork.routines

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import java.util.Calendar

object RoutinesRepository {

    private val db = FirebaseFirestore.getInstance()

    fun dayKey(cal: Calendar): String {
        val y = cal.get(Calendar.YEAR)
        val m = cal.get(Calendar.MONTH) + 1
        val d = cal.get(Calendar.DAY_OF_MONTH)
        return String.format("%04d-%02d-%02d", y, m, d)
    }

    fun listenDay(
        uid: String,
        dayKey: String,
        onChange: (List<Exercise>) -> Unit
    ): ListenerRegistration {
        return db.collection("users").document(uid)
            .collection("routine").document(dayKey)
            .collection("exercises")
            .addSnapshotListener { snap, _ ->
                val list = snap?.documents?.map { doc ->
                    val e = doc.toObject(Exercise::class.java) ?: Exercise()
                    e.copy(id = doc.id)
                } ?: emptyList()
                onChange(list)
            }
    }

    fun addExercise(uid: String, dayKey: String, ex: Exercise): Task<DocumentReference> {
        val data = hashMapOf(
            "name" to ex.name,
            "muscleGroup" to ex.muscleGroup,
            "sets" to ex.sets,
            "reps" to ex.reps,
            "weightKg" to ex.weightKg,
            "restSec" to ex.restSec,
            "done" to false
        )
        return db.collection("users").document(uid)
            .collection("routine").document(dayKey)
            .collection("exercises")
            .add(data)
    }

    fun updateExercise(uid: String, dayKey: String, ex: Exercise): Task<Void> {
        val exId = requireNotNull(ex.id)
        val data = mapOf(
            "name" to ex.name,
            "muscleGroup" to ex.muscleGroup,
            "sets" to ex.sets,
            "reps" to ex.reps,
            "weightKg" to ex.weightKg,
            "restSec" to ex.restSec
        )
        return db.collection("users").document(uid)
            .collection("routine").document(dayKey)
            .collection("exercises").document(exId)
            .update(data)
    }

    fun markDone(uid: String, dayKey: String, exId: String, value: Boolean): Task<Void> {
        return db.collection("users").document(uid)
            .collection("routine").document(dayKey)
            .collection("exercises").document(exId)
            .update("done", value)
    }

    fun deleteExercise(uid: String, dayKey: String, exId: String): Task<Void> {
        return db.collection("users").document(uid)
            .collection("routine").document(dayKey)
            .collection("exercises").document(exId)
            .delete()
    }

    fun uidOrThrow(): String =
        FirebaseAuth.getInstance().currentUser?.uid
            ?: error("Usuario no logeado")
}
