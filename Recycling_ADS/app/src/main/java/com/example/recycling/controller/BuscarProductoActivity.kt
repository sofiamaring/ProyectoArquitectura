package com.example.recycling.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.model.ProductoDAO
import com.example.recycling.R

class BuscarProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.busqueda_productos)
        //<!-- Se declaran los nombres de las variables de los EDitText, Button, etc de la vista para manejarlos aquí   -->
        val editTextNombre: EditText = findViewById(R.id.editText_consultar_producto)
        val btnConsultarProducto: ImageButton = findViewById(R.id.btnBuscar)
        val btnRegresar: ImageButton = findViewById(R.id.btnRegresar)
        //<!-- Se agrega evento al boton de la vista -->
        btnConsultarProducto.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre a consultar", Toast.LENGTH_SHORT).show()
            } else {
                //<!-- Se envia el nombre ingresado para consultar en el modelo-->
                ProductoDAO.consultarProductoPorNombre(nombre) { producto ->
                    if (producto != null) {
                        Toast.makeText(this, "Producto encontrado: ${producto.nombre}", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        //<!-- Evento para le boton de regresar, termina esta actividad-->
        btnRegresar.setOnClickListener {
            // Simplemente finaliza esta actividad, regresando a la anterior en el stack
            finish()
        }

    }
}