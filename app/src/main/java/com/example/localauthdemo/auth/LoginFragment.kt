package com.example.localauthdemo.auth

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
import com.example.localauthdemo.MainActivity
import com.example.localauthdemo.R
import com.example.localauthdemo.data.LocalUserStore
import com.example.localauthdemo.data.SessionManager

class LoginFragment : Fragment() {

    private val store by lazy { LocalUserStore(requireContext()) }
    private val session by lazy { SessionManager(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnGoToRegister = view.findViewById<Button>(R.id.btnGoToRegister)

        btnLogin.setOnClickListener {
            val email = etEmail.text?.toString()?.trim() ?: ""
            val pass = etPassword.text?.toString()?.trim() ?: ""

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)) {
                Toast.makeText(requireContext(), "Completa email y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (store.validateCredentials(email, pass)) {
                session.login(email)
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Credenciales inválidas", Toast.LENGTH_LONG).show()
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