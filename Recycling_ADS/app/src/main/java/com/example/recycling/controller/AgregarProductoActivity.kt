package com.example.recycling.controller

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.R
import com.example.recycling.model.Producto
import com.example.recycling.model.ProductoDAO

/**
 * actividad que se encarga de conectar el layout nuevo_producto con el modelo
 */
class AgregarProductoActivity: AppCompatActivity() {
    private lateinit var selectedTipoProducto: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_producto)

        val editTextNombre: EditText = findViewById(R.id.editTextNombre_registrar)
        val spinner: Spinner = findViewById(R.id.spinner_tipo_producto)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion_registrar)
        val btnAgregarProducto: Button = findViewById(R.id.añadir_producto)
        val btnRegresar: ImageButton = findViewById(R.id.btnRegresar)
        // Configurar el Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.tipos_producto_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Obtener el valor seleccionado del Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedTipoProducto = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Otra callback de la interfaz
            }
        }
        //evento del boton
        btnAgregarProducto.setOnClickListener {
            //recibe lo escrito en los edittext del layout
            val nombre = editTextNombre.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString().trim()
            if (nombre.isEmpty() || selectedTipoProducto.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                //crea el producto con los dtaos ingresados para mandarselo al modelo
                val producto = Producto(nombre, selectedTipoProducto, descripcion)
                ProductoDAO.crearProducto(producto) { success, error ->
                    if (success) {
                        Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                        editTextNombre.setText("")
                        spinner.setSelection(0)
                        editTextDescripcion.setText("")
                    } else {
                        Toast.makeText(this, "Error al agregar el producto: $error", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
        // boton para regresar de layout(terminar actividad)
        btnRegresar.setOnClickListener {
            // Simplemente finaliza esta actividad, regresando a la anterior en el stack
            finish()
        }
    }
}
