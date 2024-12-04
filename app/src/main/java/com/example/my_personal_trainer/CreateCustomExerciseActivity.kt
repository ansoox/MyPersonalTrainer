package com.example.my_personal_trainer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

data class CustomExercise(
    val name: String,
    val description: String,
    val imageUri: String? = null
)

class CreateCustomExerciseActivity : AppCompatActivity() {

    private val exercisesFileName = "custom_exercises.json"
    private val exercisesList = mutableListOf<CustomExercise>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_custom_exercise)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val btnAddImage = findViewById<Button>(R.id.btnAddImage)
        val btnAddCustomEx = findViewById<Button>(R.id.btnAddCustomEx)

        // Загрузка сохранённых упражнений
        loadExercises()

        // Для выбора изображения
        var selectedImageUri: String? = null
        val getImage =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    selectedImageUri = data?.data.toString()
                    imageView.setImageURI(data?.data)
                }
            }

        btnAddImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            getImage.launch(intent)
        }

        btnAddCustomEx.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val description = editTextDescription.text.toString().trim()

            if (name.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newExercise = CustomExercise(name, description, selectedImageUri)
            exercisesList.add(newExercise)

            saveExercises()
            Toast.makeText(this, "Упражнение добавлено!", Toast.LENGTH_SHORT).show()

            // Очистка полей
            editTextName.text.clear()
            editTextDescription.text.clear()
            imageView.setImageResource(0)
            selectedImageUri = null

            val intent = Intent(this, CustomExercisesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadExercises() {
        val file = File(filesDir, exercisesFileName)
        if (file.exists()) {
            val json = file.readText()
            val type = object : TypeToken<MutableList<CustomExercise>>() {}.type
            val loadedExercises: MutableList<CustomExercise> = Gson().fromJson(json, type)
            exercisesList.addAll(loadedExercises)
        }
    }

    private fun saveExercises() {
        val file = File(filesDir, exercisesFileName)
        val json = Gson().toJson(exercisesList)
        file.writeText(json)
    }
}
