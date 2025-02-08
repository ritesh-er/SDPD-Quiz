package com.sdpd.sdpdquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val questions = listOf(
        "Which operating system is commonly used in portable devices?" to
                listOf("Windows", "Linux", "Android", "macOS"),

        "What is the primary programming language for native Android app development?" to
                listOf("Swift", "Kotlin", "JavaScript", "C#"),

        "Which component is responsible for managing an app's UI in Android?" to
                listOf("Activity", "Service", "Broadcast Receiver", "Content Provider"),

        "Which API is used for handling background tasks in Android?" to
                listOf("Retrofit", "WorkManager", "Volley", "LiveData"),

        "What is the role of the AndroidManifest.xml file?" to
                listOf("Defines app permissions & activities", "Stores app images", "Manages UI layout", "Handles database storage")
    )

    private val correctAnswers = listOf("Android", "Kotlin", "Activity", "WorkManager", "Defines app permissions & activities")


    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        showQuestion()

        val submitButton: Button = findViewById(R.id.btn_submit)
        submitButton.setOnClickListener {
            val selectedOptionId = findViewById<RadioGroup>(R.id.radio_group).checkedRadioButtonId
            if (selectedOptionId != -1) {
                val selectedAnswer = findViewById<RadioButton>(selectedOptionId).text.toString()
                if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
                    score++
                }

                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    showQuestion()
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("score", score)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun showQuestion() {
        val questionText: TextView = findViewById(R.id.text_question)
        val radioGroup: RadioGroup = findViewById(R.id.radio_group)

        val (question, options) = questions[currentQuestionIndex]
        questionText.text = question
        radioGroup.removeAllViews()

        for (option in options) {
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioGroup.addView(radioButton)
        }
    }
}
