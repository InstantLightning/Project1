/*
This class deals with the gui for the game
 */
package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryCommands {
    public void newGUI(Player player) {

        Inventory gui = Bukkit.createInventory(null, 9, "Sharks and Minnows Games");
        JoinVillager amountOfPlayers = new JoinVillager();

        ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemStack serverEnderEye = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta emptyMeta = empty.getItemMeta();
        ItemMeta serverEnderEyeMeta = serverEnderEye.getItemMeta();

        int playerAmount = amountOfPlayers.getAmountOfPlayers();
        emptyMeta.setDisplayName(" ");
        serverEnderEyeMeta.setDisplayName("Players = " + playerAmount);
        empty.setItemMeta(emptyMeta);
        serverEnderEye.setItemMeta(serverEnderEyeMeta);
        gui.setItem(0, serverEnderEye);

        for (int i = 1; i <= 8; ++i) {
            gui.setItem(i, empty);
        }

        player.openInventory(gui);
    }
}

