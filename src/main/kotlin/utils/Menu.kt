package org.example.utils

import org.example.gestor.GestorJuegos
import org.example.conexion.MongoDBConexion


/**
 * Clase que representa el menú principal de la aplicación.
 */
class Menu(
    private val gestorJuegos: GestorJuegos
) {
    /**
     * Función interna para pedir una opción dentro de un rango específico.
     * @param min El valor mínimo permitido.
     * @param max El valor máximo permitido.
     * @return La opción seleccionada por el usuario.
     */
    private fun pedirOpcion(min: Int, max: Int): Int {
        var opcion: Int

        do {
            print("Introduce opción -> ")
            opcion = readln().toIntOrNull() ?: -1

            if (opcion == -1) {
                println("Opción no válida. ")
            } else if (opcion !in min..max) {
                println("Opción fuera de rango. ")
            }
        } while (opcion !in min..max)
        return opcion
    }


    /**
     * Función que muestra y gestiona el menú principal.
     */
    fun menuPrincipal() {

        var opc: Int
        do {
            imprimirMenuPrincipal()
            opc = pedirOpcion(1, 6)

            when (opc) {
                1 -> gestorJuegos.obtenerJuegos()
                2 -> gestorJuegos.obtenerPorGenero()
                3 -> gestorJuegos.registrarJuego()
                4 -> gestorJuegos.modificarJuego()
                5 -> gestorJuegos.eliminarPorGenero()
                6 -> {
                    println("Saliendo del programa...")
                    MongoDBConexion.cerrarConexion()
                }
            }
        } while (opc != 6)
    }


    /**
     * Función interna para imprimir las opciones del menú principal.
     */
    private fun imprimirMenuPrincipal() {
        println("\nMenu ejercicios:")
        println("1.- Mostrar todos los juegos")
        println("2.- Mostrar juego por género")
        println("3.- Añadir juego")
        println("4.- Editar juego")
        println("5.- Eliminar juegos por género")
        println("6.- Salir...")
    }


}