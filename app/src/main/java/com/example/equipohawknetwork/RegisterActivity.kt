package com.example.equipohawknetwork

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.equipohawknetwork.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener { doRegister() }
        binding.tvIrLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun doRegister() {
        val name  = binding.etNombre.text?.toString()?.trim().orEmpty()
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val pass  = binding.etPass.text?.toString()?.trim().orEmpty()
        val pass2 = binding.etConfirm.text?.toString()?.trim().orEmpty()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { toast("Correo inválido"); return }
        if (pass.length < 6) { toast("La contraseña debe tener al menos 6 caracteres"); return }
        if (pass != pass2) { toast("Las contraseñas no coinciden"); return }

        binding.btnRegistrar.isEnabled = false
        binding.tvEstado.text = "Creando cuenta..."

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnSuccessListener { res ->
                val user = res.user ?: run {
                    fail("No se pudo crear el usuario"); binding.btnRegistrar.isEnabled = true; return@addOnSuccessListener
                }
                val uid = user.uid
                db.collection("users").document(uid)
                    .set(
                        mapOf(
                            "email" to email,
                            "displayName" to name,
                            "createdAt" to FieldValue.serverTimestamp(),
                            "weeklyTargets" to mapOf("sets" to 12, "volumeKg" to 10000, "days" to 4)
                        )
                    )
                    .addOnSuccessListener {
                        user.sendEmailVerification()
                            .addOnSuccessListener {
                                binding.tvEstado.text = "Cuenta creada. Te enviamos un correo de verificación."
                                toast("Verifica tu correo y luego inicia sesión")
                                FirebaseAuth.getInstance().signOut()
                            }
                            .addOnFailureListener { e -> fail(e.localizedMessage ?: "No se pudo enviar verificación") }
                    }
                    .addOnFailureListener { e -> fail(e.localizedMessage ?: "No se pudo guardar el usuario") }
                    .addOnCompleteListener { binding.btnRegistrar.isEnabled = true }
            }
            .addOnFailureListener { e ->
                fail(e.localizedMessage ?: "Error al registrar")
                binding.btnRegistrar.isEnabled = true
            }
    }

    private fun fail(msg: String) { binding.tvEstado.text = msg; toast(msg) }
    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
