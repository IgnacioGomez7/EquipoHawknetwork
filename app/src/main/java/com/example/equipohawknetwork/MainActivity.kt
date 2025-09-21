package com.example.equipohawknetwork

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equipohawknetwork.push.FcmDebug
import com.example.equipohawknetwork.push.FcmTokenManager
import com.example.equipohawknetwork.push.NotificationPermissionHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // (Opcional) Etiquetar usuario en Crashlytics si hay sesión
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            FirebaseCrashlytics.getInstance().setUserId(it)
        }
        FirebaseCrashlytics.getInstance().log("MainActivity onCreate")

        // Permiso de notificaciones (Android 13+)
        NotificationPermissionHelper.requestIfNeeded(this)

        // Obtener/guardar token FCM si hay usuario
        FcmTokenManager.fetchAndSaveIfLoggedIn()

        // Prueba mínima Firestore
        val db = FirebaseFirestore.getInstance()
        val data = mapOf("mensaje" to "hola", "timestamp" to FieldValue.serverTimestamp())
        db.collection("pruebas").add(data)
            .addOnSuccessListener { ref ->
                Toast.makeText(this, "Firestore OK: ${ref.id}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Fallo al escribir", e)
                Toast.makeText(this, "Error Firestore: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }

        // === Botones en Dashboard ===
        val root = findViewById<ViewGroup>(android.R.id.content)

        // Cerrar sesión
        val btnLogout = Button(this).apply {
            text = "Cerrar sesión"
            setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        }
        root.addView(
            btnLogout,
            ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = 24 }
        )

        // Eventos (Compose)
        val btnEvents = Button(this).apply {
            text = "Eventos (Compose)"
            setOnClickListener {
                startActivity(Intent(this@MainActivity, EventsActivity::class.java))
            }
        }
        root.addView(
            btnEvents,
            ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = 96 }
        )

        // Mostrar/copiar token FCM
        val btnShowToken = Button(this).apply {
            text = "Mostrar token FCM"
            setOnClickListener {
                FcmDebug.copyCurrentToken(this@MainActivity) { token ->
                    FcmTokenManager.saveTokenIfLoggedIn(token)
                }
            }
        }
        root.addView(
            btnShowToken,
            ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = 168 }
        )

        // Suscribirse al tópico "test"
        val btnSubscribe = Button(this).apply {
            text = "Suscribirme al tema 'test'"
            setOnClickListener {
                FirebaseMessaging.getInstance().subscribeToTopic("test")
                    .addOnCompleteListener {
                        Toast.makeText(this@MainActivity, "Suscrito a 'test'", Toast.LENGTH_SHORT).show()
                    }
            }
        }
        root.addView(
            btnSubscribe,
            ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = 240 }
        )
    }

    override fun onStart() {
        super.onStart()
        FirebaseCrashlytics.getInstance().log("MainActivity onStart")
        val u = FirebaseAuth.getInstance().currentUser
        if (u == null || !u.isEmailVerified) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
