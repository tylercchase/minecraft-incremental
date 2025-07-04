package com.example.rPGPlugin.listeners

import com.example.rPGPlugin.data.PlayerData
import com.example.rPGPlugin.data.PlayerHandler
import com.example.rPGPlugin.items.MenuItem
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
        val data: PlayerData = test.initPlayer(event.player.name)
        val joinCount = data.getJoinCount() + 1
        data.setJoinCount(joinCount)
        println("--------------------------------------")
        println("Joined $joinCount times!")
        println("--------------------------------------")
        println(data.getId())
        println(event.player.uniqueId)
        event.player.inventory.setItem(8, MenuItem().getItem())
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val test: PlayerHandler = PlayerHandler.getInstance()
        test.closePlayer(event.player.name)
    }
}