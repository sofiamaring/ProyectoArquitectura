package com.example.recycling.controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.model.Producto
import com.example.recycling.model.ProductoDAO
import com.example.recycling.R

class AgregarProductoActivity: AppCompatActivity() { //actividad que se encarga de conectar el layout nuevo_producto con el mode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_producto)
        // variables para los edittext del layout
        val editTextNombre: EditText = findViewById(R.id.editTextNombre_registrar)
        val editTextTipo: EditText = findViewById(R.id.editTextTipo_registrar)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion_registrar)
        val btnAgregarProducto: Button = findViewById(R.id.añadir_producto)
        val btnRegresar: ImageButton = findViewById(R.id.btnRegresar)

        btnAgregarProducto.setOnClickListener { //evento del boton
            //recibe lo escrito en los edittext del layout
            val nombre = editTextNombre.text.toString().trim()
            val tipo = editTextTipo.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString().trim()
            if (nombre.isEmpty() || tipo.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                //crea el producto con los dtaos ingresados para mandarselo al modelo
                val producto = Producto(nombre, tipo, descripcion)
                ProductoDAO.crearProducto(producto) { success, error ->
                    if (success) {
                        Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                        editTextNombre.setText("")
                        editTextTipo.setText("")
                        editTextDescripcion.setText("")
                    } else {
                        Toast.makeText(this, "Error al agregar el producto: $error", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

        btnRegresar.setOnClickListener { // boton para regresar de layout(terminar actividad)
            // Simplemente finaliza esta actividad, regresando a la anterior en el stack
            finish()
        }
    }
}
