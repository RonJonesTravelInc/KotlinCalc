package com.example.calculator.classes

import android.content.Context
import android.util.Log
import android.widget.Button

open class Btn (context: Context) : Button(context) {
    var type: String = "Number"

    override fun setId(id: Int) {
        super.setId(id)
    }
}