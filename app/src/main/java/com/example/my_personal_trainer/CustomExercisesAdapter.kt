package com.example.my_personal_trainer

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomExercisesAdapter(
    private val exercises: List<CustomExercise>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CustomExercisesAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textViewExerciseName)
        val descriptionTextView: TextView = view.findViewById(R.id.textViewExerciseDescription)
        val imageView: ImageView = view.findViewById(R.id.imageViewExercise)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_custom_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.nameTextView.text = exercise.name
        holder.descriptionTextView.text = exercise.description

        // Установка изображения, если есть
        if (exercise.imageUri != null) {
            holder.imageView.setImageURI(Uri.parse(exercise.imageUri))
        } else {
            holder.imageView.setImageResource(R.drawable.placeholder_image) // Заглушка
        }

        // Обработчик клика по элементу
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int = exercises.size
}
