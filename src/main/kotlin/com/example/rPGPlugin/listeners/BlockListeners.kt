package com.example.rPGPlugin.listeners

import io.papermc.paper.datacomponent.DataComponentTypes
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

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
    fun onPlayerInteract(event: PlayerInteractEvent) {
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        event.isCancelled = true // disable everything for now
    }

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onBlockInventoryMove(event: InventoryClickEvent) {
        if (event.whoClicked is Player) {
            val itemData = event.currentItem?.getData(DataComponentTypes.ITEM_NAME)
            if (itemData != null) {
                val key = NamespacedKey("rpgplugin", "action")
                if (event.currentItem?.persistentDataContainer?.has(key, PersistentDataType.STRING) ?: false) {
                    val value = event.currentItem?.persistentDataContainer?.get(key, PersistentDataType.STRING)
                    if (value == "MENU") {
                        event.isCancelled = true
                        return
                    }
                }
            }
            return
        }
    }

}