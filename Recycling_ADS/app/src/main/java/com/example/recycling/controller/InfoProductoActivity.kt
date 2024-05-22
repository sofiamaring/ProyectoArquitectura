package com.example.recycling.controller

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.R

class InfoProductoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_producto)

        val nombre = intent.getStringExtra("nombre")
        val descripcion = intent.getStringExtra("descripcion")
        val tipo = intent.getStringExtra("tipo")
        val estado = intent.getStringExtra("estado")

        val btnRegresar: ImageButton = findViewById(R.id.btnRegresar)
        val btnModificar: ImageButton = findViewById(R.id.btnModificar)

        val textViewNombre: TextView = findViewById(R.id.textViewNombre)
        val textViewDescripcion: TextView = findViewById(R.id.textViewDescripcion)

        // Asegurarse de que los valores no sean nulos
        textViewNombre.text = nombre ?: "Nombre no disponible"
        textViewDescripcion.text = descripcion ?: "Descripción no disponible"

        btnRegresar.setOnClickListener {
            finish()
        }

        btnModificar.setOnClickListener {
            val intent = Intent(this, ModificarProductoActivity::class.java).apply {
                putExtra("nombre", nombre)
                putExtra("tipo", tipo)
                putExtra("descripcion", descripcion)
                putExtra("estado", estado)
            }
            startActivity(intent)
        }
    }
}
