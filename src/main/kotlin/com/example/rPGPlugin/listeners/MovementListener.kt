package com.example.rPGPlugin.listeners


import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDamageEvent

class MovementListener : Listener {

    @EventHandler
    fun onPlayerMineBlock(event: BlockBreakEvent) {
        if(event.block.type == Material.DIRT) {
            println("Mining Dirt!")
        }
    }

    @EventHandler
    fun onPlayerBreakProgress(event: BlockDamageEvent) {
//        event.isCancelled = true
        event.isCancelled = true
        println(event.itemInHand.type)
    }
}