package model


public class Producto() {
    var nombre: String = ""
    var tipo: String = ""
    var idProducto: String = ""
    var estado: String = "activo"
    var descripcion: String = ""

    constructor(nombre: String, tipo: String,  des : String) : this() {
        this.nombre = nombre
        this.tipo = tipo
        this.descripcion = des
    }

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getTipo(): String {
        return tipo
    }

    fun setTipo(tipo: String) {
        this.tipo = tipo
    }

    fun getIdProducto(): String {
        return idProducto
    }

    fun setIdProducto(idProducto: String) {
        this.idProducto = idProducto
    }

    fun getEstado(): String {
        return estado
    }

    fun setEstado(estado: String) {
        this.estado = estado
    }

    fun getDescripcion(): String {
        return descripcion
    }

    fun setDescripcion(desc: String) {
        this.descripcion = desc
    }

}