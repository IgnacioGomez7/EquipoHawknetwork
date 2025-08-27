package com.example.localauthdemo.data

import android.content.Context
import org.json.JSONObject
import com.example.localauthdemo.util.HashUtils

class LocalUserStore(private val context: Context) {
    private val prefs = context.getSharedPreferences("users_store", Context.MODE_PRIVATE)
    private val KEY = "users_json"

    private fun read(): JSONObject {
        val raw = prefs.getString(KEY, "{}") ?: "{}"
        return try { JSONObject(raw) } catch (e: Exception) { JSONObject() }
    }

    private fun write(obj: JSONObject) {
        prefs.edit().putString(KEY, obj.toString()).apply()
    }

    fun userExists(email: String): Boolean {
        val db = read()
        return db.has(email.lowercase())
    }

    fun addUser(name: String, email: String, password: String) {
        val db = read()
        val key = email.lowercase()
        val user = JSONObject().apply {
            put("name", name)
            put("email", key)
            put("passwordHash", HashUtils.sha256(password))
        }
        db.put(key, user)
        write(db)
    }

    fun validateCredentials(email: String, password: String): Boolean {
        val db = read()
        val key = email.lowercase()
        if (!db.has(key)) return false
        val user = db.getJSONObject(key)
        val saved = user.optString("passwordHash")
        return saved == HashUtils.sha256(password)
    }

    fun getUserName(email: String): String? {
        val db = read()
        val key = email.lowercase()
        return if (db.has(key)) db.getJSONObject(key).optString("name") else null
    }
}