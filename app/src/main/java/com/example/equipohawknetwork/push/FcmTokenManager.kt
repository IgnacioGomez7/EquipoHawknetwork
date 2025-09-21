package com.example.equipohawknetwork.push

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

object FcmTokenManager {

    private const val TAG = "FcmTokenManager"

    /** Obtiene el token actual y lo guarda en Firestore si hay usuario logeado. */
    fun fetchAndSaveIfLoggedIn() {
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                Log.d(TAG, "FCM token: $token")
                saveTokenIfLoggedIn(token)
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "No se pudo obtener token FCM", e)
            }
    }

    /** Llama esto desde onNewToken o cuando quieras re-registrar. */
    fun saveTokenIfLoggedIn(token: String) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()
        // Guardamos en subcolección para evitar duplicados y mantener historial
        val doc = db.collection("users").document(uid)
            .collection("fcmTokens").document(token)
        doc.set(
            mapOf(
                "token" to token,
                "createdAt" to FieldValue.serverTimestamp(),
                "platform" to "android"
            )
        )
    }
}

