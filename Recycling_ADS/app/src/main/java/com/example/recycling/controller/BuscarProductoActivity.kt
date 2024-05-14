package com.example.recycling.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.model.ProductoDAO
import com.example.recycling.R

class BuscarProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.busqueda_productos)

        val editTextNombre: EditText = findViewById(R.id.editText_consultar_producto)
        val btnConsultarProducto: Button = findViewById(R.id.consultar_producto_lupa)

        btnConsultarProducto.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre a consultar", Toast.LENGTH_SHORT).show()
            } else {
                ProductoDAO.consultarProductoPorNombre(nombre) { producto ->
                    if (producto != null) {
                        Toast.makeText(this, "Producto encontrado: ${producto.nombre}", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}