package com.example.recycling.controller

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.R
import com.example.recycling.model.ProductoDAO
//actividad para consultar un producto
class BuscarProductoActivity : AppCompatActivity() { 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.busqueda_productos)
        // variables para acceder a los datos del layout
        val editTextNombre: EditText = findViewById(R.id.editText_consultar_producto)
        val btnConsultarProducto: ImageButton = findViewById(R.id.btnBuscar)
        val btnRegresar: ImageButton = findViewById(R.id.btnRegresar)

        btnConsultarProducto.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre a consultar", Toast.LENGTH_SHORT).show()
            } else {
            //una vez se llama al modelo, se trasmiten los datos a la siguiente actividad
            //para que puedan utilizarse con el objrtivo de hacer una unica consulta y siempre estar tratando con el mismo dato consultado
                ProductoDAO.buscarProductosPorNombre(nombre) { productos ->
                    if (productos.isNotEmpty()) {
                        //inicializa la actividad ResultadosBusqueda y le envia los datos ingresados por el usuario
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
