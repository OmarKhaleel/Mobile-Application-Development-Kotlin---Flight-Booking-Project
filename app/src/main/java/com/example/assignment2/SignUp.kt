package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText

class SignUp : AppCompatActivity() {

    private lateinit var handler:SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        handler = SQLiteHelper(this)
        val fullNameED: EditText = findViewById(R.id.FullNameED)
        val emailED: EditText = findViewById(R.id.EmailED)
        val passwordED: EditText = findViewById(R.id.PasswordED)
        val btnSignUp2: Button = findViewById(R.id.SignUpBT2)

        btnSignUp2.setOnClickListener {
            handler.insertUserData(fullNameED.text.toString(), emailED.text.toString(), passwordED.text.toString())
            val intent5 = Intent(this, MainActivity::class.java)
            startActivity(intent5)
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