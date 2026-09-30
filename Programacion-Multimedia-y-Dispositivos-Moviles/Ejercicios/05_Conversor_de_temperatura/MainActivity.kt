package com.example.pmdmud1.e05

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // TODO 1: completa las dos funciones usando return.
    private fun celsiusAFahrenheit(celsius: Double): Double {
        return 0.0 // Sustituye por la fórmula correcta.
    }

    private fun fahrenheitACelsius(fahrenheit: Double): Double {
        return 0.0 // Sustituye por la fórmula correcta.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val temperatura = findViewById<EditText>(R.id.etTemperatura)
        val resultado = findViewById<TextView>(R.id.tvResultado)
        val cAF = findViewById<Button>(R.id.btnCtoF)
        val fAC = findViewById<Button>(R.id.btnFtoC)

        cAF.setOnClickListener {
            // TODO 2: valida la entrada y llama a celsiusAFahrenheit.
        }
        fAC.setOnClickListener {
            // TODO 3: valida la entrada y llama a fahrenheitACelsius.
        }
    }
}
