package com.example.pmdmud1.e04

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
        val nota = findViewById<EditText>(R.id.etNota)
        val resultado = findViewById<TextView>(R.id.tvResultado)
        val boton = findViewById<Button>(R.id.btnEvaluar)

        boton.setOnClickListener {
            // TODO 1: convierte y valida nota (Int entre 0 y 10).
            // TODO 2: si es válida usa when para asignar la calificación.
            // TODO 3: muestra el resultado o el error.
        }
    }
}
