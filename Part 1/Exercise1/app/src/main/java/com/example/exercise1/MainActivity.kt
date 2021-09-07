package com.example.exercise1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.buttonSum)

        button.setOnClickListener {
            this.sum(findViewById(R.id.editTextNumber0), findViewById(R.id.editTextNumber1), findViewById(R.id.textViewResult))
        }
    }

   private fun sum(editTextNumber0: EditText, editTextNumber1: EditText, result: TextView) {
        val number0: Float? = editTextNumber0.text.toString().toFloatOrNull()
        val number1: Float? = editTextNumber1.text.toString().toFloatOrNull()

        if(number0 != null && number1 != null) {
            result.text = (number0 + number1).toString()
        }
    }
}