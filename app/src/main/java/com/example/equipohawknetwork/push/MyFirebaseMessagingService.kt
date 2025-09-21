package com.example.equipohawknetwork.push

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "Nuevo token: $token")
        // Guardarlo en Firestore si hay usuario logeado (opcional)
        FcmTokenManager.saveTokenIfLoggedIn(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val title = message.notification?.title ?: message.data["title"] ?: "GymTracker"
        val body  = message.notification?.body  ?: message.data["body"]  ?: "Tienes una nueva notificación"
        // Mostrar notificación local usando nuestro helper
        FcmNotificationHelper.showSimpleNotification(applicationContext, title, body)
    }
}
