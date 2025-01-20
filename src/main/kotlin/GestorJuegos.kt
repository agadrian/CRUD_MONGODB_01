package org.example

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.bson.Document



class GestorJuegos(
    private val collection: MongoCollection<Document>,
    private val consola: Consola
) {

    private fun pedirDatos(paraUpdate: Boolean = false): Document {
        println("*** DATOS JUEGOS ***")

        // Dependiendo de ssi es para update o para crear unon nuevo, pido el titulo o no (para no duplicar funciones)
        val titulo = if (!paraUpdate) consola.pedirString("Introduce título: ") else null

        val genero = consola.pedirString("Introduce género: ")

        val precio = consola.pedirDouble("Introduce precio: ")

        val fechaLanz = consola.pedirFecha("Introduce fecha lanzamiento (DD/MM/AAAA) ó vacio para la de hoy: ")

        // Dependiendo de si es para update o no, devuelvo el titulo o no.
        return if (paraUpdate){
            Document()
                .append("genero", genero)
                .append("precio", precio)
                .append("fechaLanz", fechaLanz)
        }else{
            Document()
                .append("titulo", titulo)
                .append("genero", genero)
                .append("precio", precio)
                .append("fechaLanz", fechaLanz)
        }
    }


    fun registrarJuego(){
        val doc = pedirDatos()
        val titulo = doc.getString("titulo")

        val existe = collection.find(Document("titulo", titulo)).firstOrNull() != null

        if (existe) {
            println("ERROR - Ya existe un juego con este título: $titulo.")
        }else {
            collection.insertOne(doc)
            println("Juego $titulo registrado correctamente")
        }

    }


    fun obtenerJuegos(){
        println("\n** LISTADO DE JUEGOS **")
        collection.find().forEach { doc ->
            imprimirDatosDoc(doc)
        }
    }


    fun obtenerPorGenero(){
        val genero = consola.pedirString("Introduce género a mostrar: ")
        val filter = Filters.eq("genero", genero)
        val resultados = collection.find(filter)

        if (resultados.any()){
            println("\n** JUEGOS DEL GÉNERO $genero **")
            resultados.forEach { doc ->
                imprimirDatosDoc(doc)
            }
        }else{
            println("No hay juegos del género $genero")
        }
    }


    fun modificarJuego(){
        obtenerJuegos()

        val tituloAModificar = consola.pedirString("Introduce el título del juego que quieres modificar:")

        val filter = Filters.eq("titulo", tituloAModificar)

        val juego = collection.find(filter)

        if (juego.any()) {
            val doc = pedirDatos(paraUpdate = true)
            val nuevoDoc = Document("\$set", doc)

            collection.updateOne(filter, nuevoDoc)
            println("Juego $tituloAModificar modificado correctamente")
        }else{
            println("No existe el juego con el título $tituloAModificar")
        }

    }


    fun eliminarPorGenero(){
        val genero = consola.pedirString("Introduce género a borrar: ")
        val filter = Filters.eq("genero", genero)
        val resultados = collection.find(filter)


        if (resultados.any()){
            val juegosBorrados = collection.deleteMany(filter)
            println("Juegos del género $genero borrrados: ${juegosBorrados.deletedCount} ")
        }else{
            println("No hay ningún juego del género $genero")
        }
    }


    private fun imprimirDatosDoc(doc: Document){
        println(
            "Titulo: ${doc.getString("titulo")} - " +
            "Género: ${doc.getString("genero")} - " +
            "Precio: ${doc.getDouble("precio")} - " +
            "Fecha Lanzamiento: ${doc.getString("fechaLanz")}"
        )
    }
}