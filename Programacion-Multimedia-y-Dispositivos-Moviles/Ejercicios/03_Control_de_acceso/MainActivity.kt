package com.example.pmdmud1.e03

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
        val edad = findViewById<EditText>(R.id.etEdad)
        val resultado = findViewById<TextView>(R.id.tvResultado)
        val boton = findViewById<Button>(R.id.btnComprobar)

        boton.setOnClickListener {
            // TODO 1: convierte edad con toIntOrNull().
            // TODO 2: valida null y los límites 0..120.
            // TODO 3: usa && para comprobar el acceso.
            // TODO 4: distingue menor de 18, permitido y mayor de 65.
        }
    }
}
