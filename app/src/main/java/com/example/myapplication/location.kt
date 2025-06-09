package com.example.myapplication

import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.telephony.SmsManager
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale

class location : AppCompatActivity() {

    var fusedLocationProviderClient: FusedLocationProviderClient? = null

    var lat: String? = null
    var log: String? = null
    var address: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_location)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getlocation()
    }

    private fun getlocation() {
        //Toast.makeText(applicationContext,"location", Toast.LENGTH_LONG).show()
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }

        fusedLocationProviderClient!!.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val geocoder = Geocoder(this@location, Locale.getDefault())
                try {
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)


                    lat = addresses?.get(0)!!.latitude.toString()
                    log = addresses[0]!!.longitude.toString()
                    address = addresses.get(0).getAddressLine(0)
//                    Toast.makeText(applicationContext,address.toString(),Toast.LENGTH_LONG).show()

                    val sb = StringBuffer()
                    sb.append("Current location latitude").append(lat)
                    sb.append(System.getProperty("line.separator"))
                    sb.append("current location longitutde").append(log)
                    sb.append(System.getProperty("line.separator"))
                    sb.append("Current Address").append(address)
                    val msg = sb.toString()



//                    Toast.makeText(applicationContext,lat.toString(),Toast.LENGTH_LONG).show()
//                    Toast.makeText(applicationContext,log.toString(),Toast.LENGTH_LONG).show()
//                    Toast.makeText(applicationContext,addresses.get(0).getAddressLine(0),Toast.LENGTH_LONG).show()

//                    val s = send(applicationContext, "madhurioza2@gmail.com", "Call Log ", msg)
//                       s.execute()

                    val prefs = getSharedPreferences("My", MODE_PRIVATE)

                    val number = prefs.getString("number", "")
                    Toast.makeText(applicationContext,number,Toast.LENGTH_LONG).show()
                    val smsManager = SmsManager.getDefault() as SmsManager
                    smsManager.sendTextMessage(number,null,address,null,null)



                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        when(keyCode)
        {
            KeyEvent.KEYCODE_VOLUME_UP ->  getlocation()

        }
        return true

    }
}