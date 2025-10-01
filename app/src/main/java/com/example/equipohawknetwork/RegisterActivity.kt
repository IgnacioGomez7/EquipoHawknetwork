package com.example.equipohawknetwork

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val tilPass = findViewById<TextInputLayout>(R.id.tilPass)
        val etPass = findViewById<TextInputEditText>(R.id.etPass)
        val tilConfirm = findViewById<TextInputLayout>(R.id.tilConfirm)
        val etConfirm = findViewById<TextInputEditText>(R.id.etConfirm)
        val progress = findViewById<View>(R.id.progress)
        val btnRegister = findViewById<View>(R.id.btnRegister)
        val tvGoLogin = findViewById<TextView>(R.id.tvGoLogin)

        // Resalta "Inicio de sesión"
        tvGoLogin.highlightWord("Inicio de sesión")

        tvGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnRegister.setOnClickListener {
            tilEmail.error = null
            tilPass.error = null
            tilConfirm.error = null

            val email = etEmail.text?.toString()?.trim().orEmpty()
            val pass = etPass.text?.toString()?.trim().orEmpty()
            val confirm = etConfirm.text?.toString()?.trim().orEmpty()

            var ok = true
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.error = "Correo inválido"; ok = false
            }
            if (pass.length < 6) {
                tilPass.error = "Mínimo 6 caracteres"; ok = false
            }
            if (confirm != pass) {
                tilConfirm.error = "Las contraseñas no coinciden"; ok = false
            }
            if (!ok) return@setOnClickListener

            setLoading(true, progress, btnRegister)
            auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { setLoading(false, progress, btnRegister) }
                .addOnSuccessListener {
                    auth.currentUser?.sendEmailVerification()
                    Toast.makeText(this, "¡Cuenta creada! Te enviamos un correo de verificación.", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "No se pudo registrar: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun setLoading(loading: Boolean, progress: View, btn: View) {
        progress.visibility = if (loading) View.VISIBLE else View.GONE
        btn.isEnabled = !loading
    }

    // Extensión local para resaltar una palabra en un TextView (color primario + negrita)
    private fun TextView.highlightWord(word: String) {
        val src = text?.toString() ?: return
        val start = src.indexOf(word, ignoreCase = true)
        if (start < 0) return
        val end = start + word.length
        val span = android.text.SpannableString(src)
        val color = com.google.android.material.color.MaterialColors.getColor(
            this, com.google.android.material.R.attr.colorPrimary, 0
        )
        span.setSpan(android.text.style.StyleSpan(android.graphics.Typeface.BOLD), start, end, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(android.text.style.ForegroundColorSpan(color), start, end, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = span
    }
}
