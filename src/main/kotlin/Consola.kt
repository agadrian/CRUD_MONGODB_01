package org.example

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Consola {



    fun pedirString(msg: String): String{
        print(msg)

        var str: String
        do {
            str = readln()
            if (str.isBlank()){
                print("Debes introducir algun valor: ")
            }
        }while (str.isBlank())
        return str
    }

    fun pedirInt(msg: String): Int{
        print(msg)
        var int: Int?

        do {
            int = readln().toIntOrNull()
            if (int == null || int <= 0){
                print("Debes introducir un entero positivo: ")
            }
        }while (int == null || int <= 0)
        return int
    }

    fun pedirDouble(msg: String): Double{
        print(msg)
        var double: Double?

        do {
            double = readln().toDoubleOrNull()
            if (double == null){
                print("Debes introducir un valor numerico positivo: ")
            }
        }while (double == null ||double <= 0.0f)
        return double
    }

    /**
     * Fecha con formato dd/mm/aaaa
     */
    fun pedirFecha(msg: String): String{
        print(msg)

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var fechaValida: LocalDate? = null
        var fechaIntroducida: String

        do {
            fechaIntroducida = readln()

            // Si en blanco, fecha actual formateada
            if (fechaIntroducida.isBlank()){
                return LocalDate.now().format(formatter)
            }

            try {
                fechaValida = LocalDate.parse(fechaIntroducida, formatter)
            }catch (e: Exception){
                print("Formato de fecha inválido, introduce una fecha válida (dd/mm/aaaa): ")
            }
        }while (fechaValida == null)

        return fechaValida.format(formatter)
    }
}