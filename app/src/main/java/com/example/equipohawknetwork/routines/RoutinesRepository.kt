// app/src/main/java/com/example/equipohawknetwork/routines/RoutinesRepository.kt
package com.example.equipohawknetwork.routines

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RoutinesRepository {
    private val db = FirebaseFirestore.getInstance()

    fun listenDay(
        uid: String,
        dayKey: String,
        onChange: (List<Exercise>) -> Unit,
        onError: (Exception) -> Unit = {}
    ): ListenerRegistration {
        return db.collection("users").document(uid)
            .collection("routines").document(dayKey)
            .collection("exercises")
            .orderBy("order", Query.Direction.ASCENDING)
            .addSnapshotListener { snap, e ->
                if (e != null) { onError(e); return@addSnapshotListener }
                val list = snap?.documents?.map { d ->
                    Exercise(
                        id = d.id,
                        name = d.getString("name") ?: "",
                        muscleGroup = d.getString("muscleGroup") ?: "",
                        sets = (d.getLong("sets") ?: 0L).toInt(),
                        reps = (d.getLong("reps") ?: 0L).toInt(),
                        weightKg = d.getDouble("weightKg")
                            ?: (d.getLong("weightKg")?.toDouble() ?: 0.0),
                        restSec = (d.getLong("restSec") ?: 0L).toInt(),
                        order = (d.getLong("order") ?: 0L).toInt(),
                        done = d.getBoolean("done") ?: false
                    )
                } ?: emptyList()
                onChange(list)
            }
    }

    fun addExercise(uid: String, dayKey: String, data: Map<String, Any?>) =
        db.collection("users").document(uid)
            .collection("routines").document(dayKey)
            .collection("exercises")
            .add(data)

    fun updateExercise(uid: String, dayKey: String, id: String, data: Map<String, Any?>) =
        db.collection("users").document(uid)
            .collection("routines").document(dayKey)
            .collection("exercises").document(id)
            .update(data)

    fun markDone(uid: String, dayKey: String, id: String, done: Boolean = true) =
        db.collection("users").document(uid)
            .collection("routines").document(dayKey)
            .collection("exercises").document(id)
            .update(mapOf("done" to done))

    companion object {
        private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        fun dayKey(cal: Calendar): String = sdf.format(cal.time)
    }
}
