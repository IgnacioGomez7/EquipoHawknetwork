package com.example.equipohawknetwork.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor() : ViewModel() {
    val events = mutableStateListOf<Event>()

    fun addEvent(title: String, dateMillis: Long, priority: Priority, description: String) {
        events.add(0, Event(UUID.randomUUID().toString(), title, dateMillis, priority, description))
    }
    fun removeEvent(id: String) {
        events.removeAll { it.id == id }
    }
}
