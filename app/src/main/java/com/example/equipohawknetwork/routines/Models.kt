package com.example.equipohawknetwork.routines

import com.google.firebase.firestore.DocumentSnapshot

fun DocumentSnapshot.toExercise(): Exercise = Exercise(
    id = id,
    name = getString("name") ?: "",
    muscleGroup = getString("muscleGroup") ?: "",
    sets = (getLong("sets") ?: 0L).toInt(),
    reps = (getLong("reps") ?: 0L).toInt(),
    weightKg = (getDouble("weightKg") ?: 0.0),
    restSec = (getLong("restSec") ?: 0L).toInt(),
    order = (getLong("order") ?: 0L).toInt(),
    done = getBoolean("done") ?: false
)
