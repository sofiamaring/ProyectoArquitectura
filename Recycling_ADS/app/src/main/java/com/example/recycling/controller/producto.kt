package com.example.recycling.controller
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.model.Producto
import com.example.recycling.model.ProductoDAO
import android.view.View

class ControllerProducto(private val productoDao: ProductoDAO) : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTipo: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var btnAgregarProducto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // se inicializa la vista
        setContentView(R.layout.nuevo_producto)

        // Inicializar los elementos de la vista nuevo_producto
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTipo = findViewById(R.id.editTextTipo)
        editTextDescripcion = findViewById(R.id.editTextDescripcion)
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto)

        // Agregar evento clic al botón "Agregar Producto"
        btnAgregarProducto.setOnClickListener {
            agregarProducto()
        }
    }
    //funcion de agregar producto cuando se haga click en el boton
    // donde se accede a la informacion ingresada en los edittext de las vistas
    private fun agregarProducto() {
        val nombre = editTextNombre.text.toString().trim()
        val tipo = editTextTipo.text.toString().trim()
        val descripcion = editTextDescripcion.text.toString().trim()

        if (nombre.isEmpty() || tipo.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoProducto = Producto(nombre, tipo, descripcion)

        productoDao.crearProducto(nuevoProducto) { exito ->
            if (exito) {
                Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                limpiarCampos()
            } else {
                Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun limpiarCampos() {
        editTextNombre.text.clear()
        editTextTipo.text.clear()
        editTextDescripcion.text.clear()
    }










    //falta inicializar las otras vistas para acceder a us slementos y botones y asignarles las funciones correspondientes
    private fun consultarProducto() {
        val nombreProducto = editTextNombre3.text.toString().trim()
        if (nombreProducto.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese el nombre a consultar", Toast.LENGTH_SHORT).show()
            return
        }
        productoDao.consultarProductoPorNombre(nombreProducto){ producto ->
            if (producto != null) {

                mostrarLayoutInfoProducto(producto)
            } else {
                mostrarAlarmaProductoNoEncontrado()
            }
        }
    }

    private fun mostrarLayoutInfoProducto(producto: Producto) {

        findViewById<View>(R.id.alarma_producto_no_encontrado).visibility = View.GONE


        findViewById<View>(R.id.info_producto).visibility = View.VISIBLE

    }

    private fun mostrarAlarmaProductoNoEncontrado() {

        findViewById<View>(R.id.info_producto).visibility = View.GONE

        findViewById<View>(R.id.alarma_producto_no_encontrado).visibility = View.VISIBLE
    }













    private fun modificarProducto() {
        val nombre = editTextNombre2.text.toString().trim()
        val tipo = editTextTipo2.text.toString().trim()
        val descripcion = editTextDescripcion2.text.toString().trim()

        if (nombre.isEmpty() || tipo.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        productoDao.consultarProductoPorNombre(nombre) { producto ->
            if (producto != null) {
                val nuevoProducto = Producto(nombre, tipo, descripcion)
                productoDao.actualizarProducto(producto.idProducto, nuevoProducto) { exito ->
                    if (exito) {
                        Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "No se encontró el producto a modificar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}