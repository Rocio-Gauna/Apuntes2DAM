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

        val nombre: String = "Miguel"
        val edad: Int = 28
        var intentos: Int = 0
        boton.setOnClickListener {
            intentos++
            val mensaje = "Soy $nombre, tengo $edad. Intentos: $intentos"
            tarjeta.text = mensaje
            Log.d("PMDM", mensaje)

        }
    }
}
