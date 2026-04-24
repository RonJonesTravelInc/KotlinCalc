package com.example.calculator.classes

import android.content.Context
import android.util.Log

class MathBtn(context: Context, types: String, ids: Int) : Btn(context) {

    init {
        this.id = ids
        this.type = types
        when (type) {
            "plus" -> this.text = "+"
            "minus" -> this.text = "-"
            "multiply" -> this.text = "*"
            "divide" -> this.text = "/"
            "equals" -> this.text = "="
            else -> this.text = "="
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)

        when (type) {
            "plus" -> Log.d("math", "add them")
            "minus" -> Log.d("math", "subtract them")
            "multiply" -> Log.d("math", "multiply them")
            "divide" -> Log.d("math", "divide them")
            "equals" -> Log.d("math", "equals")
            else -> Log.d("math", "equals")
        }
    }
}