package com.example.recycling.controller


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.os.Handler
import android.os.Looper
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.R
import com.example.recycling.model.Producto
import com.example.recycling.model.ProductoDAO
/**
 * actividad que se encarga de conectar el layout editar_producto con el modelo
 */

class ModificarProductoActivity : AppCompatActivity() {

    private lateinit var productoId: String
    private lateinit var estadoProducto: String
    private lateinit var selectedTipoProducto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editar_producto)

        val editTextNombre: EditText = findViewById(R.id.editTextNombre_editar)
        val spinner: Spinner = findViewById(R.id.spinner_tipo_producto)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion_editar)
        val btnModificarProducto: Button = findViewById(R.id.btneditar_producto)
        val switchEstado: Switch = findViewById(R.id.switch1)
        val textViewEstado: TextView = findViewById(R.id.estado)
        val btnRegresar: ImageButton = findViewById(R.id.btnregresar)

        // Configurar el Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.tipos_producto_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Configurar un listener para el Spinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedTipoProducto = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Otra callback de la interfaz
            }
        }

        // Recibir los datos del producto
        val nombre = intent.getStringExtra("nombre")
        val tipo = intent.getStringExtra("tipo")
        val descripcion = intent.getStringExtra("descripcion")
        val estado = intent.getStringExtra("estado")

        // Establecer los datos en los campos de entrada
        editTextNombre.setText(nombre)
        editTextDescripcion.setText(descripcion)

        // Inicializar el Spinner con el valor actual del tipo de producto
        if (tipo != null) {
            val spinnerPosition = (spinner.adapter as ArrayAdapter<String>).getPosition(tipo)
            spinner.setSelection(spinnerPosition)
        }

        // Inicializar el estado del producto
        estadoProducto = estado ?: "activo"
        switchEstado.isChecked = estadoProducto == "activo"
        textViewEstado.text = if (estadoProducto == "activo") "Disponible" else "No Disponible"
        textViewEstado.setTextColor(if (estadoProducto == "activo") resources.getColor(R.color.green) else resources.getColor(R.color.red))

        // Necesitamos recuperar el ID del producto desde la base de datos
        if (nombre != null) {
            ProductoDAO.buscarProductosPorNombre(nombre) { productos ->
                if (productos.isNotEmpty()) {
                    val producto = productos[0]
                    productoId = producto.idProducto // Guardar el ID del producto
                }
            }
        } else {
            Toast.makeText(this, "Error: Nombre del producto no encontrado", Toast.LENGTH_SHORT).show()
        }

        switchEstado.setOnCheckedChangeListener { _, isChecked ->
            estadoProducto = if (isChecked) "activo" else "desactivado"
            textViewEstado.text = if (estadoProducto == "activo") "Disponible" else "No Disponible"
            textViewEstado.setTextColor(if (estadoProducto == "activo") resources.getColor(R.color.green) else resources.getColor(R.color.red))
        }

        btnModificarProducto.setOnClickListener {
            val nuevoNombre = editTextNombre.text.toString().trim()
            val nuevaDescripcion = editTextDescripcion.text.toString().trim()
            if (nuevoNombre.isEmpty() || selectedTipoProducto.isEmpty() || nuevaDescripcion.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                if (::productoId.isInitialized) {
                    val productoModificado = Producto(nuevoNombre, selectedTipoProducto, productoId, estadoProducto, nuevaDescripcion)
                    ProductoDAO.actualizarProducto(productoId, productoModificado) { success ->
                        if (success) {
                            Toast.makeText(this, "El producto ha sido actualizado", Toast.LENGTH_SHORT).show()

                            // Usar un Handler para retrasar la redirección
                            Handler(Looper.getMainLooper()).postDelayed({
                                val searchIntent = Intent(this, BuscarProductoActivity::class.java)
                                searchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(searchIntent)
                                finish()
                            }, 1500)
                        } else {
                            Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Error: ID del producto no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnRegresar.setOnClickListener {
            finish()
        }
    }
}
