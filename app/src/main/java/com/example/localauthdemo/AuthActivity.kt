package com.example.localauthdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.localauthdemo.auth.LoginFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.authContainer, LoginFragment())
                .commit()
        }
    }
}