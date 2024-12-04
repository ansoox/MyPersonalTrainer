package com.example.my_personal_trainer

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrainingDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_detail)

        // Получаем название тренировки и список упражнений из интента
        val trainingName = intent.getStringExtra("training_title") ?: "Тренировка"
        val exercisesList = intent.getStringArrayListExtra("exercises_list") ?: arrayListOf()

        // Устанавливаем название тренировки
        title = trainingName

        // Настраиваем RecyclerView для отображения списка упражнений
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TrainingExercisesAdapter(this, exercisesList)

        // Обработка нажатия на кнопку "Назад"
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Закрываем текущую активность и возвращаемся назад
        }

    }
}