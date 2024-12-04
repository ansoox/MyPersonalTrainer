package com.example.my_personal_trainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class Profile : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSave: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnSave = findViewById(R.id.btnSave)
        btnLogout = findViewById(R.id.btnLogout)

        val currentUsername = intent.getStringExtra("username") ?: ""
        val currentPassword = intent.getStringExtra("password") ?: ""
        val btnPage1 = findViewById<Button>(R.id.btnPage1)
        val btnPage2 = findViewById<Button>(R.id.btnPage2)
        val btnPage3 = findViewById<Button>(R.id.btnPage3)

        if (currentUsername.isNotEmpty() && currentPassword.isNotEmpty()) {
            etUsername.setText(currentUsername)
            etPassword.setText(currentPassword)
        }

        // Сохранение изменений
        btnSave.setOnClickListener {
            val newUsername = etUsername.text.toString()
            val newPassword = etPassword.text.toString()

            if (newUsername.isNotBlank() && newPassword.isNotBlank()) {
                updateUser(currentUsername, newUsername, newPassword)
                Toast.makeText(this, "Данные обновлены", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        // Выход из профиля
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnPage1.setOnClickListener {
            val intent = Intent(this, TrainingProgramsWindow::class.java)
            startActivity(intent)
        }

        btnPage2.setOnClickListener {
            val intent = Intent(this, Exercises::class.java)
            startActivity(intent)
        }

        btnPage3.setOnClickListener {
        }
    }

    private fun updateUser(oldUsername: String, newUsername: String, newPassword: String) {
        val userFile = File(filesDir, "users.txt")
        if (userFile.exists()) {
            val users = userFile.readLines().toMutableList()
            var userUpdated = false

            for (i in users.indices) {
                val data = users[i].split(":")
                if (data[0] == oldUsername) {
                    users[i] = "$newUsername:$newPassword"
                    userUpdated = true
                    break
                }
            }

            if (!userUpdated) {
                users.add("$newUsername:$newPassword")
            }

            userFile.writeText(users.joinToString("\n"))
        } else {
            userFile.writeText("$newUsername:$newPassword\n")
        }
    }
}