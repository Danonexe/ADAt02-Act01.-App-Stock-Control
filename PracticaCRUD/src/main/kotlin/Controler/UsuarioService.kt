package Controler

import repository.UsuarioRepository

class UsuarioService {
    fun comprobarContraseña(nombreUsuario: String, password: String): Boolean {
        var UsuarioRepository = UsuarioRepository()
        var Usuario=UsuarioRepository.getUsuario(nombreUsuario)
        if(Usuario==null){
            return false
        }else if(Usuario.password == password){
        return true
        }else{
            return false
        }
    }
}