package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class ContactUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contactus)

        val btnBooking: Button = findViewById(R.id.GoToBookingBT)

        btnBooking.setOnClickListener {
            val intent3 = Intent(this, Booking::class.java)
            startActivity(intent3)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.flight_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.Rate -> {
                val rate_dialog = RateUsClass()
                rate_dialog.show(supportFragmentManager, "Rate Us Custom Dialog")
            }
            R.id.FirstClassDetails -> {
                val first_class_dialog = FirstClassDetailsClass()
                first_class_dialog.show(supportFragmentManager, "First Class Flight Details Custom Dialog")
            }
            R.id.EconomyClassDetails -> {
                val economy_class_dialog = EconomyClassDetailsClass()
                economy_class_dialog.show(supportFragmentManager, "Economy Class Flight Details Custom Dialog")
            }
            R.id.Location -> {
                val intent = Intent(this, Location::class.java)
                startActivity(intent)
            }
            R.id.ContactUs -> {
                val intent2 = Intent(this, ContactUs::class.java)
                startActivity(intent2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}