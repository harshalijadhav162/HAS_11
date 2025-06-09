package com.example.myapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.databinding.ActivityAdminloginBinding
import com.example.myapplication.databinding.ActivityAdminregisterBinding
import com.google.firebase.auth.FirebaseAuth

class Adminlogin : AppCompatActivity() {

    private lateinit var binding: ActivityAdminloginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminloginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val edemail = binding.edemail
        val edpassword = binding.edpassword
        val btnforgot = binding.btnforgot
        val btnlogin = binding.btnlogin
        val btnregister = binding.btnregister
        btnregister.setOnClickListener {
            val intent = Intent(applicationContext,Adminregister::class.java)
            startActivity(intent)

        }

        auth = FirebaseAuth.getInstance()

        //login
        btnlogin.setOnClickListener {

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(edemail.text.toString()).matches()) {
                edemail.setError("Enter Email Id")
                return@setOnClickListener
            }
            else if (edpassword.text.isEmpty()){
                edpassword.setError("Enter Password")
                return@setOnClickListener
            }



            auth.signInWithEmailAndPassword(edemail.text.toString(),edpassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"successfully Login", Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, Admindash::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Failed to login", Toast.LENGTH_LONG).show()
                    }
                }
        }

        //forgot password
        btnforgot.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dailog_forgot,null)
            val username = view.findViewById<EditText>(R.id.ed_forgot)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotpassword(username)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()

        }
    }

    private fun forgotpassword(username: EditText?) {

        auth.sendPasswordResetEmail(username!!.text.toString())
            .addOnCompleteListener {
                if(it.isSuccessful)
                {
                    Toast.makeText(applicationContext,"Email Sent", Toast.LENGTH_LONG).show()
                }
            }



    }
}