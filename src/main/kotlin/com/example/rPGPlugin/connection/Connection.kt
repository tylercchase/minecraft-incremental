package com.example.rPGPlugin.connection


import com.mongodb.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


class Connection {
    val uri = "mongodb://root:example@localhost:27017"
    companion object {
        private lateinit var singleInstance: Connection
        public fun getInstance(): Connection {
            if (!::singleInstance.isInitialized) {
                this.singleInstance = Connection()
            }
            return this.singleInstance
        }
    }
    var mongoClient: MongoClient? = null
    var database: MongoDatabase? = null

    private constructor() {
        initConnection()
    }

    public fun initConnection() {
        println("Connecting to $uri")

        // Construct a ServerApi instance using the ServerApi.builder() method
        val serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build()
        val settings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(this.uri))
            .serverApi(serverApi)
            .build()
        try {
            this.mongoClient = MongoClients.create(settings)
            this.database = mongoClient?.getDatabase("players")
            try {
                val command = BsonDocument("ping", BsonInt64(1));
                val commandResult = database?.runCommand(command)
                println("Pinged document successfully")
                println(commandResult)
            } catch(e : Exception) {
                println("Something went wrong")
            }
        } catch (e: Exception) {
            println("Could not initialize Admin")
        }
    }
}