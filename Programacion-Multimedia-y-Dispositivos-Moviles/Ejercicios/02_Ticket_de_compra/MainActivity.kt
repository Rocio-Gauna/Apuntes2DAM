package com.example.pmdmud1.e02

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
        val precio = findViewById<EditText>(R.id.etPrecio)
        val cantidad = findViewById<EditText>(R.id.etCantidad)
        val resultado = findViewById<TextView>(R.id.tvResultado)
        val boton = findViewById<Button>(R.id.btnTicket)

        boton.setOnClickListener {
            // TODO 1: lee los dos EditText como texto.
            // TODO 2: conviértelos de forma segura y valida sus valores.
            // TODO 3: calcula y muestra el importe.
            val textoPrecio = Precio.text.toString()
            val textoCantidad = cantidad.text.toString()
            val precioNumero  = textoPrecio.toDoubleOrNull()
            val cantidadNumero = textoCantidad.toIntOrNull()
            if (prcioNumero == null || cantidadNumero == null){
                resultado.text = "Precio negativo o cantidad no valida"
            }else{
                val total = precioNumero * cantidadNumero 
                resultado.text = "Total: $precioNumero x $ cantidadNumero = $total"
            }
        }
    }
}
