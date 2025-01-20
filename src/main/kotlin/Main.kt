package org.example

fun main() {
    val consola = Consola()

    try {
        val collection = MongoDBConexion.database.getCollection("juegos")
        val gestorJuegos = GestorJuegos(collection, consola)
        val menu = Menu(gestorJuegos)

        menu.menuPrincipal()
    } catch (e: Exception) {
        println("ERROR - (${e.message})")
    }
}



/**
 * Teoría. Responde, usando tus palabras, a las siguientes preguntas.
 *
 * a) ¿Qué ventajas e inconvenientes encuentras al usar una base de datos documental con MongoDB?
 *
 * Ventajas principales es que es ideal para almacenar gran catidades de datos, ya que haccerlo en bases de datos relacionales implicaría el uso de gran cantidad de recursos y no sería viable.
 * Otra ventaja que veo esta relacionada con la anterior, y es que es bastante eficiente en cuanto a operacion con grandes volumenes de datos.
 *
 * Como desventaja, se hace más complicado el relacionar o referenciar a otra tabla, ya que no existe los joins por ejemplo, se hace de otra manera que resulta más engorrosa.
 * Tampoco creo que sea la mejor opcion a la hora de necesitar crear transacciones complejas o estructuradas.
 * Por esto mismo, creo que otra desventaja sería la curva de aprendizaje para alguien que comienza a introducirse en el tema, que puede ser pronunciada.
 *
 *
 *
 * b) ¿Cuáles han sido los principales problemas que has tenido al desarrollar la aplicación?
 *
 * En este caso no he tenido muchos problemas, ya que lo que consideraba que iba a ser mi mayor problema, era que a la hora de insertar valores, no fueran duplicados de algun tipo, que luego a la hora de obtenerlos me generara confusion, pero finalmente creo que lo he solucionado de una manera óptima.
 *
 *
 *
 * c) ¿Cómo solucionarías el problema de la inconsistencia de datos en una base de datos documental? (El hecho de que en los documentos de una colección no sea obligatorio seguir un esquema único)
 *
 * Lo que haría principalmente sería implementar una capa lógica, donde se gestione toda la validación de los datos, tanto la insercción como actualización de datos y demás. Esto me aseguraría que lo que envío a la base de datos está previamente checkeado y se puede hacer de forma segura, evitando asi esta inconsistencia.
 *
 */