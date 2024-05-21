package com.example.recycling.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.model.Producto
import com.example.recycling.model.ProductoDAO
import com.example.recycling.R

//actividad para actualizar los datos del producto sis es deseado
class ModificarProductoActivity : AppCompatActivity() {
    private lateinit var productoId: String
    private lateinit var estadoProducto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editar_producto)

        val editTextNombre: EditText = findViewById(R.id.editTextNombre_editar)
        val editTextTipo: EditText = findViewById(R.id.editTextTipo_editar)
        val editTextDescripcion: EditText = findViewById(R.id.editTextDescripcion_editar)
        val btnModificarProducto: Button = findViewById(R.id.btneditar_producto)
        val switchEstado: Switch = findViewById(R.id.switch1)
        val textViewEstado: TextView = findViewById(R.id.estado)
        val btnRegresar: ImageButton = findViewById(R.id.btnregresar)


        // Recibir los datos del producto
        val nombre = intent.getStringExtra("nombre")
        val tipo = intent.getStringExtra("tipo")
        val descripcion = intent.getStringExtra("descripcion")
        val estado = intent.getStringExtra("estado")

        // Establecer los datos en los campos de entrada segun el producto
        editTextNombre.setText(nombre)
        editTextTipo.setText(tipo)
        editTextDescripcion.setText(descripcion)

        // Inicializar el estado del producto para el switch
        estadoProducto = estado ?: "activo"
        switchEstado.isChecked = estadoProducto == "activo"
        textViewEstado.text = if (estadoProducto == "activo") "Disponible" else "No Disponible"
        textViewEstado.setTextColor(if (estadoProducto == "activo") resources.getColor(R.color.green) else resources.getColor(R.color.red))




        // Se recupera el ID del producto para no crear uno nuevo.
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

        //Actualizar el evento del swicth cada vez que se oprima
        switchEstado.setOnCheckedChangeListener { _, isChecked ->
            estadoProducto = if (isChecked) "activo" else "desactivado"
            textViewEstado.text = if (estadoProducto == "activo") "Disponible" else "No Disponible"
            textViewEstado.setTextColor(if (estadoProducto == "activo") resources.getColor(R.color.green) else resources.getColor(R.color.red))
        }


        // el evento del boton para modifcar le producto en la base de datos
        btnModificarProducto.setOnClickListener {
            // crea nuevas variables con los datos que ingreso nuevamente
            val nuevoNombre = editTextNombre.text.toString().trim()
            val nuevoTipo = editTextTipo.text.toString().trim()
            val nuevaDescripcion = editTextDescripcion.text.toString().trim()
            if (nuevoNombre.isEmpty() || nuevoTipo.isEmpty() || nuevaDescripcion.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                // revisa que exista el id encontrado del producto original
                if (::productoId.isInitialized) {
                    // crea un nuevo producto con el id anterior y las nuevas caracteristcas y lo manda la modelo
                    val productoModificado = Producto(nuevoNombre, nuevoTipo, productoId, estadoProducto, nuevaDescripcion)
                    ProductoDAO.actualizarProducto(productoId, productoModificado) { success ->
                        if (success) {
                            val resultIntent = Intent().apply {
                                putExtra("nombre", nuevoNombre)
                                putExtra("tipo", nuevoTipo)
                                putExtra("descripcion", nuevaDescripcion)
                                putExtra("estado", estadoProducto)
                                putExtra("idProducto", productoId)
                            }
                            setResult(Activity.RESULT_OK, resultIntent)
                            Toast.makeText(this, "Se actualizo el producto", Toast.LENGTH_SHORT).show()

                            finish()
                        } else {
                            Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Error: ID del producto no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnRegresar.setOnClickListener(){
            finish()
        }
    }
}
