package com.example.recycling.model
/**
* clase para la creacion de productos
 */
class Producto(
    var nombre: String = "",
    var tipo: String = "",
    var idProducto: String = "",
    var estado: String = "activo",
    var descripcion: String = ""
) {
    // Constructor secundario si es necesario
    constructor(nombre: String, tipo: String, des: String) : this(nombre, tipo, descripcion = des)

    // Los getters y setters están implícitamente disponibles
    // No necesitas definirlos explícitamente a menos que necesites lógica adicional en ellos
}
