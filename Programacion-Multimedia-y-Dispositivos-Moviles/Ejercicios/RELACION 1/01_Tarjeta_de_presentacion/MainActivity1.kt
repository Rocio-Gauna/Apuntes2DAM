package com.example.ejercicio1

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
        val tarjeta = findViewById<TextView>(R.id.tvTarjeta)
        val boton = findViewById<Button>(R.id.btnPresentar)
        val nombre: String = "Rocio"
        val edad = 31
        var intentos = 0

        // TODO 1: declara nombre (String), edad (Int) e intentos (var Int).
        // TODO 2: en el clic aumenta intentos, escribe la tarjeta y usa Log.d.
        boton.setOnClickListener {
            // Escribe aquí tu solución.
            intentos++
            tarjeta.text = "Mi nombre es $nombre, mi edad es $edad años, Numero intentos: $intentos"
        }
    }
}