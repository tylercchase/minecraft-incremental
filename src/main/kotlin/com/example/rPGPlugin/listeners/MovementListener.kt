package com.example.rPGPlugin.listeners


import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockDamageEvent
import org.bukkit.event.player.PlayerInputEvent

class MovementListener : Listener {

    @EventHandler
    fun onPlayerMineBlock(event: BlockBreakEvent) {
        if(event.block.type == Material.DIRT) {
            println("Mining Dirt!")
        }
    }

    @EventHandler
    fun onPlayerCrouch(event: PlayerInputEvent) {
        if(event.input.isSneak) {
            event.player.teleport(event.player.location.add(0.0, 5.0, 0.0))
        }
    }
    @EventHandler
    fun onPlayerBreakProgress(event: BlockDamageEvent) {
//        event.isCancelled = true
        event.isCancelled = true
        println(event.itemInHand.type)
    }
}