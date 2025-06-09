package com.example.myapplication

import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Allocateroom : AppCompatActivity() {

    var edname: EditText?=null
    var ednumber: EditText?=null
    var edin: EditText?=null
    var edout: EditText?=null
    var edtype: EditText?=null
    var number:String?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_allocateroom)

        edname = findViewById<EditText>(R.id.edusername)
        ednumber = findViewById<EditText>(R.id.ednumber)
        edin = findViewById<EditText>(R.id.edtimein)
        edout = findViewById<EditText>(R.id.edtimeout)
        edtype = findViewById(R.id.edtype)
        val bundle = intent.extras
        number = bundle?.getString("number")

        Toast.makeText(applicationContext,number,Toast.LENGTH_LONG).show()



    }
    fun UploadData(view: View?) {
        val bed = edname!!.text.toString()
        val total = ednumber!!.text.toString()
        val free = edin!!.text.toString()
        val allocated = edout!!.text.toString()
        val alloctebed = edtype!!.text.toString()

        val message = "No Of Bed In One Room"+bed+"Total Room With Floor"+total+"Free Room"+free+"Allocated Room Number"+ allocated+"Allocated Bed Number"+ alloctebed

        val smsManager: SmsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(number, null, message, null, null)
        Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()


    }
}