package repository

import jakarta.persistence.EntityManager
import model.Usuario
import utils.HibernateUtils

class UsuarioRepository {

    private fun getEntityManager() : EntityManager {
        return HibernateUtils.getEntityManager("PracticaCURD")
    }

    fun getUsuario(nombreUsuario:String): Usuario? {
        var usuarioEncontrado: Usuario? = null
        val em: EntityManager = getEntityManager()
        try {
            em.transaction.begin()
            usuarioEncontrado = em.find(Usuario::class.java, nombreUsuario)
            em.transaction.commit()
        } catch (e: Exception) {
            em.transaction.rollback()
            e.printStackTrace()
        } finally {
            em.close()
        }
        return usuarioEncontrado
    }
}
