package com.example.rPGPlugin.connection


import com.example.rPGPlugin.data.PlayerData
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoClientSettings.getDefaultCodecRegistry
import com.mongodb.MongoWriteException
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import com.mongodb.client.model.ReplaceOptions
import org.bson.BsonDocument
import org.bson.BsonInt64
import org.bson.codecs.configuration.CodecProvider
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider


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
    var collection: MongoCollection<PlayerData>? = null

    private constructor() {
        initConnection()
    }

    fun initConnection() {
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
            val pojoCodecProvider: CodecProvider = PojoCodecProvider.builder().automatic(true).build()
            val pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider))


            this.database = mongoClient?.getDatabase("players")?.withCodecRegistry(pojoCodecRegistry)
            this.collection = database?.getCollection("players", PlayerData::class.java)
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

    fun getPlayerData(uuid: String): PlayerData {
        try {
            return this.collection?.find(Filters.eq("_id", uuid))?.first() ?: PlayerData(uuid)
        } catch (exception: MongoWriteException) {
            return PlayerData(uuid)
        }
    }

    fun setPlayerData(data: PlayerData?) {
        // TODO: there's definitely optimizations here for only updating the parts that need to be
        // for now just throw whatever we have in the db
        try {
            val options = ReplaceOptions().upsert(true)
            this.collection?.replaceOne(Filters.eq("_id", data?.getId()), data, options)
        } catch(exception: MongoWriteException) {
            println("Something went wrong")
        }
    }

}