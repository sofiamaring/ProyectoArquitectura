package controller
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import model.Producto
import model.ProductoDAO
import android.view.View

class ControllerProducto(private val productoDao: ProductoDAO) : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTipo: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var btnAgregarProducto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_producto)

        // Inicializar las vistas
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTipo = findViewById(R.id.editTextTipo)
        editTextDescripcion = findViewById(R.id.editTextDescripcion)
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto)

        // Agregar evento clic al botón "Agregar Producto"
        btnAgregarProducto.setOnClickListener {
            agregarProducto()
        }
    }

    private fun agregarProducto() {
        val nombre = editTextNombre.text.toString().trim()
        val tipo = editTextTipo.text.toString().trim() // Usar editTextTipo en lugar de editTextPrecio
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
        editTextPrecio.text.clear()
        editTextDescripcion.text.clear()
    }
    
    private fun consultarProducto() {
        val nombreProducto = editTextNombre.text.toString().trim()
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
        val nombre = editTextNombre.text.toString().trim()
        val tipo = editTextTipo.text.toString().trim()
        val descripcion = editTextDescripcion.text.toString().trim()

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
