package com.example.assignment2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Location : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contactus)

        val mapIntent: Intent = Uri.parse(
            "geo:31.9951238, 35.8795549?z=18"
        ).let { location ->
            Intent(Intent.ACTION_VIEW, location)
        }
        startActivity(mapIntent);
    }
}