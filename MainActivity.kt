package com.example.shoppingcartdam  // CAMBIA SI TU PAQUETE ES DIFERENTE

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.collections.mutableListOf  // Para carrito = mutableListOf<Producto>()
import kotlin.math.roundToInt  // Si sale error en sumOf, pero probablemente no

// Toque personal: Desarrollado por [Tu Nombre] en 2º DAM UCJC, para prácticas en Shopify 🚀
// Easter egg: Si agregas 3 items, muestra un Toast "¡Listo para checkout en Shopify!"

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarritoAdapter
    private val carrito = mutableListOf<Producto>()
    private lateinit var totalText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        totalText = findViewById(R.id.totalText)
        val fabAgregar = findViewById<FloatingActionButton>(R.id.fabAgregar)

        // Productos de ejemplo (toque e-commerce: gadgets para Shopify)
        val productosEjemplo = listOf(
            Producto("Laptop UCJC Edition", 999.99),
            Producto("Móvil Android Pro", 599.99),
            Producto("Accesorio DAM", 49.99)
        )

        adapter = CarritoAdapter(carrito) { posicion ->
            // Lógica quitar: reduce cantidad, si 0 remueve
            carrito[posicion].cantidad--
            if (carrito[posicion].cantidad <= 0) {
                carrito.removeAt(posicion)
            }
            actualizarUI()
            Toast.makeText(this, "Item removido", Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fabAgregar.setOnClickListener {
            // Agrega primer producto como demo (puedes expandir con dialog)
            if (carrito.isEmpty()) {
                carrito.add(productosEjemplo[0].copy(cantidad = 1))
            } else {
                carrito[0].cantidad++  // Simula agregar más
            }
            actualizarUI()
            if (carrito.isNotEmpty() && carrito[0].cantidad >= 3) {
                Toast.makeText(this, "¡Easter egg! Listo para checkout en Shopify 😎", Toast.LENGTH_LONG).show()
            }
        }

        actualizarUI()
    }

    private fun actualizarUI() {
        adapter.notifyDataSetChanged()
        val total = carrito.sumOf { it.precio * it.cantidad }
        totalText.text = "Total: €${String.format("%.2f", total)}"
    }
}