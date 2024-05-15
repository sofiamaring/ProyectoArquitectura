package com.example.recycling.model

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


//<!-- funciones para acceder a la base de datos-->
object ProductoDAO {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val productosRef: DatabaseReference = database.getReference("productos")

    fun crearProducto(producto: Producto, callback: (Boolean, String?) -> Unit) {
        val ref = productosRef.push()
        producto.idProducto = ref.key ?: ""

        ref.setValue(producto).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(true, null) // No hay error, pasamos null
            } else {
                callback(false, task.exception?.message) // Pasamos el mensaje de error
            }
        }
    }

    fun actualizarProducto(productoId: String, nuevoProducto: Producto, callback: (Boolean) -> Unit) {
        productosRef.child(productoId).setValue(nuevoProducto).addOnCompleteListener { task ->
            callback(task.isSuccessful)
        }
    }

    fun eliminarProducto(productoId: String, callback: (Boolean) -> Unit) {
        productosRef.child(productoId).removeValue().addOnCompleteListener { task ->
            callback(task.isSuccessful)
        }
    }

    fun consultarProductoPorNombre(nombre: String, callback: (Producto?) -> Unit) {
        productosRef.orderByChild("nombre").equalTo(nombre).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val producto = snapshot.children.firstOrNull()?.getValue(Producto::class.java)
                callback(producto)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null)
            }
        })
    }

    fun obtenerTodosLosProductos(callback: (List<Producto>) -> Unit) {
        productosRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productos = snapshot.children.mapNotNull { it.getValue(Producto::class.java) }
                callback(productos)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(emptyList())
            }
        })
    }
}