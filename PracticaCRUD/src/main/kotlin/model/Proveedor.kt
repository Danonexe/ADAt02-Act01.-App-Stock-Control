package model

import jakarta.persistence.*

@Entity
@Table(name = "proveedores")
class Proveedor(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre", nullable = false, length = 50, unique = true)
    val nombre: String,

    @Column(name = "direccion", nullable = false)
    val direccion: String,

    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL])
    val productos: List<Producto> = emptyList()
)
