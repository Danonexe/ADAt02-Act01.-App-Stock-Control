package repository

import jakarta.persistence.EntityManager
import model.Producto
import model.Proveedor
import utils.HibernateUtils
import java.util.Date

class ProductoRepository {
    var ProveedorRepository = ProveedorRepository()

    private fun getEntityManager(): EntityManager {
        return HibernateUtils.getEntityManager("PracticaCURD")
    }

    //crear un nuevo producto
    fun crearProducto(id: String,categoria: String, nombre: String, descripcion: String, precioSinIva: Float,precioConIva: Float, fechaAlta: Date, stock: Int, idProveedor: Long): Producto? {
        val em = getEntityManager()
        val proveedor = ProveedorRepository.obtenerProveedorPorId(idProveedor)

        if (proveedor == null) {
            println("No se puede crear el producto porque el proveedor con ID $idProveedor no existe.")
            return null
        }

        val producto = Producto(id, categoria, nombre, descripcion, precioSinIva, precioConIva, fechaAlta,stock,proveedor)

        try {
            em.transaction.begin()
            em.persist(producto)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            return null // Retorna null en caso de error
        } finally {
            em.close()
        }
        return producto // Retorna el producto creado
    }
    //borrar productos
    fun deleteProduct(id: String) :Boolean {
        val em = getEntityManager()
        em.transaction.begin()
        val producto = em.find(Producto::class.java, id)
        if (producto != null) {
            em.remove(producto)
            em.transaction.commit()
            em.close()
            return true
        }else{
            em.transaction.commit()
            em.close()
            return false
        }

    }
    //modificar nombre
    fun modificarNombreProducto(id: String, nuevoNombre: String):Boolean {
        val em = getEntityManager()
        em.transaction.begin()

        val productoExistente = em.find(Producto::class.java, id)
        if (productoExistente != null) {
            // Creamos un nuevo producto con el nombre actualizado
            val productoActualizado = Producto(productoExistente.id, productoExistente.categoria, nuevoNombre,productoExistente.descripcion,productoExistente.precio_sin_iva,productoExistente.precio_con_iva,productoExistente.fecha_alta,productoExistente.stock, productoExistente.proveedor)
            em.merge(productoActualizado)
            em.transaction.commit()
            em.close()
            return true
        }else {
            em.transaction.commit()
            em.close()
            return false
        }
    }
    fun modificarStockProducto(id: String, nuevoStock: Int):Boolean {
        val em = getEntityManager()
        em.transaction.begin()

        val productoExistente = em.find(Producto::class.java, id)
        if (productoExistente != null) {
            val productoActualizado = Producto(productoExistente.id, productoExistente.categoria, productoExistente.nombre, productoExistente.descripcion, productoExistente.precio_sin_iva, productoExistente.precio_con_iva, productoExistente.fecha_alta, nuevoStock, productoExistente.proveedor
            )
            em.merge(productoActualizado)
            em.transaction.commit()
            em.close()
            return true
        }else {
            em.transaction.commit()
            em.close()
            return false
        }
    }
    //get producto
    fun getProducto(id: String): Producto? {
        val em = getEntityManager()
        val producto = em.find(Producto::class.java, id)
        em.close()
        return producto
    }
    //lista productos con y sin stock
    fun getProductosConStock(): List<Producto> {
        val em = getEntityManager()
        val query = em.createQuery("FROM Producto WHERE stock > 0", Producto::class.java)
        val productos = query.resultList
        em.close()
        return productos
    }
    fun getProductosSinStock(): List<Producto> {
        val em = getEntityManager()
        val query = em.createQuery("FROM Producto WHERE stock = 0", Producto::class.java)
        val productos = query.resultList
        em.close()
        return productos
    }

}
