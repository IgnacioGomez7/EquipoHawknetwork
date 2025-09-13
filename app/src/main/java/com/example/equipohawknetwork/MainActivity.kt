package com.example.equipohawknetwork

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // (Opcional) Etiquetar usuario en Crashlytics si hay sesión
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            FirebaseCrashlytics.getInstance().setUserId(it)
        }
        FirebaseCrashlytics.getInstance().log("MainActivity onCreate")

        // Prueba mínima: escribe “hola” en Firestore al entrar (para validar conexión)
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

        // Botón: Cerrar sesión
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

        // Botón: Eventos (Compose) — abre la nueva Activity con NavHost (lista ↔ formulario)
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
            ).apply { topMargin = 96 } // debajo del logout
        )

        // 🔴 Se eliminó el botón de "Crash de prueba (debug)" de Crashlytics (único cambio destructivo)
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

    /** True si la app está en modo debug (puedes dejarlo aunque ya no se use). */
    private fun isDebugBuild(): Boolean {
        return (applicationInfo.flags and android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE) != 0
    }
}

