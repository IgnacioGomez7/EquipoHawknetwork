package com.example.equipohawknetwork

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equipohawknetwork.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { doLogin() }
        binding.btnReenviar.setOnClickListener { resendVerification() }

        // (Opcional) Navegar a registro
        binding.tvIrRegistro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            user.reload().addOnCompleteListener {
                if (auth.currentUser?.isEmailVerified == true) goToMain()
            }
        }
    }

    private fun doLogin() {
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val pass  = binding.etPass.text?.toString()?.trim().orEmpty()
        if (email.isEmpty() || pass.isEmpty()) {
            toast("Correo y contraseña requeridos"); return
        }

        binding.btnLogin.isEnabled = false
        binding.tvEstado.text = "Iniciando sesión..."

        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                auth.currentUser?.reload()?.addOnCompleteListener {
                    val u = auth.currentUser
                    if (u != null && u.isEmailVerified) {
                        ensureUserDoc(u)
                        goToMain()
                    } else {
                        binding.tvEstado.text = "Tu correo no está verificado. Revisa tu bandeja o pulsa Reenviar."
                        toast("Correo no verificado")
                    }
                    binding.btnLogin.isEnabled = true
                }
            }
            .addOnFailureListener { e ->
                binding.tvEstado.text = e.localizedMessage ?: "Error al iniciar sesión"
                binding.btnLogin.isEnabled = true
            }
    }

    private fun resendVerification() {
        val user = auth.currentUser ?: run {
            toast("Primero inicia sesión para reenviar verificación"); return
        }
        user.sendEmailVerification()
            .addOnSuccessListener {
                toast("Correo de verificación enviado")
                try { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"))) } catch (_: Exception) {}
            }
            .addOnFailureListener { e -> toast(e.localizedMessage ?: "No se pudo enviar el correo") }
    }

    private fun ensureUserDoc(user: FirebaseUser) {
        val ref = db.collection("users").document(user.uid)
        ref.get().addOnSuccessListener { snap ->
            if (!snap.exists()) {
                ref.set(
                    mapOf(
                        "email" to (user.email ?: ""),
                        "displayName" to (user.displayName ?: ""),
                        "createdAt" to FieldValue.serverTimestamp(),
                        "weeklyTargets" to mapOf("sets" to 12, "volumeKg" to 10000, "days" to 4)
                    )
                )
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
