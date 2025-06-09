package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

class uploadfee : AppCompatActivity() {

    var edname: EditText?=null
    var ednumber: EditText?=null
    var edin: EditText?=null
    var edout: EditText?=null

    var stationname:String?=null
    var date:String?=null



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_uploadfee)

        edname = findViewById<EditText>(R.id.edusername)
        ednumber = findViewById<EditText>(R.id.ednumber)
        edin = findViewById<EditText>(R.id.edtimein)
        edout = findViewById<EditText>(R.id.edtimeout)


        val btn = findViewById<Button>(R.id.btndate)

        btn.setOnClickListener {

            showDatePickerDialog()
        }



    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            date = "$selectedDay/${selectedMonth + 1}/$selectedYear"

        }, year, month, day)

        datePickerDialog.show()
    }

    fun UploadData(view: View?) {
        val name = edname!!.text.toString()
        val number = ednumber!!.text.toString()
        val timein = edin!!.text.toString()
        val timeout = edout!!.text.toString()


        if(number.length > 10)
        {
            Toast.makeText(applicationContext,"Number Should Be 10 Digit",Toast.LENGTH_LONG).show()

        }else if(number.length == 10)
        {
            val data = FirebaseDatabase.getInstance().getReference().child("hostelfee")
            val service = Hostelfees(name,number, timein, timeout, date)


            data.push().setValue(service)

            Toast.makeText(applicationContext,"Uploaded", Toast.LENGTH_LONG).show()


        }




    }
}