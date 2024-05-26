package com.example.recycling.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.R
/**
 * actividad que se encarga de conectar el layout resultados_busqueda con el modelo
 */
class ResultadosBusquedaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_busqueda)

        val nombres = intent.getStringArrayExtra("nombres")
        val tipos = intent.getStringArrayExtra("tipos")
        val descripciones = intent.getStringArrayExtra("descripciones")
        val estados = intent.getStringArrayExtra("estados")
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayoutResultados)
        val btnRegresar: ImageButton = findViewById(R.id.btnRegresar)

        if (nombres != null && tipos != null && descripciones != null) {
            for (i in nombres.indices) {
                val nombre = nombres[i]
                val estado = estados?.get(i)
                val tipo = tipos[i]
                val descripcion = descripciones[i]
                val button = Button(this).apply {
                    text = "$nombre: $estado"
                    setOnClickListener {
                        val intent = Intent(
                            this@ResultadosBusquedaActivity,
                            InfoProductoActivity::class.java
                        ).apply {
                            putExtra("nombre", nombre)
                            putExtra("tipo", tipo)
                            putExtra("descripcion", descripcion)
                            putExtra("estado", estado)
                        }
                        startActivity(intent)
                    }
                }
                linearLayout.addView(button)
            }
        } else {
            Toast.makeText(this, "No se encontraron productos", Toast.LENGTH_SHORT).show()
        }
        btnRegresar.setOnClickListener {
            finish()
        }
    }
}
