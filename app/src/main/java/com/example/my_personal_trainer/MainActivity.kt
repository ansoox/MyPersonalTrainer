package com.example.my_personal_trainer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logFileContents()

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (authenticateUser(username, password)) {
                Toast.makeText(this, "Вход выполнен!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, TrainingProgramsWindow::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Проверка логина и пароля
    private fun authenticateUser(username: String, password: String): Boolean {
        val userFile = File(filesDir, "users.txt")

        if (userFile.exists()) {
            val users = userFile.readLines()
            for (user in users) {
                val data = user.split(":")
                if (data[0] == username && data[1] == password) {
                    return true
                }
            }
        }
        return false
    }

    private fun logFileContents() {
        val userFile = File(filesDir, "users.txt")
        if (userFile.exists()) {
            val fileContents = userFile.readText()
            Log.d("FileContent", fileContents) // Вывод в Logcat
        } else {
            Log.d("FileContent", "Файл не найден")
        }
    }
}