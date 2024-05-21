package com.example.recycling.controller

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.R
import com.example.recycling.model.ProductoDAO

class BuscarProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.busqueda_productos)

        val editTextNombre: EditText = findViewById(R.id.editText_consultar_producto)
        val btnConsultarProducto: ImageButton = findViewById(R.id.btnBuscar)
        val btnRegresar: ImageButton = findViewById(R.id.btnRegresar)

        btnConsultarProducto.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre a consultar", Toast.LENGTH_SHORT).show()
            } else {
                ProductoDAO.buscarProductosPorNombre(nombre) { productos ->
                    if (productos.isNotEmpty()) {
                        val intent = Intent(this, ResultadosBusquedaActivity::class.java).apply {
                            val nombres = productos.map { it.nombre }.toTypedArray()
                            val tipos = productos.map { it.tipo }.toTypedArray()
                            val descripciones = productos.map { it.descripcion }.toTypedArray()
                            val estados = productos.map { it.estado }.toTypedArray()
                            putExtra("nombres", nombres)
                            putExtra("tipos", tipos)
                            putExtra("descripciones", descripciones)
                            putExtra("estados", estados)
                        }
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "No se encontraron productos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnRegresar.setOnClickListener {
            finish()
        }
    }
}
