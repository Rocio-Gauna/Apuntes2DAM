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
        
            //  leo el texto del campo y lo convierto a entero (número o null)
    val edadNumero = edad.text.toString().toIntOrNull()

    //  edad no válida si es null, menor que 0 o mayor que 120
    // El null va primero: si es null, Kotlin ya no llega a comparar con números
    if (edadNumero == null || edadNumero < 0 || edadNumero > 120) {
        resultado.text = "Edad no válida"
    } else {
        //  true solo si tiene 18 o más Y a la vez 65 o menos
        val puedeEntrar = edadNumero >= 18 && edadNumero <= 65

        //  elijo el mensaje según el caso
        if (puedeEntrar) {
            resultado.text = "Acceso permitido"
        } else if (edadNumero < 18) {
            resultado.text = "No puede entrar: menor de edad"
        } else {
            resultado.text = "No puede entrar: supera la edad máxima"
        }
    }
        }
    }
}
