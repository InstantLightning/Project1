package com.gmail.shawnpkeene.project1;

import net.minecraft.server.v1_12_R1.Entity;
import net.minecraft.server.v1_12_R1.World;
import net.minecraft.server.v1_12_R1.WorldServer;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

public class Scythe implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand() == null) {
            return;
        }
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (mainHand.hasItemMeta() && mainHand.getItemMeta().getDisplayName().equals("Scythe") &&
                mainHand.getType() == Material.IRON_HOE) {
            Block grassBlock = event.getBlock();
            if (grassBlock.getType()== Material.CROPS) {
                BlockState state = grassBlock.getState();
                Crops cropState = (Crops) state.getData();
                if (cropState.getState() == CropState.RIPE) {
                    Block temp;
                    short blocksBroken = 0;
                    int radius = 3;
                    int radiusSquared = radius * radius;
                    for (int i = -radius; i <= radius; ++i) {
                        for (int j = -radius; j <= radius; ++j) {
                            if ((i * i) + (j * j) <= radiusSquared) {
                                temp = grassBlock.getRelative(i, 0, j);
                                if (temp.getState().getData() instanceof Crops &&
                                        ((Crops) temp.getState().getData()).getState() == CropState.RIPE ) {
                                    temp.breakNaturally();
                                    ++blocksBroken;
                                }
                            }
                        }
                    }
                    blocksBroken += mainHand.getDurability();
                    mainHand.setDurability(blocksBroken);
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }
}
