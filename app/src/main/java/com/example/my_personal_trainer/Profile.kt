package com.example.my_personal_trainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnPage1 = findViewById<Button>(R.id.btnPage1)
        val btnPage2 = findViewById<Button>(R.id.btnPage2)
        val btnPage3 = findViewById<Button>(R.id.btnPage3)

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
}