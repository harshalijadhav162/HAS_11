package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class Showfees : AppCompatActivity() {

    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null

    private var adapter:FeesAdapter?=null
    private var list: ArrayList<Hostelfees>? = null

    private  var mylist :ArrayList<String>?=null



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_showfees)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        list = ArrayList()


        val pref = getSharedPreferences("user", MODE_PRIVATE)
        val name = pref.getString("name", "")
        Toast.makeText(applicationContext,name.toString(), Toast.LENGTH_LONG).show()
        database = FirebaseDatabase.getInstance()


        val mDatabaseRef = FirebaseDatabase.getInstance().getReference("hostelfee")

        val query: Query = mDatabaseRef.orderByChild("name").equalTo(name)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                print(dataSnapshot)


                for (data in dataSnapshot.children) {
                    println(data)


                    val models: Hostelfees? = data.getValue(Hostelfees::class.java)
                    println(models)
                    if (models != null) {
                        list!!.add(models)
                    }
                }

                adapter = FeesAdapter(list,applicationContext)
                recyclerView.adapter = adapter

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}