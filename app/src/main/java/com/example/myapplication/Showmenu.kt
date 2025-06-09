package com.example.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Showmenu : AppCompatActivity() {

    private var database: FirebaseDatabase? = null
    private var ref: DatabaseReference? = null

    private var adapter: MenuAdapte? = null
    private var list: ArrayList<Menu>? = null
    var sharedpreferences: SharedPreferences? = null
    private var listener: MenuAdapte.RecyclerViewClickListener? = null


    var address: String? = null

    var recyclerView: RecyclerView? = null

    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_showmenu)

        ref = FirebaseDatabase.getInstance().reference.child("menu")
        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.serachview)
    }

    override fun onStart() {
        super.onStart()

        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        list = ArrayList()
                        for (ds in snapshot.children) {
                            list!!.add(ds.getValue(Menu::class.java)!!)
                        }
                        //setOnClickListner()
                        val adapter = MenuAdapte(list, listener)
                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Showmenu, "error", Toast.LENGTH_SHORT).show()
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
            val mylist = ArrayList<Menu?>()
            for (`object` in list!!) {
                if (`object`!!.address.toLowerCase().contains(s.toLowerCase())) {
                    mylist.add(`object`)
                }
            }
            val adapter = MenuAdapte(mylist,listener)
            recyclerView!!.adapter = adapter
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}