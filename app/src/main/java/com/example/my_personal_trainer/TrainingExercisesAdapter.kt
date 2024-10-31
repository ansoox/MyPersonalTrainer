package com.example.my_personal_trainer

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrainingExercisesAdapter(
    private val context: Context,
    private val exercisesList: List<String>
) : RecyclerView.Adapter<TrainingExercisesAdapter.ExerciseViewHolder>() {

    private val exerciseData = mapOf(
        "Ягодичный мост" to Pair("Описание для упражнения Ягодичный мост.", R.drawable.glute_bridge),
        "Бицепс в блоке" to Pair("Описание для упражнения Бицепс в блоке.", R.drawable.biceps_v_bloke),
        "Выпады" to Pair("Описание для упражнения Ягодичный мост.", R.drawable.vipady_nazad_s_gantelyami),
        "Гиперэкстензия" to Pair("Описание для упражнения Гиперэкстензия.", R.drawable.glute_bridge),
        "Жим в хамере" to Pair("Описание для упражнения Жим в хамере.", R.drawable.vertical_jim),
        "Жим ногами" to Pair("Описание для упражнения Жим ногами.", R.drawable.jim_nogami),
        "Жим ногами лежа в смите" to Pair("Описание для упражнения Жим ногами лежа в смите.", R.drawable.obratni_jim_v_smite),
        "Махи в кроссовере" to Pair("Описание для упражнения Махи в кроссовере.", R.drawable.glute_bridge),
        "Молотки" to Pair("Описание для упражнения Молотки.", R.drawable.glute_bridge),
        "Разгибания ног в тренажере" to Pair("Описание для упражнения Разгибания ног в тренажере.", R.drawable.glute_bridge),
        "Разгибания ног сидя в тренажере на бицепс" to Pair("Описание для упражнения Разгибания ног сидя в тренажере на бицепс.", R.drawable.glute_bridge),
        "Румынская тяга" to Pair("Описание для упражнения Румынская тяга.", R.drawable.glute_bridge),
        "Сгибания ног лежа в тренажере на бицепс" to Pair("Описание для упражнения Сгибания ног лежа в тренажере на бицепс.", R.drawable.glute_bridge),
        "Тяга блока за голову" to Pair("Описание для упражнения Тяга блока за голову.", R.drawable.glute_bridge),
        "Тяга верхнего блока" to Pair("Описание для упражнения Тяга верхнего блока.", R.drawable.glute_bridge),
        "Тяга горизонтально" to Pair("Описание для упражнения Тяга горизонтально.", R.drawable.glute_bridge),
        "Тяга прямых рук" to Pair("Описание для упражнения Тяга прямых рук.", R.drawable.glute_bridge),
        "Французский жим" to Pair("Описание для упражнения Французский жим.", R.drawable.glute_bridge)
        // Добавьте пары (описание, изображение) для других упражнений
    )

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.exerciseName)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val exerciseName = exercisesList[position]
                val exerciseDescription = exerciseData[exerciseName]?.first ?: "Описание отсутствует"
                val exerciseImageResId = exerciseData[exerciseName]?.second ?: R.drawable.vipady_nazad_s_gantelyami

                // Переход на детальную активность
                val intent = Intent(context, ExerciseDetailActivity::class.java).apply {
                    putExtra("exercise_name", exerciseName)
                    putExtra("exercise_description", exerciseDescription)
                    putExtra("exercise_image", exerciseImageResId)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.exerciseName.text = exercisesList[position]
    }

    override fun getItemCount(): Int = exercisesList.size
}