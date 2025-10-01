package com.example.equipohawknetwork.push

import android.os.Build
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

object FcmTokenManager {

    /** Guarda el token bajo users/{uid}/fcmTokens/{token} si hay sesión */
    fun saveTokenIfLoggedIn(token: String) {
        val user = FirebaseAuth.getInstance().currentUser ?: return
        val db = FirebaseFirestore.getInstance()
        val data = mapOf(
            "token" to token,
            "updatedAt" to FieldValue.serverTimestamp(),
            "device" to Build.MODEL,
            "brand" to Build.BRAND
        )
        db.collection("users")
            .document(user.uid)
            .collection("fcmTokens")
            .document(token) // id = token para evitar duplicados
            .set(data)
    }

    /** Obtiene el token actual y lo guarda si hay sesión */
    fun fetchAndSaveIfLoggedIn() {
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { t -> saveTokenIfLoggedIn(t) }
    }
}
