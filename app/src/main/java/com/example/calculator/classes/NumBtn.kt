package com.example.calculator.classes

import android.content.Context

class NumBtn(context: Context, ids: Int) : Btn(context) {

    init {
        this.id = ids
        this.text = ids.toString()
        this.type = "Number"
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
    }
}