package com.example.localauthdemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.localauthdemo.data.LocalUserStore
import com.example.localauthdemo.data.SessionManager

class MainActivity : AppCompatActivity() {

    private val session by lazy { SessionManager(this) }
    private val store by lazy { LocalUserStore(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tvWelcome)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val email = session.getCurrentUserEmail()
        val name = email?.let { store.getUserName(it) } ?: "invitado"
        tv.text = "Bienvenido, $name\n($email)"

        btnLogout.setOnClickListener {
            session.logout()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        if (!session.isLoggedIn()) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}