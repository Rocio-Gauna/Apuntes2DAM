package com.example.pmdmud1.e07

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val entrada1 = findViewById<EditText>(R.id.etNumero1)
        val entrada2 = findViewById<EditText>(R.id.etNumero2)
        val resultado = findViewById<TextView>(R.id.tvResultado)

        fun calcular(operacion: String) {
            val numero1  = entrada1.text.toString().DoubleOrNull()
            val numero2  = entrada2.text.toString().DoubleOrNull()
            if( numero1 == null || numero2== null){
                resultado.text = "Introduce dos numero validos"
                return
            }
            if(operacion == "/" && numero2 == 0.0){
                resultado.text = "No se puede dividir entre cero"
                return
            }
            val valor = when (operacion){
                "+" -> numero1 + numero2
                "-" -> numero1 - numero2
                "*" -> numero1 * numero2
                "/" -> numero1 / numero2
                else -> return
            }
            resultado.text = "Resultado: $valor"
            // TODO 1: convierte los dos números y valida la entrada.
            // TODO 2: evita dividir entre cero.
            // TODO 3: calcula con when y muestra el resultado.
        }
        findViewById<Button>(R.id.btnSumar).setOnClickListener{(calcular("+"))}
        findViewById<Button>(R.id.btnRestar).setOnClickListener{(calcular("-"))}
        findViewById<Button>(R.id.btnMultiplicar).setOnClickListener{(calcular("*"))}
        findViewById<Button>(R.id.btnDividir).setOnClickListener{(calcular("/"))}
        // TODO 4: conecta los cuatro botones con setOnClickListener.
        // Todos deben llamar a calcular, pasando el operador indicado.
    }
}
