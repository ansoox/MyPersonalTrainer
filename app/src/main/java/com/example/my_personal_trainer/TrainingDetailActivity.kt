package com.example.my_personal_trainer

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrainingDetailActivity : AppCompatActivity() {

    private val exercisesList = listOf(
        "Ягодичный мост", "Румынская тяга", "Жим ногами", "Тяга верхнего блока",
        "Махи в кроссовере", "Разгибания ног сидя в тренажере на бицепс",
        "Сгибания ног лежа в тренажере на бицепс", "Французский жим", "Молотки",
        "Бицепс в блоке"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_detail)

        val trainingName = intent.getStringExtra("training_name")
        val detailTextView = findViewById<TextView>(R.id.tvTrainingDetail)
        detailTextView.text = trainingName

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TrainingExercisesAdapter(this, exercisesList)

        // Обработка нажатия на кнопку "Назад"
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Закрывает текущую активность и возвращается к предыдущей
        }
    }
}