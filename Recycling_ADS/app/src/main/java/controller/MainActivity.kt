package controller

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recycling_ads.ui.theme.Recycling_ADSTheme
import com.example.recycling_ads.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_producto)

    }

    //funciones

    fun sign_in (view: View){
        setContentView(R.layout.dashboard_recycling)
    }

    fun añadir_producto (view: View){
        setContentView(R.layout.busqueda_productos)
    }

    fun editar_producto (view: View){
        setContentView(R.layout.busqueda_productos)
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Recycling_ADSTheme {
        Greeting("Android")
    }
}