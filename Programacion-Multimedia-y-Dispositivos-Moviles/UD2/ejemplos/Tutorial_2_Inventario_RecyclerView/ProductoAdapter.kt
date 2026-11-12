package com.example.inventariopmdm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(private val datos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(vista: View) :
        RecyclerView.ViewHolder(vista) {
        val nombre: TextView = vista.findViewById(R.id.tvProducto)
        val cantidad: TextView = vista.findViewById(R.id.tvCantidad)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ProductoViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: ProductoViewHolder, position: Int
    ) {
        val producto = datos[position]
        holder.nombre.text = producto.nombre
        holder.cantidad.text = "Unidades: ${producto.cantidad}"
    }

    override fun getItemCount(): Int = datos.size
}
