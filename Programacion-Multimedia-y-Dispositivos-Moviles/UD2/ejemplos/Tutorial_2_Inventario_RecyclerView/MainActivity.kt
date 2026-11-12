package com.example.inventariopmdm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val productos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val entradaNombre = findViewById<EditText>(R.id.etNombre)
        val entradaCantidad = findViewById<EditText>(R.id.etCantidad)
        val boton = findViewById<Button>(R.id.btnAgregar)
        val resumen = findViewById<TextView>(R.id.tvResumen)
        val lista = findViewById<RecyclerView>(R.id.rvProductos)

        val adaptador = ProductoAdapter(productos)
        lista.layoutManager = LinearLayoutManager(this)
        lista.adapter = adaptador

        boton.setOnClickListener {
            val nombre = entradaNombre.text.toString().trim()
            val cantidad = entradaCantidad.text.toString().toIntOrNull()
            if (nombre.isEmpty() || cantidad == null || cantidad < 1) {
                resumen.text = "Introduce un material y unidades positivas"
            } else {
                productos.add(Producto(nombre, cantidad))
                adaptador.notifyItemInserted(productos.lastIndex)
                resumen.text = resumenInventario(productos)
                entradaNombre.text.clear()
                entradaCantidad.text.clear()
            }
        }
    }

    private fun resumenInventario(datos: List<Producto>): String {
        val unidades = datos.sumOf { it.cantidad }
        return "Referencias: ${datos.size} · Unidades: $unidades"
    }
}
