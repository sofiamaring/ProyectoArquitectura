package controller

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import model.Producto
import model.ProductoDAO

class AgregarProductoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_producto)

        val editTextNombre: EditText = findViewById(R.id.editTextNombreAgregar)
        val editTextTipo: EditText = findViewById(R.id.editTextTipoAgregar)
        val btnAgregarProducto: Button = findViewById(R.id.btnAgregarProducto)

        btnAgregarProducto.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            val tipo = editTextTipo.text.toString().trim()
            if (nombre.isEmpty() || tipo.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                val producto = Producto(nombre, tipo)
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