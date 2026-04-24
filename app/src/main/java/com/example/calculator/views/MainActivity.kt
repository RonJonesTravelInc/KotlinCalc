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
                    val result = calculateResult(currentText)
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

    private fun calculateResult(expression: String): Double {
        if (expression.isEmpty()) return 0.0

        //Note for me: mutableListOf allows Lists to be added or subtracted to
        val numbers = mutableListOf<Double>()
        val operators = mutableListOf<Char>()
        var currentNumber = ""

        //make the string into lists of numbers and operators
        for (char in expression) {
            if (char.isDigit()) {
                currentNumber += char
            } else if (char == '+' || char == '-' || char == '*' || char == '/') {
                if (currentNumber.isNotEmpty()) {
                    numbers.add(currentNumber.toDouble())
                    currentNumber = ""
                }
                operators.add(char)
            }
        }

        if (currentNumber.isNotEmpty()) {
            numbers.add(currentNumber.toDouble())
        }

        if (numbers.isEmpty()) return 0.0


        //NEW LOGIC

        //DO Multiply and divide operations FIRST
        val postMDNumbers = mutableListOf<Double>()
        val postMDOperators = mutableListOf<Char>()

        if (numbers.isNotEmpty()) postMDNumbers.add(numbers[0])

        for (i in operators.indices) {
            val op = operators[i]
            val nextNum = numbers[i + 1]

            if (op == '*' || op == '/') {
                //start at 0
                val lastIdx = postMDNumbers.size - 1
                val prevNum = postMDNumbers[lastIdx]

                if (op == '*' ) {
                    postMDNumbers[lastIdx] = prevNum * nextNum
                } else {
                    if (nextNum == 0.0) return 0.0 // Handle Division by Zero
                    postMDNumbers[lastIdx] = prevNum / nextNum
                }
            } else {
                // If it's + or -, just save it for the second pass
                postMDOperators.add(op)
                postMDNumbers.add(nextNum)
            }
        }

        // 3. Second Pass: Handle Addition and Subtraction
        var result = postMDNumbers[0]
        for (i in postMDOperators.indices) {
            val nextVal = postMDNumbers[i + 1]
            when (postMDOperators[i]) {
                '+' -> result += nextVal
                '-' -> result -= nextVal
            }
        }

        return result
    }
}
