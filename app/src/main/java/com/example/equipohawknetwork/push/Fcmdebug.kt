package com.example.equipohawknetwork.push

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging

object FcmDebug {
    fun copyCurrentToken(context: Context, onToken: (String) -> Unit = {}) {
        FirebaseMessaging.getInstance().token
            .addOnSuccessListener { token ->
                Log.d("FCM", "token: $token")
                if (token.isNullOrBlank()) {
                    Toast.makeText(context, "Sin token FCM aún", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }
                val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cm.setPrimaryClip(ClipData.newPlainText("fcm", token))
                Toast.makeText(context, "Token copiado al portapapeles", Toast.LENGTH_SHORT).show()
                onToken(token)
            }
            .addOnFailureListener {
                Toast.makeText(context, "No se pudo obtener token FCM", Toast.LENGTH_SHORT).show()
            }
    }
}

