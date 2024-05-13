package controller



























































    // Inflar el layout de consultar_producto
    val inflater2 = LayoutInflater.from(this)
    val consultar_productoLayout = inflater2.inflate(R.layout.busqueda_productos, null)

    val editTextNombre3 = editar_productoLayout.findViewById<EditText>(R.id.editText_consultar_producto)
    val btnConsultarProducto = editar_productoLayout.findViewById<Button>(R.id.consultar_producto_lupa)

    btnConsultarProducto.setOnClickListener {
        consultarProducto()
        }
































    // Inflar el layout de editar_producto
    val inflater1 = LayoutInflater.from(this)
    val editar_productoLayout = inflater1.inflate(R.layout.editar_producto, null)

    // Acceder a los elementos del layout editar_producto
    val editTextNombre2 = editar_productoLayout.findViewById<EditText>(R.id.editTextNombre_editar)
    val editTextTipo2 = editar_productoLayout.findViewById<EditText>(R.id.editTextTipo_editar)
    val editTextDescripcion2 = editar_productoLayout.findViewById<EditText>(R.id.editTextDescripcion_editar)
    val btnEditarProducto = editar_productoLayout.findViewById<Button>(R.id.editar_producto)

    btnEditarProducto.setOnClickListener {
        modificarProducto()
        }

















