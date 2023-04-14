package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment

class EconomyClassDetailsClass: DialogFragment(R.layout.fragment_dialog_economy_class_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dismissBT2 : Button = view.findViewById(R.id.DismissEconomy)
        dismissBT2.setOnClickListener{
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}