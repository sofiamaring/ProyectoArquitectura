package com.example.recycling.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recycling.R

class ResultadosBusquedaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados_busqueda)
        //recibe las varibales del producto que se ingreso en la consulta para mostrarlo
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
                // le brinda al boton el texto y el evento correspondiente
                val button = Button(this).apply {
                    text = "$nombre: $estado"
                    setOnClickListener {
                        val intent = Intent(
                            //Envia los datos a la siguiente actividad para mostrar los datos correspondientes
                            // en el siguiente layout
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
        //evento para regresar al layout anterior(termina la actividad)
        btnRegresar.setOnClickListener {
            finish()
        }
    }
}
