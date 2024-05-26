package com.example.recycling.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.R

/**
 * actividad inicial donde empieza con el dashboard
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura el contenido de la vista al layout del dashboard
        setContentView(R.layout.dashboard_recycling)

        // Configurar el botón para agregar productos
        val btnAgregarProducto: ImageButton = findViewById(R.id.btnAgregarProducto)
        btnAgregarProducto.setOnClickListener {
            // Inicia la actividad para agregar productos
            startActivity(Intent(this, AgregarProductoActivity::class.java))
        }

        // Configurar el botón para buscar productos
        val btnBuscarProducto: ImageButton = findViewById(R.id.buscarbtn)
        btnBuscarProducto.setOnClickListener {
            // Inicia la actividad para buscar productos
            startActivity(Intent(this, BuscarProductoActivity::class.java))
        }
    }
}
