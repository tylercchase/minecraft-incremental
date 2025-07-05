package com.example.rPGPlugin.listeners

import com.example.rPGPlugin.RPGPlugin
import com.example.rPGPlugin.inventories.MainMenuInventory
import io.papermc.paper.datacomponent.DataComponentTypes
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class InventoryListeners : Listener {
    private val pluginReference: RPGPlugin

    constructor(pluginReference: RPGPlugin) {
        this.pluginReference = pluginReference
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR) {
            val heldItem = event.item
            if (heldItem != null) {
                if (checkAction("MENU", heldItem)) { // convert this to some system to not have magic strings here
                    println("Right clicking a menu item!")
                    val inventory = MainMenuInventory(pluginReference)
                    event.player.openInventory(inventory.getInventory())

                }
            }
        }
    }

    @EventHandler
    fun onBlockInventoryMove(event: InventoryClickEvent) {
        if (event.whoClicked is Player) {
            val item = event.currentItem
            if (item != null) {
                if (checkAction("MENU", item)) {
                    event.isCancelled = true
                }
            }
            return
        }
    }

    fun checkAction(type: String, item: ItemStack): Boolean {
        val itemData = item.getData(DataComponentTypes.ITEM_NAME)
        if (itemData != null) {
            val key = NamespacedKey("rpgplugin", "action")
            if (item.persistentDataContainer.has(key, PersistentDataType.STRING)) {
                val value = item.persistentDataContainer.get(key, PersistentDataType.STRING)
                if (value == type) {
                    return true
                }
            }
        }

        return false
    }
}