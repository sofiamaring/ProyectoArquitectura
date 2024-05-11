package model

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object ProductoDAO {
    // acceder a la base de datos
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val productosRef: DatabaseReference = database.getReference("productos")


    //funcion para crear un producto nuevo ene la base de datos
    fun crearProducto(producto: Producto, callback: (Boolean) -> Unit) {
        productosRef.push().setValue(producto)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true) // Operación exitosa
                } else {
                    callback(false) // Operación fallida
                }
            }
    }
    //funcion para actualizar un producto nuevo ene la base de datos, encontrando el producto a modificar por su id
    fun actualizarProducto(productoId: String, nuevoProducto: Producto, callback: (Boolean) -> Unit) {
        productosRef.child(productoId).setValue(nuevoProducto)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true) // Operación exitosa
                } else {
                    callback(false) // Operación fallida
                }
            }
    }
    // funcion para eliminar un producto en la base de datos
    fun eliminarProducto(productoId: String, callback: (Boolean) -> Unit) {
        productosRef.child(productoId).removeValue()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true) // Operación exitosa
                } else {
                    callback(false) // Operación fallida
                }
            }
    }

    //funcion para consultar producto por el nombre
    fun consultarProductoPorNombre(nombre: String, callback: (Producto?) -> Unit) {
        productosRef.orderByChild("nombre").equalTo(nombre).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var producto: Producto? = null
                for (productoSnapshot in snapshot.children) {
                    producto = productoSnapshot.getValue(Producto::class.java)
                    if (producto != null) {
                        break // Solo necesitamos el primer resultado
                    }
                }
                callback(producto)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null)
            }
        })
    }
    // funcion para obtener la lista de todos los productos
    fun obtenerTodosLosProductos(callback: (List<Producto>) -> Unit) {
        productosRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productos = mutableListOf<Producto>()
                for (productoSnapshot in snapshot.children) {
                    val producto = productoSnapshot.getValue(Producto::class.java)
                    producto?.let { productos.add(it) }
                }
                callback(productos)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(emptyList())
            }
        })
    }
}