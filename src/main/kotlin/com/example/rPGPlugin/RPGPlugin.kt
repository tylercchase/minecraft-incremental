package com.example.rPGPlugin

import com.example.rPGPlugin.connection.Connection
import com.example.rPGPlugin.listeners.MovementListener
import org.bukkit.plugin.java.JavaPlugin


class RPGPlugin : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        logger.info("RPG Plugin enabled")
        server.pluginManager.registerEvents(MovementListener(), this)
        var test = Connection.getInstance()
    }

    override fun onDisable() {
    }    // Plugin shutdown logic

}

