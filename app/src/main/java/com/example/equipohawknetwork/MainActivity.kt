package com.example.equipohawknetwork

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.example.equipohawknetwork.routines.RoutinesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Crashlytics: etiqueta uid si hay sesión
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            FirebaseCrashlytics.getInstance().setUserId(it)
        }
        FirebaseCrashlytics.getInstance().log("MainActivity onCreate")

        // Permiso notificaciones (Android 13+)
        requestPostNotificationsIfNeeded()

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

        // Toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Hawk Network"

        // Botones
        val btnRoutines = findViewById<Button>(R.id.btnRoutines)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnRoutines.setOnClickListener {
            startActivity(Intent(this@MainActivity, RoutinesActivity::class.java))
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
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
