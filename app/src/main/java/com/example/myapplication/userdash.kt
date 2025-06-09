package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class userdash : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_userdash)

        val adddriver = findViewById<ImageView>(R.id.adddriver)

        adddriver.setOnClickListener {
            val intent = Intent(applicationContext, uploadstudent::class.java)
            startActivity(intent)

        }

        val showcom = findViewById<ImageView>(R.id.showcom)

        showcom.setOnClickListener {
            val intent = Intent(applicationContext, ShowAttendance::class.java)
            startActivity(intent)

        }

        val student = findViewById<ImageView>(R.id.student)

        student.setOnClickListener {
            val intent = Intent(applicationContext, Showmenu::class.java)
            startActivity(intent)

        }


        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            val intent = Intent(applicationContext, chatbot::class.java)
            startActivity(intent)

        }

        val fee = findViewById<ImageView>(R.id.fees)

        fee.setOnClickListener {
            val intent = Intent(applicationContext, Showfees::class.java)
            startActivity(intent)

        }


        val btnlock = findViewById<ImageView>(R.id.lock)

        btnlock.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }

    }
}