package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class RateUsClass: DialogFragment(R.layout.fragment_dialog_rate_us) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cancelbt : Button = view.findViewById(R.id.DismissEconomy)
        val submitbt : Button = view.findViewById(R.id.submitBT)
        val radioGroup = view.findViewById<RadioGroup>(R.id.RatingRadioGroup)
        cancelbt.setOnClickListener{
            dismiss()
        }
        submitbt.setOnClickListener {
            val selectedOption: Int = radioGroup.checkedRadioButtonId
            val radioButton = view.findViewById<RadioButton>(selectedOption)
            Toast.makeText(context,radioButton.text,Toast.LENGTH_SHORT).show()
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}