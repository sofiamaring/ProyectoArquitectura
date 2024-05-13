package controller
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.LinearLayout
import android.widget.Button
import com.example.recycling_ads.R

class login_recycling : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_producto)
        val boton_crear_cuenta = findViewById<Button>(R.id.sign_in)
        boton_crear_cuenta.setOnClickListener {
            setContentView(R.layout.dashboard_recycling)
        }
    }
}

//navegavilidad del dashboard

class buscar_dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_recycling)
        val boton_crear_cuenta = findViewById<LinearLayout>(R.id.buscar_principal)
        boton_crear_cuenta.setOnClickListener {
            setContentView(R.layout.busqueda_productos)
        }
    }
}

class buscar_dashboard_arriba : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_recycling)
        val boton_crear_cuenta = findViewById<LinearLayout>(R.id.buscar_superior)
        boton_crear_cuenta.setOnClickListener {
            setContentView(R.layout.busqueda_productos)
        }
    }
}

class registrar_dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_recycling)
        val boton_crear_cuenta = findViewById<LinearLayout>(R.id.registrar)
        boton_crear_cuenta.setOnClickListener {
            setContentView(R.layout.nuevo_producto)
        }
    }
}

class menu_dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_recycling)
        val boton_crear_cuenta = findViewById<LinearLayout>(R.id.menu_dashboard)
        boton_crear_cuenta.setOnClickListener {
            setContentView(R.layout.menu_recycling)
        }
    }
}

//navegabilidad productos

class modificar_info : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_producto)
        val boton_crear_cuenta = findViewById<LinearLayout>(R.id.editar_producto_info)
        boton_crear_cuenta.setOnClickListener {
            setContentView(R.layout.editar_producto)
        }
    }
}
