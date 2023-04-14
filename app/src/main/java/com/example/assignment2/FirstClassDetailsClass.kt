package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment

class FirstClassDetailsClass: DialogFragment(R.layout.fragment_dialog_first_class_details) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dismissBT : Button = view.findViewById(R.id.DismissFirst)
        dismissBT.setOnClickListener{
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}