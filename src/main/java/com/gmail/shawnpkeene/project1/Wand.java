package com.gmail.shawnpkeene.project1;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Wand implements Listener {

    private static final double RADIUS = 1.0D;
    private static final Map<UUID, Integer> KILLS = new HashMap<>();

    @EventHandler
    public void wand(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        ItemMeta handMeta = hand.getItemMeta();
        Plugin plugin = Project1.getPlugin(Project1.class);
        float r = 1f;
        float g = 1f;
        float b = 1f;
        if (hand.getType().equals(Material.STICK) && handMeta.getDisplayName().equals("Wand") &&
                event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Location loc = player.getLocation();
            Vector direction = loc.getDirection().normalize();
            loc.add(0,1.5,0);
            new BukkitRunnable() {
                Double distance = 0.0;

                @Override
                public void run() {

                    loc.add(direction.getX() * distance, direction.getY() * distance, direction.getZ() * distance);
                    PacketPlayOutWorldParticles particles =
                            new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_NORMAL, true,
                                    (float) loc.getBlockX(),
                                    (float) loc.getBlockY(),
                                    (float) loc.getBlockZ(),
                                    r,
                                    g,
                                    b,
                                    0,
                                    100,
                                    null);

                    for (Player onlinePlayer: Bukkit.getOnlinePlayers()) {

                        EntityPlayer nmsPlayer = ((CraftPlayer) onlinePlayer).getHandle();
                        nmsPlayer.playerConnection.sendPacket(particles);
                        AxisAlignedBB playerBox = nmsPlayer.getBoundingBox();
                        AxisAlignedBB particleBox = new AxisAlignedBB(
                                loc.getX() - RADIUS,
                                loc.getY() - RADIUS,
                                loc.getZ() - RADIUS,
                                loc.getX() + RADIUS,
                                loc.getY() + RADIUS,
                                loc.getZ() + RADIUS);

                        if (playerBox.c(particleBox) && onlinePlayer != player) {
                            plugin.getServer().broadcastMessage("If statement in wand for loop worked");
                            onlinePlayer.setHealth(0.0);
                            onlinePlayer.spigot().respawn();
                            int kills = KILLS.getOrDefault(player.getUniqueId(), 0);
                            KILLS.put(player.getUniqueId(), ++kills);
                            String valueInt = String.valueOf(KILLS.get(player.getUniqueId()));
                            plugin.getServer().broadcastMessage("Player has " + valueInt + " kills");
                        }

                    }

                    if (distance > 50 || loc.getBlock().getType().isSolid()) {
                        //plugin.getServer().broadcastMessage("If statement to cancel wand worked");
                        this.cancel();
                    }

                    loc.subtract(direction.getX() * distance, direction.getY() * distance, direction.getZ() * distance);

                    distance += 2;
                }
            }.runTaskTimer(plugin,0,1);
        }
    }
}
