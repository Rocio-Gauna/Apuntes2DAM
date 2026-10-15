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
                // Leo el campo y lo convierto a decimal: número válido o null
    val temperaturaNumero = temperatura.text.toString().toDoubleOrNull()

    // Filtro: si es null, el campo estaba vacío o no era un número
    if (temperaturaNumero == null) {
        resultado.text = "Introduce una temperatura válida"
    } else {
        // F = C × 9 / 5 + 32 (se multiplica y divide antes de sumar)
        val fahrenheitResultado = temperaturaNumero * 9.0 / 5.0 + 32.0
        // Muestro dato de entrada, unidad y resultado
        resultado.text = "$temperaturaNumero ºC = $fahrenheitResultado ºF"
    }
        }
        fAC.setOnClickListener {
            // TODO 3: valida la entrada y llama a fahrenheitACelsius.
           // Leo el mismo campo y lo convierto a decimal: número válido o null
    val temperaturaNumero = temperatura.text.toString().toDoubleOrNull()

    // Filtro: si es null, el dato no es válido
    if (temperaturaNumero == null) {
        resultado.text = "Introduce una temperatura válida"
    } else {
        // C = (F - 32) × 5 / 9 (los paréntesis fuerzan a restar primero)
        val celsiusResultado = (temperaturaNumero - 32.0) * 5.0 / 9.0
        // Muestro dato de entrada, unidad y resultado
        resultado.text = "$temperaturaNumero ºF = $celsiusResultado ºC"
    }
        }
    }
}
