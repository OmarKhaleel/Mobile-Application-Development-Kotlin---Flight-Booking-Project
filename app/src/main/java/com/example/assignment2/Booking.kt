package com.example.assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class Booking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booking)

        var flag : String = ""
        val spinnerVal : Spinner = findViewById(R.id.SPClass)
        var options = arrayOf("", "Economy", "First Class")
        val btnConfirm : Button = findViewById(R.id.ConfirmBT)
        val btnHome: Button = findViewById(R.id.HomeBT)
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        spinnerVal.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options )
        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameLayout, thirdFragment)
            commit()
        }

        btnConfirm.setOnClickListener {
            if (flag == "Economy") {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.FrameLayout, firstFragment)
                    commit()
                }
            } else if (flag == "First Class") {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.FrameLayout, secondFragment)
                    commit()
                }
            } else {
                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.FrameLayout, thirdFragment)
                    commit()
                }
            }
        }

        btnHome.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }
    }
}