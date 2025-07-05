package com.example.rPGPlugin.listeners

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.ItemStack

class BlockListeners : Listener {
    @EventHandler
    fun onPlayerMineBlock(event: BlockBreakEvent) {
        event.isCancelled = true
        if (event.block.type == Material.DIRT) {
            println("Mining Dirt!")
        }
        if (event.block.type == Material.SUGAR_CANE) {
            // find all above sugarcane and set to air
            var currentBlock: Block = event.block
            var minedBlocks = 1
            val blocks = mutableListOf<Block>(currentBlock)
            while (true) {
                currentBlock = currentBlock.getRelative(BlockFace.UP)
                if (currentBlock.type != Material.SUGAR_CANE) {
                    break
                }
                blocks.add(currentBlock)
                minedBlocks += 1
            }
            for (block in blocks.reversed()) {
                // handle each sugarcane break.
                // also setup a timer to respawn sugarcane???
                println("Adding currency!")
                // not sure if this should give sugarcane or just generic points
                // maybe a shop system?
                event.player.inventory.addItem(ItemStack(Material.SUGAR_CANE))
                block.type = Material.AIR
            }
        }
    }


    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        event.isCancelled = true // disable everything for now
    }

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        event.isCancelled = true
    }

}