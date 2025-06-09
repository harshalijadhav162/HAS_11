package com.example.myapplication

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class studentattence : AppCompatActivity() {

    var ref: DatabaseReference? = null
    var list: ArrayList<Present>? = null
    private var listener: AttendanceAdapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null



    var searchView: SearchView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_studentattence)

        ref = FirebaseDatabase.getInstance().reference.child("presentstudent")
        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.searchbar)
    }

    override fun onStart() {
        super.onStart()

        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        list = ArrayList()
                        for (ds in snapshot.children) {

                            list!!.add(ds.getValue(Present::class.java)!!)
                        }

                        //setOnClickListner()


                        val adapter = AttendanceAdapter(list,listener)

                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@studentattence, "error", Toast.LENGTH_SHORT).show()
                }
            })
        }

        if (searchView != null) {
            searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(s: String): Boolean {
                    search(s)
                    return true
                }
            })
        }

    }

    private fun search(s: String) {

        try{
            val mylist = ArrayList<Present?>()
            for (`object` in list!!) {
                if (`object`!!.name.toLowerCase().contains(s.toLowerCase())) {
                    mylist.add(`object`)
                }
            }
            val adapter = AttendanceAdapter(mylist,listener)
            recyclerView!!.adapter = adapter
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}