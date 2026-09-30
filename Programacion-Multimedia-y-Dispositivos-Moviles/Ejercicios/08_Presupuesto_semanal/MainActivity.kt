package com.example.pmdmud1.e08

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // TODO 1: declara fun saldo(ingresos: Double, comida: Double,
    //                       transporte: Double): Double y devuelve el cálculo.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ingresos = findViewById<EditText>(R.id.etIngresos)
        val comida = findViewById<EditText>(R.id.etComida)
        val transporte = findViewById<EditText>(R.id.etTransporte)
        val resultado = findViewById<TextView>(R.id.tvResultado)
        val calcular = findViewById<Button>(R.id.btnCalcular)

        calcular.setOnClickListener {
            // TODO 2: convierte las tres entradas y comprueba los errores.
            // TODO 3: llama a saldo y clasifica su valor mediante when.
            // TODO 4: muestra el resultado.
        }
        // TODO 5: crea btnLimpiar en XML y programa su listener.
    }
}
