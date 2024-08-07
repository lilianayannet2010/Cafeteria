import java.util.InputMismatchException
import java.util.Scanner

data class Product(var name: String, var price: Double)

fun main() {
    val scanner = Scanner(System.`in`)
    val menu = mutableListOf<Product>()
    var running = true

    while (running) {
        println("Cafetería Menu")
        println("1. Agregar producto")
        println("2. Mostrar menú")
        println("3. Eliminar producto")
        println("4. Actualizar producto")
        println("5. Salir")
        print("Seleccione una opción: ")

        when (scanner.nextInt()) {
            1 -> addProduct(scanner, menu)
            2 -> showMenu(menu)
            3 -> removeProduct(scanner, menu)
            4 -> updateProduct(scanner, menu)
            5 -> running = false
            else -> println("Opción inválida. Por favor intente nuevamente.")
        }
    }
}

fun addProduct(scanner: Scanner, menu: MutableList<Product>) {
    print("Ingrese el nombre del producto: ")
    val name = scanner.next()

    var price: Double? = null
    while (price == null) {
        print("Ingrese el precio del producto: ")
        try {
            price = scanner.nextDouble()
        } catch (e: InputMismatchException) {
            println("Entrada inválida. Por favor, ingrese un número válido para el precio.")
            scanner.next() // Limpiar el buffer del scanner
        }
    }

    menu.add(Product(name, price))
    println("Producto agregado exitosamente.")
}

fun showMenu(menu: List<Product>) {
    if (menu.isEmpty()) {
        println("El menú está vacío.")
    } else {
        println("Menú de la cafetería:")
        for ((index, product) in menu.withIndex()) {
            println("${index + 1}. ${product.name} - \$${product.price}")
        }
    }
}

fun removeProduct(scanner: Scanner, menu: MutableList<Product>) {
    if (menu.isEmpty()) {
        println("El menú está vacío.")
        return
    }
    showMenu(menu)
    print("Seleccione el número del producto que desea eliminar: ")
    val index = scanner.nextInt() - 1
    if (index in menu.indices) {
        menu.removeAt(index)
        println("Producto eliminado exitosamente.")
    } else {
        println("Número de producto inválido.")
    }
}

fun updateProduct(scanner: Scanner, menu: MutableList<Product>) {
    if (menu.isEmpty()) {
        println("El menú está vacío.")
        return
    }
    showMenu(menu)
    print("Seleccione el número del producto que desea actualizar: ")
    val index = scanner.nextInt() - 1
    if (index in menu.indices) {
        print("Ingrese el nuevo nombre del producto: ")
        val newName = scanner.next()

        var newPrice: Double? = null
        while (newPrice == null) {
            print("Ingrese el nuevo precio del producto: ")
            try {
                newPrice = scanner.nextDouble()
            } catch (e: InputMismatchException) {
                println("Entrada inválida. Por favor, ingrese un número válido para el precio.")
                scanner.next() // Limpiar el buffer del scanner
            }
        }

        menu[index].name = newName
        menu[index].price = newPrice
        println("Producto actualizado exitosamente.")
    } else {
        println("Número de producto inválido.")
    }
}
