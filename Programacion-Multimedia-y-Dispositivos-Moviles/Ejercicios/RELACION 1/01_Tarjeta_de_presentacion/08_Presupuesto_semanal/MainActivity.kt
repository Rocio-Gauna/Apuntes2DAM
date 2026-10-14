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
    fun saldo (ingresos:Double, comida: Double, transporte: Double): Double {
                return ingresos - comida - transporte
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ingresos = findViewById<EditText>(R.id.etIngresos)
        val comida = findViewById<EditText>(R.id.etComida)
        val transporte = findViewById<EditText>(R.id.etTransporte)
        val resultado = findViewById<TextView>(R.id.tvResultado)
        val calcular = findViewById<Button>(R.id.btnCalcular)
        val botonLimpiar = findViewById<Button>(R.id.btLimpiar)


        calcular.setOnClickListener {
            // TODO 2: convierte las tres entradas y comprueba los errores.
            val ingreso_valido = ingresos.text.toString().toDoubleOrNull()
            val comidas_valido = comida.text.toString().toDoubleOrNull()
            val transporte_valido = transporte.text.toString().toDoubleOrNull()

            if (ingreso_valido == null || comidas_valido == null || transporte_valido == null){
                resultado.text = "Los datos ingresados no deben estar vacios"
            }else{
            // TODO 3: llama a saldo y clasifica su valor mediante when.
                 val saldo_calculado = saldo(ingreso_valido,comidas_valido,transporte_valido)
                 val mensaje = when {
                     saldo_calculado == 0.0 -> "No sobra saldo"
                     saldo_calculado > 0 -> "Sobra saldo"
                     else -> " falta {$saldo_calculado}"
                 }
            // TODO 4: muestra el resultado.
            resultado.text = mensaje
            }
        }
        // TODO 5: crea btnLimpiar en XML y programa su listener.
        botonLimpiar.setOnClickListener{
            ingresos.text.clear()
            comida.text.clear()
            transporte.text.clear()
            resultado.text = ""
        }
    }
}