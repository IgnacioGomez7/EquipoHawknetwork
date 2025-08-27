package com.example.localauthdemo.data

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE)

    fun login(email: String) {
        prefs.edit().putString("current_user_email", email).apply()
    }

    fun logout() {
        prefs.edit().remove("current_user_email").apply()
    }

    fun isLoggedIn(): Boolean = prefs.contains("current_user_email")

    fun getCurrentUserEmail(): String? = prefs.getString("current_user_email", null)
}