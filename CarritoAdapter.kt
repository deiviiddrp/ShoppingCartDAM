package com.example.shoppingcartdam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.compose.ui.layout.layout
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

data class Producto(val nombre: String, val precio: Double, var cantidad: Int = 0)

class CarritoAdapter(private val carrito: List<Producto>, private val onRemoveClick: (Int) -> Unit) :
    RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreText: TextView = view.findViewById(R.id.nombreText)
        val precioText: TextView = view.findViewById(R.id.precioText)
        val cantidadText: TextView = view.findViewById(R.id.cantidadText)
        val removeButton: Button = view.findViewById(R.id.removeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false) // Line 22
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = carrito[position]
        holder.nombreText.text = producto.nombre
        holder.precioText.text = String.format(Locale.getDefault(), "€%.2f", producto.precio)
        holder.cantidadText.text = "Cant: ${producto.cantidad}"
        holder.removeButton.setOnClickListener { onRemoveClick(position) }
    }

    override fun getItemCount() = carrito.size
}
