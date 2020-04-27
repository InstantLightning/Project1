package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WandLeftClick implements Listener {
    private static final Map<UUID, Integer> COOLDOWN = new HashMap<>();
    
    @EventHandler
    public void wandLeftClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        boolean north = false;
        boolean east = false;
        boolean south = false;
        boolean west = false;
        Location loc = player.getLocation();
        Location blockLoc = loc;
        Block purpleBlock = blockLoc.getBlock();
        //Colorable purple = ((Colorable) purpleBlock.getState().getData());
        loc.setX(loc.getBlockX() + 0.5);
        loc.setZ(loc.getBlockZ() + 0.5);
       // Bukkit.broadcastMessage("wandLeftClick called");
        if (hand == null || hand.getType() == Material.AIR) {
            return;
        }

        ItemMeta handMeta = hand.getItemMeta();
        float degrees = player.getLocation().getYaw() + 180;

        if (degrees > 0 && degrees <= 45.5) {
            Bukkit.broadcastMessage(" You are clicking north");
            north = true;
        }
        if (degrees > 315.5 && degrees <= 360) {
            Bukkit.broadcastMessage(" You are clicking north");
            north = true;
        } else if (degrees > 45.5 && degrees <= 135.5) {
            Bukkit.broadcastMessage(" You are clicking east");
            east = true;
        } else if (degrees > 135.5 && degrees <= 225.5) {
            Bukkit.broadcastMessage(" You are clicking south");
            south = true;
        } else if (degrees > 225.5 && degrees <= 315.5) {
            Bukkit.broadcastMessage(" You are clicking west");
            west = true;
        }

        if (hand.getType() == Material.STICK && handMeta.getDisplayName().equals("Wand") &&
                event.getAction() == Action.LEFT_CLICK_AIR && !COOLDOWN.containsKey(player.getUniqueId())) {

            if (north) {
                for (int i = -1; i <= 1; ++i) {
                    blockLoc = blockLoc.add(i, 3, 1);
                    purpleBlock = blockLoc.getBlock();
                    if (purpleBlock.getType() == Material.AIR) {
                        purpleBlock.setType(Material.STAINED_GLASS);
                        purpleBlock.setData((byte) 10);
                        blockLoc = loc;
                    }
                    for (int j = 1; j <= 2; ++j) {
                        blockLoc = loc.add(0, j, 0);
                        purpleBlock = blockLoc.getBlock();
                        if (purpleBlock.getType() == Material.AIR) {
                            purpleBlock.setType(Material.STAINED_GLASS);
                            purpleBlock.setData((byte) 10);
                            blockLoc = loc;
                        }
                    }
                }
                for (int i = 1; i <= 2; ++i) {

                    blockLoc = blockLoc.add(-3, i, 0);
                    purpleBlock = blockLoc.getBlock();
                    if (purpleBlock.getType() == Material.AIR){
                        purpleBlock.setType(Material.STAINED_GLASS);
                        purpleBlock.setData((byte) 10);
                        blockLoc = loc;
                    }

                    blockLoc = blockLoc.add(3, i, 0);
                    purpleBlock = blockLoc.getBlock();
                    if (purpleBlock.getType() == Material.AIR){
                        purpleBlock.setType(Material.STAINED_GLASS);
                        purpleBlock.setData((byte) 10);
                        blockLoc = loc;
                    }


                    blockLoc = blockLoc.add(-2, i, 1);
                    purpleBlock = blockLoc.getBlock();
                    if (purpleBlock.getType() == Material.AIR){
                        purpleBlock.setType(Material.STAINED_GLASS);
                        purpleBlock.setData((byte) 10);
                        blockLoc = loc;
                    }

                    blockLoc = blockLoc.add(2, i, 1);
                    purpleBlock = blockLoc.getBlock();
                    if (purpleBlock.getType() == Material.AIR){
                        purpleBlock.setType(Material.STAINED_GLASS);
                        purpleBlock.setData((byte) 10);
                        blockLoc = loc;
                    }
                }

                blockLoc = blockLoc.add( 2, 3, 0);
                purpleBlock = blockLoc.getBlock();
                purpleBlock.setType(Material.STAINED_GLASS);
                purpleBlock.setData((byte) 10);
                blockLoc = loc;

                blockLoc = blockLoc.add( -2, 3, 0);
                purpleBlock = blockLoc.getBlock();
                purpleBlock.setType(Material.STAINED_GLASS);
                purpleBlock.setData((byte) 10);
                blockLoc = loc;
            }
        }
    }
}
