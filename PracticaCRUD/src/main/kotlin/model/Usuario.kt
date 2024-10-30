package model

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
class Usuario (
    @Id
        @Column(name = "nombreUsuario" )
        val nombreUsuario: String,

        @Column(name = "password", nullable = false,length = 20)
        val password: String
    )