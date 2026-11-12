package com.example.listacompra

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val productos = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val entrada = findViewById<EditText>(R.id.etProducto)
        val boton = findViewById<Button>(R.id.btnAgregar)
        val lista = findViewById<ListView>(R.id.lvProductos)
        val estado = findViewById<TextView>(R.id.tvEstado)
        val adaptador = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, productos
        )
        lista.adapter = adaptador

        fun refrescar() {
            adaptador.notifyDataSetChanged()
            estado.text = "Productos: ${productos.size}"
        }

        boton.setOnClickListener {
            val producto = limpiarNombre(entrada.text.toString())
            if (producto == null) {
                estado.text = "Escribe un producto válido"
            } else {
                productos.add(producto)
                entrada.text.clear()
                refrescar()
            }
        }
        lista.setOnItemClickListener { _, _, posicion, _ ->
            productos.removeAt(posicion)
            refrescar()
        }
        refrescar()
    }

    private fun limpiarNombre(texto: String): String? {
        val limpio = texto.trim()
        return if (limpio.isEmpty()) null else limpio
    }
}
