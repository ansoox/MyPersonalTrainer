package com.example.my_personal_trainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Exercises : AppCompatActivity() {

    private val exercisesList = listOf(
        "Ягодичный мост", "Румынская тяга", "Жим ногами", "Тяга верхнего блока",
        "Махи в кроссовере", "Разгибания ног сидя в тренажере на бицепс",
        "Сгибания ног лежа в тренажере на бицепс", "Французский жим", "Молотки",
        "Бицепс в блоке", "Разгибания ног в тренажере", "Жим ногами лежа в смите",
        "Гиперэкстензия", "Тяга блока за голову", "Жим в хамере",
        "Тяга горизонтально", "Тяга прямых рук", "Выпады"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TrainingExercisesAdapter(this, exercisesList)

        val btnPage1 = findViewById<Button>(R.id.btnPage1)
        val btnPage2 = findViewById<Button>(R.id.btnPage2)
        val btnPage3 = findViewById<Button>(R.id.btnPage3)

        btnPage1.setOnClickListener {
            val intent = Intent(this, TrainingProgramsWindow::class.java)
            startActivity(intent)
        }

        btnPage2.setOnClickListener {
        }

        btnPage3.setOnClickListener {
            // Переход на третью страницу
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}