// app/src/main/java/com/example/equipohawknetwork/routines/RoutinesViewModel.kt
package com.example.equipohawknetwork.routines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ListenerRegistration
import java.util.Calendar

class RoutinesViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val repo = RoutinesRepository()
    private var listener: ListenerRegistration? = null

    private val _exercises = MutableLiveData<List<Exercise>>(emptyList())
    val exercises: LiveData<List<Exercise>> = _exercises

    private var currentDayKey: String = RoutinesRepository.dayKey(Calendar.getInstance())

    fun loadToday() {
        setDay(RoutinesRepository.dayKey(Calendar.getInstance()))
    }

    fun setDay(dayKey: String) {
        currentDayKey = dayKey
        val uid = auth.currentUser?.uid ?: return
        listener?.remove()
        listener = repo.listenDay(
            uid = uid,
            dayKey = dayKey,
            onChange = { _exercises.postValue(it) }
        )
    }

    fun addExercise(ex: Exercise) {
        val uid = auth.currentUser?.uid ?: return
        val nextOrder = (_exercises.value?.size ?: 0) + 1
        val data = mapOf(
            "name" to ex.name,
            "muscleGroup" to ex.muscleGroup,
            "sets" to ex.sets,
            "reps" to ex.reps,
            "weightKg" to ex.weightKg,
            "restSec" to ex.restSec,
            "order" to (ex.order ?: nextOrder),
            "done" to (ex.done),
            "createdAt" to FieldValue.serverTimestamp()
        )
        repo.addExercise(uid, currentDayKey, data)
    }

    fun updateExercise(ex: Exercise) {
        val uid = auth.currentUser?.uid ?: return
        val id = ex.id ?: return
        val data = mapOf(
            "name" to ex.name,
            "muscleGroup" to ex.muscleGroup,
            "sets" to ex.sets,
            "reps" to ex.reps,
            "weightKg" to ex.weightKg,
            "restSec" to ex.restSec
        )
        repo.updateExercise(uid, currentDayKey, id, data)
    }

    fun markDone(ex: Exercise) {
        val uid = auth.currentUser?.uid ?: return
        val id = ex.id ?: return
        repo.markDone(uid, currentDayKey, id, true)
    }

    override fun onCleared() {
        listener?.remove()
        super.onCleared()
    }
}
