package com.example.my_personal_trainer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrainingProgramsWindow : AppCompatActivity() {
    private val trainingData = mapOf(
        "Объемные ягодицы" to listOf("Ягодичный мост", "Выпады", "Разгибания ног в тренажере"),
        "Тренировка на ноги" to listOf("Жим ногами", "Румынская тяга", "Сгибания ног лежа в тренажере на бицепс"),
        "Красивый пресс" to listOf("Гиперэкстензия", "Тяга прямых рук"),
        "Широкая спина" to listOf("Тяга блока за голову", "Тяга верхнего блока", "Тяга горизонтально"),
        "Рельефный верх" to listOf("Жим в хамере", "Французский жим"),
        "Объемные руки" to listOf("Бицепс в блоке", "Молотки")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_programs)

        val trainingList = trainingData.keys.toList()

        Log.d("TrainingPrograms", "Training list size: ${trainingList.size}")

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TrainingAdapter(this, trainingList) { trainingName ->
            // Передаем название тренировки и связанные упражнения в TrainingDetailActivity
            val exercises = trainingData[trainingName] ?: emptyList()
            val intent = Intent(this, TrainingDetailActivity::class.java).apply {
                putExtra("training_title", trainingName)
                putStringArrayListExtra("exercises_list", ArrayList(exercises))
            }
            startActivity(intent)
        }

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