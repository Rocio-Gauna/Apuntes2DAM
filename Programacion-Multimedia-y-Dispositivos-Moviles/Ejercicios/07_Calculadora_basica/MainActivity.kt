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
            // TODO 1: convierte los dos números y valida la entrada.
            // TODO 2: evita dividir entre cero.
            // TODO 3: calcula con when y muestra el resultado.
        }

        // TODO 4: conecta los cuatro botones con setOnClickListener.
        // Todos deben llamar a calcular, pasando el operador indicado.
    }
}
