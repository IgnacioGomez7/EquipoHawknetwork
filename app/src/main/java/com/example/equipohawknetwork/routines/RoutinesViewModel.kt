package com.example.equipohawknetwork.routines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ListenerRegistration
import java.util.Calendar

class RoutinesViewModel : ViewModel() {

    private val _exercises = MutableLiveData<List<Exercise>>(emptyList())
    val exercises: LiveData<List<Exercise>> = _exercises

    private var currentDayKey: String? = null
    private var reg: ListenerRegistration? = null

    fun loadToday() {
        setDay(RoutinesRepository.dayKey(Calendar.getInstance()))
    }

    fun setDay(dayKey: String) {
        if (currentDayKey == dayKey) return
        currentDayKey = dayKey
        reg?.remove()
        val uid = RoutinesRepository.uidOrThrow()
        reg = RoutinesRepository.listenDay(uid, dayKey) { list ->
            _exercises.postValue(list.sortedBy { it.name.lowercase() })
        }
    }

    fun addExercise(ex: Exercise) {
        val day = currentDayKey ?: return
        val uid = RoutinesRepository.uidOrThrow()
        RoutinesRepository.addExercise(uid, day, ex)
    }

    fun updateExercise(ex: Exercise) {
        val day = currentDayKey ?: return
        val uid = RoutinesRepository.uidOrThrow()
        RoutinesRepository.updateExercise(uid, day, ex)
    }

    fun markDone(ex: Exercise) {
        val day = currentDayKey ?: return
        val uid = RoutinesRepository.uidOrThrow()
        val id = ex.id ?: return
        RoutinesRepository.markDone(uid, day, id, true)
    }

    fun deleteExercise(ex: Exercise) {
        val day = currentDayKey ?: return
        val uid = RoutinesRepository.uidOrThrow()
        val id = ex.id ?: return
        RoutinesRepository.deleteExercise(uid, day, id)
    }

    override fun onCleared() {
        reg?.remove()
        super.onCleared()
    }
}
