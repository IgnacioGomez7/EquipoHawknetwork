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

class LoginActivity : AppCompatActivity() {

    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val progress = findViewById<View>(R.id.progress)
        val btnLogin = findViewById<View>(R.id.btnLogin)
        val tvGoRegister = findViewById<TextView>(R.id.tvGoRegister)
        val tvForgot = findViewById<TextView>(R.id.tvForgot)

        // Resalta "Regístrate"
        tvGoRegister.highlightWord("Regístrate")

        tvGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        tvForgot.setOnClickListener {
            val email = etEmail.text?.toString()?.trim().orEmpty()
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.error = "Ingresa un correo válido"
                return@setOnClickListener
            }
            auth.sendPasswordResetEmail(email)
                .addOnSuccessListener { Toast.makeText(this, "Te enviamos un correo para restablecer la contraseña.", Toast.LENGTH_LONG).show() }
                .addOnFailureListener { e -> Toast.makeText(this, "Error: ${e.localizedMessage}", Toast.LENGTH_LONG).show() }
        }

        btnLogin.setOnClickListener {
            tilEmail.error = null
            tilPassword.error = null

            val email = etEmail.text?.toString()?.trim().orEmpty()
            val pass = etPassword.text?.toString()?.trim().orEmpty()

            var ok = true
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.error = "Correo inválido"; ok = false
            }
            if (pass.length < 6) {
                tilPassword.error = "Mínimo 6 caracteres"; ok = false
            }
            if (!ok) return@setOnClickListener

            setLoading(true, progress, btnLogin)
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { setLoading(false, progress, btnLogin) }
                .addOnSuccessListener {
                    val u = auth.currentUser
                    if (u != null && u.isEmailVerified) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Verifica tu correo antes de iniciar sesión.", Toast.LENGTH_LONG).show()
                        auth.signOut()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "No se pudo iniciar sesión: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
