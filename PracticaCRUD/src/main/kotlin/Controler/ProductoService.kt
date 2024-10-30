package service

import repository.ProductoRepository
import repository.ProveedorRepository
import java.util.Date

class ProductoService {

    private val productoRepository = ProductoRepository()
    private val proveedorRepository = ProveedorRepository()

    // Método para registrar un nuevo producto
    fun altaProducto(id:String,date: Date, categoria: String, stock: Int, nombreProducto: String, precioSinIva: Float, descripcionProducto: String, idProveedor: Long, nombreProveedor: String, direccionProveedor: String): String {
        // Busca el proveedor por ID
        var proveedor = proveedorRepository.obtenerProveedorPorId(idProveedor)

        // Si no existe el proveedor, intenta crearlo
        if (proveedor == null) {
            proveedor = proveedorRepository.crearProveedor(nombreProveedor, direccionProveedor)
            if (proveedor == null) {
                return "Error: No se pudo crear el proveedor." //para asegurarnos que no es nulo más adelante
            }
        }

        // Crea el producto asociado al proveedor existente o recién creado
        val nuevoProducto = productoRepository.crearProducto(id, categoria, nombreProducto, descripcionProducto, precioSinIva, precioSinIva * 1.21f, date, stock, proveedor.id)

        return if (nuevoProducto != null) {
            "Producto registrado exitosamente: ${nuevoProducto.nombre} con ID: ${nuevoProducto.id}"
        } else {
            "Error: No se pudo registrar el producto."
        }
    }

    //bajarProducto
    fun bajarProducto(id: String) :String {
        if (productoRepository.deleteProduct(id)==true){
            return "El producto con id: $id ha sido elimnado"
        }else{
            return "El producto con id: $id no ha podido ser elimado."
        }
    }
    // cambiar nombre
    fun actualizarNombreProducto(id: String, nuevoNombre: String): String {
        if (productoRepository.modificarNombreProducto(id, nuevoNombre)==true) {
            return "Nombre del producto actualizado correctamente"
        }else{
            return "Error: No se pudo actualizar el producto."
        }
    }
    //cambiar stock
    fun actualizarStockProducto(id: String, nuevoStock: Int): String {
        if(productoRepository.modificarStockProducto(id, nuevoStock)==true){
            return "Stock del producto actualizado correctamente"
        }else{
            return "Error: No se pudo actualizar el producto."
        }
    }
    //mostrar productos
    fun mostrarProducto(id: String): String {
        val producto = productoRepository.getProducto(id)
         if (producto != null) {
            return "Información del producto:\n" +
                    "ID: ${producto.id}\n" +
                    "Nombre: ${producto.nombre}\n" +
                    "Categoría: ${producto.categoria}\n" +
                    "Descripción: ${producto.descripcion}\n" +
                    "Precio sin IVA: ${producto.precio_sin_iva}\n" +
                    "Precio con IVA: ${producto.precio_con_iva}\n" +
                    "Stock: ${producto.stock}\n" +
                    "Fecha de alta: ${producto.fecha_alta}\nP" +
                    "roveedor: ${producto.proveedor.nombre}"
        } else {
            return "No se encontró el producto"
        }
    }
    //listas de con stock y sin stock
    fun obtenerProductosConStock(): String {
        val productos = productoRepository.getProductosConStock()
        if (productos.isEmpty()) {
            return "No hay productos con stock disponible"
        }

        val resultado = StringBuilder("Productos con stock disponible:\n") // para ir añadir los strings
        for (producto in productos) {
            resultado.append("ID: ${producto.id}\n")
            resultado.append("Nombre: ${producto.nombre}\n")
            resultado.append("Categoría: ${producto.categoria}\n")
            resultado.append("Stock: ${producto.stock}\n")
            resultado.append("Precio con IVA: ${producto.precio_con_iva}\n")
            resultado.append("Proveedor: ${producto.proveedor.nombre}\n\n")
        }

        return resultado.toString()
    }
    fun obtenerProductosSinStock(): String {
        val productos = productoRepository.getProductosSinStock()
        if (productos.isEmpty()) {
            return "No hay productos sin stock disponible"
        }

        val resultado = StringBuilder("Productos sin stock disponible:\n") // para ir añadir los strings
        for (producto in productos) {
            resultado.append("ID: ${producto.id}\n")
            resultado.append("Nombre: ${producto.nombre}\n")
            resultado.append("Categoría: ${producto.categoria}\n")
            resultado.append("Stock: ${producto.stock}\n")
            resultado.append("Precio con IVA: ${producto.precio_con_iva}\n")
            resultado.append("Proveedor: ${producto.proveedor.nombre}\n\n")
        }

        return resultado.toString()
    }
}
