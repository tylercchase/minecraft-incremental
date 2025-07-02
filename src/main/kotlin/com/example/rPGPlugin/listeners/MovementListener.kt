package com.example.rPGPlugin.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.player.PlayerJoinEvent

class MovementListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        println("TestPlugin Joined")
        println(event.player.name)
        // teleport player 5 block up
    }
    @EventHandler
    fun onPlayerMineBlock(event: BlockBreakEvent) {
        println("Test")
//        println(event.brokenItem.data.itemType.name)
//        println(event.brokenItem.type.name)
        println(event.block.type.name)
    }

    @EventHandler
    fun onPlayerBreakProgress(event: BlockDamageEvent) {
//        event.isCancelled = true
        event.isCancelled = true
        println(event.itemInHand.type)
    }
}