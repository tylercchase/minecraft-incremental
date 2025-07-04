package com.example.rPGPlugin.items

import io.papermc.paper.datacomponent.DataComponentTypes
import io.papermc.paper.datacomponent.item.ItemLore
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

interface CustomItem {
    fun getItem(): ItemStack {
        val testItem: ItemStack = ItemStack.of(Material.DIRT)
        testItem.setData(
            DataComponentTypes.LORE,
            ItemLore.lore().addLine(Component.text("Test Item. Should not be here")).build()
        );

        return testItem
    }
}