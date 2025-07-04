package com.example.rPGPlugin

import com.example.rPGPlugin.connection.Connection
import com.example.rPGPlugin.data.PlayerHandler
import com.example.rPGPlugin.listeners.BlockListeners
import com.example.rPGPlugin.listeners.JoinListener
import com.example.rPGPlugin.listeners.MovementListener
import org.bukkit.plugin.java.JavaPlugin


class RPGPlugin : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        logger.info("RPG Plugin enabled")
        saveResource("config.yml", /* replace */ false)
        Connection.getInstance(config.getString("root.db-uri") ?: "")
        server.pluginManager.registerEvents(MovementListener(), this)
        server.pluginManager.registerEvents(BlockListeners(), this)
        server.pluginManager.registerEvents(JoinListener(), this)
        server.getOfflinePlayer(getConfig().getString("root.test-op") ?: "").setOp(true)

        println(server.operators)
    }

    override fun onDisable() {
        // save all currently connected players to db
        PlayerHandler.getInstance().closeAllPlayers()
    }


}

