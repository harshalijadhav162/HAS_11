package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class StudentDetails : AppCompatActivity() {

    val MY_PREFS_NAME = "MyPrefsFile"
    var name:String?=null
    var material:String?=null
    var manifacute:String?=null
    var origin:String?=null
    var weight:String?=null
    var rating:String?=null
    var demi:String?=null
    var batch:String?=null

    var ref: DatabaseReference? = null
    var two:String?=null
    var four:String?=null
    var useremail:String?=null
    var useraddress:String?=null
    var url:String?=null
    var number:String?=null
    var email:String?=null
    var no:Int?=null
    var unumber:String?=null
    var date:String?=null
    var time:String?=null




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)

        date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime())

         time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        val prefs = getSharedPreferences("MY", MODE_PRIVATE)

        unumber = prefs.getString("number", "default")

        Toast.makeText(applicationContext,unumber.toString(), Toast.LENGTH_LONG).show()

        val txtproname = findViewById<TextView>(R.id.txtname)
        val txtmaterial = findViewById<TextView>(R.id.txtmaterial)
        val txtnumber = findViewById<TextView>(R.id.number)
        val txtaddress = findViewById<TextView>(R.id.txtmanifacture)

        val txtarea = findViewById<TextView>(R.id.txtorigin)

        val txtbatch = findViewById<TextView>(R.id.txtbacth)

        val image = findViewById<ImageView>(R.id.image1)

        val bundle = intent.extras





        name = bundle?.getString("name")
        material = bundle?.getString("address")
        number = bundle?.getString("number")
        manifacute=bundle?.getString("rollno")
        origin = bundle?.getString("myclass")
        batch = bundle?.getString("batch")

        url = bundle?.getString("url")


        Glide.with(this@StudentDetails).load(url).into(image)


        txtproname.setText("Student Name:" +name)
        txtmaterial.setText("Address: "+material)
        txtnumber.setText("Number :"+number)
        txtaddress.setText("Roll No : "+manifacute)
        txtarea.setText("Class: "+origin)
        txtbatch.setText("Batch: "+batch)

        val absent = findViewById<Button>(R.id.btnabsent)

        absent.setOnClickListener {

            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("+91$number", null, "Today Your Child Is Absent!", null, null)
        }

        val presnet = findViewById<Button>(R.id.btnpresnt)

        presnet.setOnClickListener {



            val data = FirebaseDatabase.getInstance().getReference().child("presentstudent")
            val service = Present(name,number,manifacute,batch,date, time)


            data.push().setValue(service)

            Toast.makeText(applicationContext,"Uploaded", Toast.LENGTH_LONG).show()

        }

        val room = findViewById<Button>(R.id.btnroom)

        room.setOnClickListener {
            val intent = Intent(applicationContext, Allocateroom::class.java)
            intent.putExtra("number",number)
            startActivity(intent)
        }


        val delete = findViewById<Button>(R.id.btndelete)

        delete.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().reference.child("studentdata")
            val query: Query = ref.orderByChild("name")
                .equalTo(name)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(@NonNull dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        // Delete the matched node
                        snapshot.ref.removeValue().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Deletion successful
                                Toast.makeText(
                                    applicationContext,
                                    "Student deleted successfully",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            } else {
                                // Deletion failed
                                Toast.makeText(
                                    applicationContext,
                                    "Failed to delete worker",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }

                override fun onCancelled(@NonNull databaseError: DatabaseError) {
                    // Handle error
                    Toast.makeText(
                        applicationContext,
                        "Database error: " + databaseError.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }


    }
}