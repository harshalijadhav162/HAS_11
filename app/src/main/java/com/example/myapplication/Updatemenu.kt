package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Updatemenu : AppCompatActivity() {


    private var recyclerView: RecyclerView? = null
    private val db = FirebaseDatabase.getInstance()
    private val root = db.reference.child("data")

    private var adapter: UpdateAdapter? = null
    private var list: ArrayList<Menu?>? = null
    var ref: DatabaseReference? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_updatemenu)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))


        val options: FirebaseRecyclerOptions<Menu> = FirebaseRecyclerOptions.Builder<Menu>()
            .setQuery(FirebaseDatabase.getInstance().getReference().child("menu"), Menu::class.java)
            .build()

        adapter = UpdateAdapter(options,this)
        recyclerView.setAdapter(adapter)


    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }
    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }
}