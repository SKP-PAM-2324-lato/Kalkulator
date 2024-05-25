package com.example.kalkuklator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private val input = StringBuilder()
    private var operand1 = Double.NaN
    private var operand2 = 0.0
    private var currentOperator = ' '

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editText= findViewById(R.id.result)
        setNumericOnClickListener()
        setOperatorOnClickListener()
    }

    private fun setNumericOnClickListener(){
        val listener = View.OnClickListener { v ->
            val button = v as Button
            input.append(button.text)
            editText.setText(input.toString())
        }

        findViewById<Button>(R.id.button0).setOnClickListener(listener)
        findViewById<Button>(R.id.button1).setOnClickListener(listener)
        findViewById<Button>(R.id.button2).setOnClickListener(listener)
        findViewById<Button>(R.id.button3).setOnClickListener(listener)
        findViewById<Button>(R.id.button4).setOnClickListener(listener)
        findViewById<Button>(R.id.button5).setOnClickListener(listener)
        findViewById<Button>(R.id.button6).setOnClickListener(listener)
        findViewById<Button>(R.id.button7).setOnClickListener(listener)
        findViewById<Button>(R.id.button8).setOnClickListener(listener)
        findViewById<Button>(R.id.button9).setOnClickListener(listener)


        findViewById<Button>(R.id.buttonDot).setOnClickListener{
            if (input.indexOf('.') == -1){
                input.append(".")
                editText.setText(input.toString())
            }
        }


    }

    private fun setOperatorOnClickListener(){

        val listener = View.OnClickListener { v ->
            if (!operand1.isNaN()) {
                operand2 = input.toString().toDouble()
                calculate()
            } else {
                operand1 = input.toString().toDouble()
            }

            val button = v as Button
            currentOperator = button.text[0]
            input.setLength(0)
        }


        findViewById<Button>(R.id.buttonAdd).setOnClickListener(listener)
        findViewById<Button>(R.id.buttonSubtract).setOnClickListener(listener)
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener(listener)
        findViewById<Button>(R.id.buttonDivide).setOnClickListener(listener)

        findViewById<Button>(R.id.buttonEquals).setOnClickListener{
            operand2 = input.toString().toDouble()
            calculate()
            currentOperator = ' '
            operand1 = Double.NaN
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener{
            operand1 = Double.NaN
            operand2 = 0.0
            input.setLength(0)
            editText.setText("")
        }

    }

    private fun calculate(){
        when (currentOperator){
            '+' -> operand1 += operand2
            '-' -> operand1 -= operand2
            '*' -> operand1 *= operand2
            '/' -> {
                if(operand2 != 0.0)
                    operand1 /= operand2
                else{
                   Toast.makeText(baseContext, "Nie dziel przez 0!", Toast.LENGTH_SHORT).show()
                    return
                }

            }
        }
        editText.setText(operand1.toString())
        input.setLength(0)
        input.append(operand1)
    }
}