package com.example.my_personal_trainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class CustomExercisesActivity : AppCompatActivity() {

    private val exercisesFileName = "custom_exercises.json"
    private val exercisesList = mutableListOf<CustomExercise>()
    private lateinit var adapter: CustomExercisesAdapter
    private var selectedPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_exercises)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnAddCustomEx = findViewById<Button>(R.id.btnAddCustomEx)
        val btnDeleteExercise = findViewById<Button>(R.id.btnDeleteExercise)

        // Установка LayoutManager и адаптера
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CustomExercisesAdapter(exercisesList) { position ->
            selectedPosition = position
            Toast.makeText(this, "Выбрано упражнение: ${exercisesList[position].name}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

        // Загрузка упражнений из файла
        loadExercises()
        adapter.notifyDataSetChanged()

        // Переход на страницу создания упражнения
        btnAddCustomEx.setOnClickListener {
            val intent = Intent(this, CreateCustomExerciseActivity::class.java)
            startActivity(intent)
        }

        // Удаление выбранного упражнения
        btnDeleteExercise.setOnClickListener {
            selectedPosition?.let { position ->
                exercisesList.removeAt(position)
                saveExercises()
                adapter.notifyDataSetChanged()
                selectedPosition = null
                Toast.makeText(this, "Упражнение удалено", Toast.LENGTH_SHORT).show()
            } ?: Toast.makeText(this, "Выберите упражнение для удаления", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadExercises() {
        val file = File(filesDir, exercisesFileName)
        if (file.exists()) {
            val json = file.readText()
            val type = object : TypeToken<MutableList<CustomExercise>>() {}.type
            val loadedExercises: MutableList<CustomExercise> = Gson().fromJson(json, type)
            exercisesList.clear()
            exercisesList.addAll(loadedExercises)
        }
    }

    private fun saveExercises() {
        val file = File(filesDir, exercisesFileName)
        val json = Gson().toJson(exercisesList)
        file.writeText(json)
    }
}