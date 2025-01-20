package org.example

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import io.github.cdimascio.dotenv.dotenv

object MongoDBConexion {

    private val dotenv = dotenv()
    private val connectString = dotenv["URL_MONGODB"]
    private const val DB_NAME = "adrianag_gestionjuegos"


    // Configuramos la uri del cluster
    private val mongoClient: MongoClient = MongoClients.create(connectString)


    val database: MongoDatabase by lazy {
        try {
            mongoClient.getDatabase(DB_NAME)
        } catch (e: Exception) {
            throw IllegalStateException("Error al conectar con la base de datos: ${e.message}", e)
        }
    }



    fun cerrarConexion() {
        try {
            mongoClient.close()
            println("Conexión cerrada correctamente.")
        } catch (e: Exception) {
            println("Error al cerrar la conexión: ${e.message}")
        }
    }
}