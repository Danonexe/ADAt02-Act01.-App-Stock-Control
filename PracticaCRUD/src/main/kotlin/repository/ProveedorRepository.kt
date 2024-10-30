package repository

import jakarta.persistence.EntityManager
import model.Producto
import model.Proveedor
import utils.HibernateUtils

class ProveedorRepository {

    private fun getEntityManager(): EntityManager {
        return HibernateUtils.getEntityManager("PracticaCURD")
    }

    // Crear un proveedor
    fun crearProveedor(nombre: String, direccion: String): Proveedor? {
        val em = getEntityManager()
        val proveedor = Proveedor(id = 0, nombre = nombre, direccion = direccion, productos = emptyList())

        try {
            em.transaction.begin()
            em.persist(proveedor)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            e.printStackTrace()
            return null // Retorna null en caso de error
        } finally {
            em.close()
        }
        return proveedor // Retorna el proveedor creado
    }

    // Función para buscar un proveedor por ID del prooveedor
    fun obtenerProveedorPorId(idProveedor: Long): Proveedor? {
        val em = getEntityManager()
        val proveedor=em.find(Proveedor::class.java, idProveedor)
        if(proveedor!=null){
            em.close()
            return proveedor
        }else{
            em.close()
            return null
        }
    }

    // Función para buscar un proveedor por ID del producto
    fun getProveedorProducto(idProducto: String): Proveedor? {
        val em = getEntityManager()
            val producto:Producto = em.find(Producto::class.java, idProducto)
            if(producto!=null){
                em.close()
                return producto.proveedor
            }else{
                em.close()
                return null
            }
    }
    //lista proveedores
    fun getTodosProveedores(): List<Proveedor> {
        val em = getEntityManager()
        val proveedores = em.createQuery("FROM Proveedor", Proveedor::class.java).resultList
        em.close()
        return proveedores
    }
}
