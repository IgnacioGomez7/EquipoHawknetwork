package com.example.equipohawknetwork.routines

import com.google.firebase.Timestamp

data class Exercise(
    val id: String = "",
    val name: String = "",
    val muscleGroup: String = "",
    val sets: Int = 0,
    val reps: Int = 0,
    val weightKg: Double = 0.0,
    val restSec: Int = 0,
    val order: Int = 0,
    val createdAt: Timestamp? = null,
    val done: Boolean = false,
    val completedAt: Timestamp? = null
)
