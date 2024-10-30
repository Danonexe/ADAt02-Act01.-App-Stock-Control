package Controler

import repository.ProveedorRepository

class ProveedorService {

        private val proveedorRepository = ProveedorRepository()

        fun getProveedorProducto(idProducto: String): String {
            val proveedor = proveedorRepository.getProveedorProducto(idProducto)
            return if (proveedor != null) {
                "Proveedor encontrado: ${proveedor.nombre}, Dirección: ${proveedor.direccion}"
            } else {
                "Error: No se encontró un producto con ID $idProducto o no tiene un proveedor asociado."
            }
        }

    fun getStringTodosProveedores(): String {
        val proveedores = proveedorRepository.getTodosProveedores()
        if (proveedores.isEmpty()) {
            return "No hay proveedores"
        }

        val resultado = StringBuilder("Proveedores:\n") // para ir añadir los strings
        for (proveedor in proveedores) {
            resultado.append("ID: ${proveedor.id}\n")
            resultado.append("Nombre: ${proveedor.nombre}\n")
            resultado.append("Categoría: ${proveedor.direccion}\n\n")
        }

        return resultado.toString()
    }
}