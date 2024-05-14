package com.example.recycling.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.model.Producto
import com.example.recycling.model.ProductoDAO
import com.example.recycling.R

class ModificarProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editar_producto)

        val editTextNombre: EditText = findViewById(R.id.editTextNombre_editar)
        val editTextTipo: EditText = findViewById(R.id.editTextTipo_editar)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion_editar)
        val btnModificarProducto: Button = findViewById(R.id.btneditar_producto)

        btnModificarProducto.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            val tipo = editTextTipo.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString().trim()
            if (nombre.isEmpty() || tipo.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                ProductoDAO.consultarProductoPorNombre(nombre) { producto ->
                    if (producto != null) {
                        val productoModificado = Producto(nombre, tipo, descripcion)
                        ProductoDAO.actualizarProducto(producto.idProducto, productoModificado) { success ->
                            if (success) {
                                Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show()
                                finish()  // Cierra la actividad y vuelve al dashboard o a la actividad anterior
                            } else {
                                Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}