package com.example.rPGPlugin.listeners

import com.example.rPGPlugin.data.PlayerData
import com.example.rPGPlugin.data.PlayerHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        println("TestPlugin Joined")
        println(event.player.name)
        val test: PlayerHandler = PlayerHandler.getInstance()
        var data: PlayerData = test.initPlayer(event.player.name)
        var joinCount = data.getJoinCount() + 1
        data.setJoinCount(joinCount)
        println("--------------------------------------")
        println("Joined $joinCount times!")
        println("--------------------------------------")
        println(data.getId())
        println(event.player.uniqueId)

    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val test: PlayerHandler = PlayerHandler.getInstance()
        test.closePlayer(event.player.name)
    }
}