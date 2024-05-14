package com.example.recycling.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.model.Producto
import com.example.recycling.model.ProductoDAO
import com.example.recycling.R


class AgregarProductoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_producto)

        val editTextNombre: EditText = findViewById(R.id.editTextNombre_registrar)
        val editTextTipo: EditText = findViewById(R.id.editTextTipo_registrar)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion_registrar)
        val btnAgregarProducto: Button = findViewById(R.id.añadir_producto)

        btnAgregarProducto.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            val tipo = editTextTipo.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString().trim()
            if (nombre.isEmpty() || tipo.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                val producto = Producto(nombre, tipo,descripcion)
                ProductoDAO.crearProducto(producto) { success ->
                    if (success) {
                        Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                        finish()  // Cierra la actividad y vuelve al dashboard o a la actividad anterior
                    } else {
                        Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}