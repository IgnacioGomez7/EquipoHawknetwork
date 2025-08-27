package com.example.localauthdemo.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.localauthdemo.R
import com.example.localauthdemo.data.LocalUserStore

class RegisterFragment : Fragment() {

    private val store by lazy { LocalUserStore(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etName = view.findViewById<EditText>(R.id.etName)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val etConfirm = view.findViewById<EditText>(R.id.etConfirm)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)
        val btnGoToLogin = view.findViewById<Button>(R.id.btnGoToLogin)

        btnRegister.setOnClickListener {
            val name = etName.text?.toString()?.trim() ?: ""
            val email = etEmail.text?.toString()?.trim() ?: ""
            val pass = etPassword.text?.toString()?.trim() ?: ""
            val confirm = etConfirm.text?.toString()?.trim() ?: ""

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirm)) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass != confirm) {
                Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (store.userExists(email)) {
                Toast.makeText(requireContext(), "Ese email ya está registrado", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            store.addUser(name, email, pass)
            Toast.makeText(requireContext(), "Cuenta creada. Ya puedes iniciar sesión.", Toast.LENGTH_LONG).show()
            parentFragmentManager.popBackStack()
        }

        btnGoToLogin.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}