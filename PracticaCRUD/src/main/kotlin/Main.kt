import Controler.ProveedorService
import Controler.UsuarioService
import service.ProductoService
import java.text.SimpleDateFormat
import java.util.Date

fun main() {
    //menu login
    var usuarioService = UsuarioService()
    do {
        var login = false
        println("Escribe tu nombre de usuario")
        var usuario = readLine()!!
        println("Escribe tu contraseña")
        var contraseña = readLine()!!
        if (usuarioService.comprobarContraseña(usuario, contraseña) == true) {
            System.out.println("Contraseña correcta")
            login = true
        } else {
            System.out.println("Contraseña incorrecta")
            login = false
        }
    }while (login==false)
    //menu productos
    var menu=0;
    var menuProveedores=0;
    val productoService= ProductoService()
    val proveedorService= ProveedorService()
    do{
        println("Elije una opción")
        println("1. Dar de alta un producto")
        println("2. Dar de baja un producto")
        println("3. Modificar nombre de un producto")
        println("4. Modificar stock de un producto")
        println("5. Obtener producto")
        println("6. Obtener productos con stock")
        println("7. Obtener productos sin stock")
        println("8. Menú proveedores")
        println("0. Salir del menú")
        menu = readLine()!!.toInt()
        if(menu==1){
            // Lectura de datos para alta de producto
            // Esto es para que coja la fecha actual sin la hora
            val date: Date = Date()
            println("Ingrese el id del producto:")
            val id = readLine()!!
            println("Ingrese la categoría del producto:")
            val categoria = readLine()!!

            println("Ingrese el stock inicial:")
            val stock = readLine()!!.toInt()

            println("Ingrese el nombre del producto:")
            val nombreProducto = readLine()!!

            println("Ingrese el precio sin IVA:")
            val precioSinIva = readLine()!!.toFloat()

            println("Ingrese la descripción del producto:")
            val descripcionProducto = readLine()!!

            println("Ingrese el ID del proveedor:")
            val idProveedor = readLine()!!.toLong()

            println("Ingrese el nombre del proveedor:")
            val nombreProveedor = readLine()!!

            println("Ingrese la dirección del proveedor:")
            val direccionProveedor = readLine()!!

            // Llamada a la función altaProducto
            val resultado = productoService.altaProducto(id,
                date, categoria, stock, nombreProducto, precioSinIva,
                descripcionProducto, idProveedor, nombreProveedor, direccionProveedor
            )
            println(resultado)
        }
        if(menu==2){
            //dar de baja un producto
            println("Escribe el id del producto que quieres eliminar:")
            val idBorrar=readLine()!!
            println(productoService.bajarProducto(idBorrar))
        }
        if(menu==3){
            //modificarNombreProducto
            println("Escribe el id del producto al que le quieres cambiar el nombre:")
            val idCambiar=readLine()!!
            println("Escribe el nuevo nombre:")
            val nombreNuevo=readLine()!!
            println(productoService.actualizarNombreProducto(idCambiar,nombreNuevo))
        }
        if(menu==4){
            //modificarStockProducto
            println("Escribe el id del producto al que le quieres cambiar el stock:")
            val idCambiarStock=readLine()!!
            println("Escribe el nuevo stock:")
            val stockNuevo=readLine()!!.toInt()
            println(productoService.actualizarStockProducto(idCambiarStock,stockNuevo))
        }
        if(menu==5){
            //getProducto
            println("Escribe el id del producto que quieres ver:")
            val idProductoVer=readLine()!!
            println(productoService.mostrarProducto(idProductoVer))
        }
        if(menu==6){
            //getProductosConStock
            println(productoService.obtenerProductosConStock())
        }
        if(menu==7){
            //getProductosSinStock
            println(productoService.obtenerProductosSinStock())
        }
        if(menu==8){
            do {
                //metodos Proveedores
                println("Elije una opción")
                println("1. Mostrar proveedor de producto")
                println("2. Mostrar todos los proveedores")
                println("0. Salir del menú")
                menuProveedores = readLine()!!.toInt()
                if( menuProveedores==1){
                    println("Escribe el id del producto al que le quieres ver el proveedor:")
                    val idProveedorProducto=readLine()!!
                    println(proveedorService.getProveedorProducto(idProveedorProducto))
                }
                if(menuProveedores==2){
                    println(proveedorService.getStringTodosProveedores())
                }
            }while (menuProveedores!=0)
        }
    }while(menu!=0)
    println("Saliste del menu de productos")
}