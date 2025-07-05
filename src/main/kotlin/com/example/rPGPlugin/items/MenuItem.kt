package com.example.rPGPlugin.items

import com.example.rPGPlugin.RPGPlugin
import com.example.rPGPlugin.inventories.MainMenuInventory
import io.papermc.paper.datacomponent.DataComponentTypes
import io.papermc.paper.datacomponent.item.ItemLore
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType


class MenuItem : CustomItem {
    override fun getItem(): ItemStack {

        val outputItem: ItemStack = ItemStack.of(Material.INK_SAC)
        outputItem.setData(
            DataComponentTypes.ITEM_NAME,
            Component.text("Menu").color(NamedTextColor.YELLOW)
        )
        outputItem.setData(
            DataComponentTypes.LORE,
            ItemLore.lore().addLine(Component.text("Right click to open this")).build()
        )
        val key = NamespacedKey("rpgplugin", "action")
        outputItem.editPersistentDataContainer({ pdc ->
            pdc.set(key, PersistentDataType.STRING, "MENU")
        })
        return outputItem
    }

    override fun onInteract(player: Player, plugin: RPGPlugin) {
        val myInventory = MainMenuInventory(plugin)
        player.openInventory(myInventory.getInventory())
    }
}