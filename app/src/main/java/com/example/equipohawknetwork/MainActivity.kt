package com.example.equipohawknetwork

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Crashlytics: etiqueta uid si hay sesión (se mantiene)
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            FirebaseCrashlytics.getInstance().setUserId(it)
        }
        FirebaseCrashlytics.getInstance().log("MainActivity onCreate")

        // Permiso notificaciones (Android 13+)
        requestPostNotificationsIfNeeded()

        // Prueba mínima Firestore (se mantiene)
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

        // === Botones en Dashboard (ADD-ONLY, sin debug de FCM) ===
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

        // Ir a Eventos (Compose)
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
        // === END ===
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

    private fun requestPostNotificationsIfNeeded() {
        if (Build.VERSION.SDK_INT >= 33) {
            val granted = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            if (!granted) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    2001
                )
            }
        }
    }
}
