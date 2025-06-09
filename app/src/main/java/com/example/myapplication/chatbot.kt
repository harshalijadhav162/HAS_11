package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

class chatbot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chatbot)

        val eTPrompt= findViewById<EditText>(R.id.eTPrompt)
        val btnSubmit= findViewById<Button>(R.id.btnSubmit)
        val tVResult= findViewById<TextView>(R.id.tVResult)

        btnSubmit.setOnClickListener {
            val prompt= eTPrompt.text.toString()

            val generativeModel = GenerativeModel(
                // For text-only input, use the gemini-pro model
                modelName = "gemini-1.5-flash",
                apiKey = "AIzaSyB0ZRg2g79v4RDHvSb9JewJE8XXfvMGwzk"
                // ENTER YOUR KEY
            )
            runBlocking {
                val response = generativeModel.generateContent(prompt)
                tVResult.text= response.text
            }
        }


    }
}