package com.example.equipohawknetwork.model

enum class Priority { LOW, MEDIUM, HIGH }

data class Event(
    val id: String,
    val title: String,
    val dateMillis: Long,
    val priority: Priority,
    val description: String
)

