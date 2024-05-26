package com.example.recycling

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.recycling.controller.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * actividad inicial para empezar indciaciones dle test
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testFullWorkflow() {
        fun testFullWorkflow() {
            // Paso 1: Ir a BuscarProductoActivity
            onView(withId(R.id.buscarbtn)).perform(click())

            // Paso 2: Ingresar "chocolatina" y oprimir el botón de consultar
            onView(withId(R.id.editText_consultar_producto)).perform(typeText("chocolatina"))
            onView(withId(R.id.btnBuscar)).perform(click())

            // Paso 3: Verificar que estamos en ResultadosBusquedaActivity y oprimir el botón de producto
            onView(withId(R.id.linearLayoutResultados)).check(matches(isDisplayed()))
            onView(withId(R.id.btnRegresar)).perform(click())

            // Paso 4: Oprimir el botón de modificar producto en la pantalla de información del producto
            onView(withId(R.id.btnModificar)).perform(click())

            // Paso 5: Editar el producto y oprimir el botón de actualizar
            onView(withId(R.id.editTextNombre_editar)).perform(typeText("Producto Editado"))
            onView(withId(R.id.btneditar_producto)).perform(click())

            // Paso 6: Verificar que hemos regresado al dashboard (MainActivity)
            onView(withId(R.id.menu_dashboard)).check(matches(isDisplayed()))
        }
    }
}
