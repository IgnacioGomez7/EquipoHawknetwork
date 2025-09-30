package com.example.equipohawknetwork

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.equipohawknetwork.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }

    // Google
    private lateinit var googleClient: GoogleSignInClient
    private val googleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            if (idToken.isNullOrEmpty()) {
                fail("No se obtuvo idToken de Google")
                return@registerForActivityResult
            }
            val cred = GoogleAuthProvider.getCredential(idToken, null)
            binding.tvEstado.text = "Entrando con Google..."
            auth.signInWithCredential(cred)
                .addOnSuccessListener {
                    auth.currentUser?.let { u ->
                        ensureUserDoc(u)
                        goToMain()
                    } ?: fail("No se obtuvo usuario de Firebase")
                }
                .addOnFailureListener { e -> fail(e.localizedMessage ?: "Error con Google/Firebase") }
        } catch (e: ApiException) {
            fail("Google Sign-In falló: ${e.statusCode}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Email/pass
        binding.btnLogin.setOnClickListener { doLoginEmailPass() }
        binding.btnReenviar.setOnClickListener { resendVerification() }
        binding.tvIrRegistro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Google: configurar cliente
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // generado por google-services.json
            .requestEmail()
            .build()
        googleClient = GoogleSignIn.getClient(this, gso)

        // Google: botón
        findViewById<Button>(R.id.btnGoogle).setOnClickListener {
            binding.tvEstado.text = "Abriendo Google..."
            googleLauncher.launch(googleClient.signInIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            user.reload().addOnCompleteListener {
                if (auth.currentUser?.isEmailVerified == true || user.providerData.any { it.providerId == "google.com" }) {
                    // Usuarios Google ya vienen verificados
                    goToMain()
                }
            }
        }
    }

    // ====== Email/Password ======

    private fun doLoginEmailPass() {
        val email = binding.etEmail.text?.toString()?.trim().orEmpty()
        val pass  = binding.etPass.text?.toString()?.trim().orEmpty()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { toast("Correo inválido"); return }
        if (pass.isEmpty()) { toast("Ingresa tu contraseña"); return }

        binding.btnLogin.isEnabled = false
        binding.tvEstado.text = "Iniciando sesión..."

        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener {
                auth.currentUser?.reload()?.addOnCompleteListener {
                    val u = auth.currentUser
                    if (u != null && (u.isEmailVerified || u.providerData.any { it.providerId == "google.com" })) {
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

    // ====== Firestore user doc ======

    private fun ensureUserDoc(user: FirebaseUser) {
        val ref = db.collection("users").document(user.uid)
        ref.get().addOnSuccessListener { snap ->
            if (!snap.exists()) {
                ref.set(
                    mapOf(
                        "email" to (user.email ?: ""),
                        "displayName" to (user.displayName ?: ""),
                        "createdAt" to FieldValue.serverTimestamp(),
                        "weeklyTargets" to mapOf("sets" to 12, "volumeKg" to 10000, "days" to 4),
                        "providers" to user.providerData.map { it.providerId }
                    )
                )
            } else {
                // opcional: actualizar providers
                ref.update("providers", user.providerData.map { it.providerId })
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun fail(msg: String) { binding.tvEstado.text = msg; toast(msg) }
    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
