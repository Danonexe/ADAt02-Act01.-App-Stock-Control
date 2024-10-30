package model

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "productos")
class Producto (
    @Id
    @Column(name = "id")
    val id: String,

    @Column(name = "categoria", nullable = false,length = 10)
    val categoria: String,

    @Column(name = "nombre",length = 50, nullable = false)
    val nombre: String,

    @Column(name = "descripcion")
    val descripcion: String,

    @Column(name = "precio_sin_iva", nullable = false)
    val precio_sin_iva: Float,

    @Column(name = "precio_con_iva", nullable = false)
    val precio_con_iva: Float,

    @Column(name = "fecha_alta", nullable = false)
    val fecha_alta: Date,

    @Column(name = "stock", nullable = false)
    val stock: Int,

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    val proveedor: Proveedor
)
