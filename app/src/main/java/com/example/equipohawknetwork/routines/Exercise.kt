// app/src/main/java/com/example/equipohawknetwork/routines/Exercise.kt
package com.example.equipohawknetwork.routines

data class Exercise(
    val id: String? = null,
    val name: String = "",
    val muscleGroup: String = "",
    val sets: Int = 0,
    val reps: Int = 0,
    val weightKg: Double = 0.0,
    val restSec: Int = 0,
    val order: Int? = null,
    val done: Boolean = false
)
