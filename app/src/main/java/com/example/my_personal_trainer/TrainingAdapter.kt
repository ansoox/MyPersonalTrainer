package com.example.my_personal_trainer

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrainingAdapter(
    private val context: Context,
    private val trainingList: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trainingName: TextView = itemView.findViewById(R.id.trainingName)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(trainingList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_training, parent, false)
        return TrainingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.trainingName.text = trainingList[position]
    }

    override fun getItemCount(): Int = trainingList.size
}