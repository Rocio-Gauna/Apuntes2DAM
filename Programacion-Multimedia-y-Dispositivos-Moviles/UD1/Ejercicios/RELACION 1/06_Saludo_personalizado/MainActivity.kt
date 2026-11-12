package com.example.pmdmud1.e06

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
        val nombre = findViewById<EditText>(R.id.etNombre)
        val ciudad = findViewById<EditText>(R.id.etCiudad)
        val boton = findViewById<Button>(R.id.btnSaludar)
        val resultado = findViewById<TextView>(R.id.tvResultado)
        boton.setOnClickListener {
            // Leo cada campo como texto y le quito los espacios de los extremos
            val nombreEscrito = nombre.text.toString().trim()
            val ciudadEscrita = ciudad.text.toString().trim()

            // Filtro: si falta el nombre o la ciudad, aviso
            if (nombreEscrito.isEmpty() || ciudadEscrita.isEmpty()) {
                resultado.text = "Completa el nombre y la ciudad"
            // TODO 4: si no falta nada, construyo el saludo con plantillas de texto
            } else {
                resultado.text = "Hola, $nombreEscrito, te damos la bienvenida desde $ciudadEscrita"
            }
        }        
        // TODO 1: crea primero etCiudad, btnSaludar y tvResultado en XML.
        // TODO 2: localiza etNombre, etCiudad, btnSaludar y tvResultado.
        // TODO 3: añade el listener; lee, recorta y valida los dos textos.
        // TODO 4: construye un mensaje con plantillas de texto.
    }
}
