package com.example.equipohawknetwork.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.equipohawknetwork.R
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment() {

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val db by lazy { FirebaseFirestore.getInstance() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etName = view.findViewById<EditText>(R.id.etName)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
        val btnGoToLogin = view.findViewById<Button>(R.id.btnGoToLogin)

        btnRegister.setOnClickListener {
            val name = etName.text?.toString()?.trim() ?: ""
            val email = etEmail.text?.toString()?.trim() ?: ""
            val pass = etPassword.text?.toString()?.trim() ?: ""

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser ?: return@addOnCompleteListener
                    user.sendEmailVerification()

                    val uid = user.uid
                    val data = hashMapOf(
                        "email" to email,
                        "displayName" to name,
                        "createdAt" to Timestamp.now()
                    )
                    db.collection("users").document(uid).set(data)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(),
                                "Cuenta creada. Verifica tu correo.",
                                Toast.LENGTH_LONG).show()
                            parentFragmentManager.popBackStack()
                        }
                        .addOnFailureListener {
                            Toast.makeText(requireContext(),
                                "Registrado, pero no se guardó perfil: ${it.localizedMessage}",
                                Toast.LENGTH_LONG).show()
                            parentFragmentManager.popBackStack()
                        }
                } else {
                    Toast.makeText(requireContext(),
                        task.exception?.localizedMessage ?: "Error al registrar",
                        Toast.LENGTH_LONG).show()
                }
            }
        }

        btnGoToLogin.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}