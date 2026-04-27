package com.example.calculator.classes

class Calculate {
    fun calculateResult(expression: String): Double {
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