package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Admindash : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admindash)


        val adddriver = findViewById<ImageView>(R.id.adddriver)

        adddriver.setOnClickListener {
            val intent = Intent(applicationContext, Showstudent::class.java)
            startActivity(intent)

        }

        val showcom = findViewById<ImageView>(R.id.showcom)

        showcom.setOnClickListener {
            val intent = Intent(applicationContext, Uploadmenu::class.java)
            startActivity(intent)

        }

        val student = findViewById<ImageView>(R.id.student)

        student.setOnClickListener {
            val intent = Intent(applicationContext, Updatemenu::class.java)
            startActivity(intent)

        }

        val addfees = findViewById<ImageView>(R.id.add)

        addfees.setOnClickListener {
            val intent = Intent(applicationContext, uploadfee::class.java)
            startActivity(intent)

        }


        val logout = findViewById<ImageView>(R.id.logout)

        logout.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }

        val attend = findViewById<ImageView>(R.id.attendance)

        attend.setOnClickListener {
            val intent = Intent(applicationContext, studentattence::class.java)
            startActivity(intent)

        }


    }
}