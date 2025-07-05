package com.example.rPGPlugin.inventories

import com.example.rPGPlugin.RPGPlugin
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

class MainMenuInventory : InventoryHolder {
    private val inventory: Inventory

    constructor(plugin: RPGPlugin) {
        this.inventory = plugin.server.createInventory(this, 9)
    }

    override fun getInventory(): Inventory {
        return this.inventory
    }
}