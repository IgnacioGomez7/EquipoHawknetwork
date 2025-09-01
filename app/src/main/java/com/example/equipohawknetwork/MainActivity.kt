package com.example.equipohawknetwork

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 👇 Prueba mínima: escribe “hola” en Firestore al entrar (para validar conexión)
        val db = FirebaseFirestore.getInstance()
        val data = mapOf("mensaje" to "hola", "timestamp" to FieldValue.serverTimestamp())
        db.collection("pruebas").add(data)
            .addOnSuccessListener { ref -> Toast.makeText(this, "Firestore OK: ${ref.id}", Toast.LENGTH_SHORT).show() }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Fallo al escribir", e)
                Toast.makeText(this, "Error Firestore: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
    }

    override fun onStart() {
        super.onStart()
        val u = FirebaseAuth.getInstance().currentUser
        if (u == null || !u.isEmailVerified) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
