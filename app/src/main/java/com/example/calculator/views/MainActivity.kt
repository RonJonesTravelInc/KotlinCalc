package com.example.calculator.views

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.calculator.adapters.GridAdapter
import com.example.calculator.R
import com.example.calculator.classes.Btn
import com.example.calculator.classes.Calculate
import com.example.calculator.classes.MathBtn
import com.example.calculator.classes.NumBtn

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var btnList = listOf<Btn>(
            NumBtn(this, 0), NumBtn(this, 1),
            NumBtn(this, 2), NumBtn(this, 3), NumBtn(this, 4),
            NumBtn(this, 5), NumBtn(this, 6), NumBtn(this, 7), NumBtn(this, 8),
            NumBtn(this, 9), MathBtn(this, "plus", 10), MathBtn(this, "minus", 11), MathBtn(this, "divide", 12),
            MathBtn(this, "multiply", 13), MathBtn(this, "equals", 14)
        )

        setContentView(R.layout.activity_main)

        val txtView = findViewById<TextView>(R.id.textView)
        val gridView = findViewById<GridView>(R.id.grid)
        val clr = findViewById<Button>(R.id.clear)

        clr.setOnClickListener { txtView.text = "Enter" }

        gridView.adapter = GridAdapter(btnList, this) { clickedText ->
            val currentText = txtView.text.toString()
            if (clickedText == "=") {
                if (currentText != "Enter") {
                    val calc = Calculate()
                    val result = calc.calculateResult(currentText)
                    txtView.text = result.toString()
                }
            } else {
                if (txtView.text.toString() == "Enter") {
                    txtView.text = clickedText
                } else {
                    txtView.text = txtView.text.toString() + clickedText
                }
            }
        }
    }
}
