package com.example.rPGPlugin.data

import com.example.rPGPlugin.connection.Connection


internal class PlayerHandler {
    private val players: HashMap<String, PlayerData> = hashMapOf<String, PlayerData>()
    companion object {
        private lateinit var singleInstance: PlayerHandler
        public fun getInstance(): PlayerHandler {
            if (!::singleInstance.isInitialized) {
                this.singleInstance = PlayerHandler()
            }
            return this.singleInstance
        }
    }

    private var connection: Connection = Connection.getInstance()

    private constructor()

    fun initPlayer(uuid: String): PlayerData {
        var data = this.connection.getPlayerData(uuid)
        players[uuid] = data
        return data // this should be by reference?
    }
    fun getPlayer(uuid: String): PlayerData? {
        return players.get(uuid)
    }

    fun setPlayer(uuid: String, player: PlayerData) {
        players[uuid] = player
    }
    fun closePlayer(uuid: String) {
        var data = players[uuid]
        connection.setPlayerData(data)
        players.remove(uuid)

    }
}