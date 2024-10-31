package com.example.my_personal_trainer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrainingProgramsWindow : AppCompatActivity() {
    private val trainingList = listOf(
        "Объемные ягодицы", "Тренировка на ноги", "Красивый пресс",
        "Широкая спина", "Рельефный верх", "Объемные руки"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_programs)

        Log.d("TrainingPrograms", "Training list size: ${trainingList.size}")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TrainingAdapter(this, trainingList)

        val btnPage1 = findViewById<Button>(R.id.btnPage1)
        val btnPage2 = findViewById<Button>(R.id.btnPage2)
        val btnPage3 = findViewById<Button>(R.id.btnPage3)

        btnPage1.setOnClickListener {
            // Уже на текущей странице, ничего не делаем
        }

        btnPage2.setOnClickListener {
            // Переход на вторую страницу
            val intent = Intent(this, Exercises::class.java)
            startActivity(intent)
        }

        btnPage3.setOnClickListener {
            // Переход на третью страницу
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}