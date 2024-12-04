package com.example.my_personal_trainer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.bumptech.glide.Glide

class ExerciseDetailActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    private lateinit var imageView: ImageView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_detail)

        val exerciseName = intent.getStringExtra("exercise_name")
        val exerciseDescription = intent.getStringExtra("exercise_description")
        val exerciseImageResId = intent.getIntExtra("exercise_image", R.drawable.glute_bridge)

        val nameTextView = findViewById<TextView>(R.id.tvExerciseName)
        val descriptionTextView = findViewById<TextView>(R.id.tvExerciseDescription)
        imageView = findViewById(R.id.exerciseImage)

        Glide.with(this)
            .asGif()
            .load(exerciseImageResId)
            .into(imageView)

        nameTextView.text = exerciseName
        descriptionTextView.text = exerciseDescription

        gestureDetector = GestureDetectorCompat(this, SwipeGestureListener())
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        imageView.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            scaleGestureDetector.onTouchEvent(event)

            // Проверка, что действие завершилось, для сброса масштаба
            if (event.action == MotionEvent.ACTION_UP) {
                resetScale()
            }
            true
        }

        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun resetScale() {
        // Сброс масштабирования до исходного размера
        scaleFactor = 1.0f
        imageView.scaleX = scaleFactor
        imageView.scaleY = scaleFactor
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { gestureDetector.onTouchEvent(it) }
        return super.onTouchEvent(event)
    }

    inner class SwipeGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100
        val nativeLib = NativeLib()

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            e1?.let {
                val diffX = e2.x - it.x
//                val diffX = nativeLib.calculateDiffX(e2.x, it.x)
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        finish()
                    }
                    return true
                }
            }
            return false
        }

        override fun onLongPress(e: MotionEvent) {
            // Начало увеличения при долгом нажатии
            scaleFactor = 3.0f
            imageView.scaleX = scaleFactor
            imageView.scaleY = scaleFactor
        }
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = scaleFactor.coerceIn(1.0f, 3.0f)

            imageView.scaleX = scaleFactor
            imageView.scaleY = scaleFactor
            return true
        }
    }
}