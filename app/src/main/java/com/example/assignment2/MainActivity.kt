package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater as menuInflater

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            R.id.Booking -> {
                val intent = Intent(this, Booking::class.java)
                startActivity(intent)
            }
            R.id.FirstClassDetails -> {
                val first_class_dialog = FirstClassDetailsClass()
                first_class_dialog.show(supportFragmentManager, "First Class Flight Details Custom Dialog")
            }
            R.id.EconomyClassDetails -> {
                val economy_class_dialog = EconomyClassDetailsClass()
                economy_class_dialog.show(supportFragmentManager, "Economy Class Flight Details Custom Dialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}