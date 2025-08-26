package com.example.equipohawknetwork.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.equipohawknetwork.MainActivity
import com.example.equipohawknetwork.R
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnGoToRegister = view.findViewById<Button>(R.id.btnGoToRegister)
        val btnResend = view.findViewById<Button>(R.id.btnResendVerification)

        btnLogin.setOnClickListener {
            val email = etEmail.text?.toString()?.trim() ?: ""
            val pass = etPassword.text?.toString()?.trim() ?: ""

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(requireContext(), "Completa email y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user?.isEmailVerified == true) {
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        requireActivity().finish()
                    } else {
                        Toast.makeText(requireContext(),
                            "Tu correo no está verificado. Revisa tu bandeja o reenvía verificación.",
                            Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(requireContext(),
                        task.exception?.localizedMessage ?: "Error al iniciar sesión",
                        Toast.LENGTH_LONG).show()
                }
            }
        }

        btnResend.setOnClickListener {
            val user = auth.currentUser
            if (user != null && user.isEmailVerified == false) {
                user.sendEmailVerification().addOnCompleteListener { t ->
                    if (t.isSuccessful) {
                        Toast.makeText(requireContext(), "Verificación reenviada", Toast.LENGTH_SHORT).show()
                        auth.signOut()
                    } else {
                        Toast.makeText(requireContext(),
                            t.exception?.localizedMessage ?: "No se pudo reenviar",
                            Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(),
                    "Primero inicia sesión para reenviar la verificación.",
                    Toast.LENGTH_LONG).show()
            }
        }

        btnGoToRegister.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.authContainer, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}