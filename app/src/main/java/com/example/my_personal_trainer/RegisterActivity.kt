package com.example.my_personal_trainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNewUsername: EditText
    private lateinit var etNewPassword: EditText
    private lateinit var btnSave: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNewUsername = findViewById(R.id.etNewUsername)
        etNewPassword = findViewById(R.id.etNewPassword)
        btnSave = findViewById(R.id.btnSave)
        btnBack = findViewById(R.id.btnBack)


        btnBack.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            val newUsername = etNewUsername.text.toString()
            val newPassword = etNewPassword.text.toString()

            if (newUsername.isNotEmpty() && newPassword.isNotEmpty()) {
                if (isUsernameExists(newUsername)) {
                    // Пользователь с таким именем уже существует
                    Toast.makeText(this, "Пользователь с таким именем уже существует, придумайте другой", Toast.LENGTH_SHORT).show()
                } else {
                    // Сохраняем нового пользователя
                    saveUser(newUsername, newPassword)
                    Toast.makeText(this, "Пользователь сохранен!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, TrainingProgramsWindow::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isUsernameExists(username: String): Boolean {
        val userFile = File(filesDir, "users.txt")
        if (userFile.exists()) {
            val users = userFile.readLines()
            for (user in users) {
                val data = user.split(":")
                if (data[0] == username) {
                    return true
                }
            }
        }
        return false
    }

    // Сохранение данных пользователя в файл
    private fun saveUser(username: String, password: String) {
        val userFile = File(filesDir, "users.txt")
        val writer = FileWriter(userFile, true)
        writer.append("$username:$password\n")
        writer.flush()
        writer.close()
    }
}