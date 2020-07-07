
/*
This class is dealing with the wand a player gets when the game starts. It however only deals with when a player
right clicks a wand. Shows that I can do nms. I do know that I can make particles better on spigot to further optimize
it.
 */

package com.gmail.shawnpkeene.project1;
import net.minecraft.server.v1_12_R1.AxisAlignedBB;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Wand implements Listener {

    private static final double RADIUS = 1.0D;
    private static final Map<UUID, Integer> KILLS = new HashMap<>();
    private static final Map<UUID, Integer> COOLDOWN = new HashMap<>();


    @EventHandler
    public void onJoin2(PlayerJoinEvent event) {
        KillsFile killsFile = new KillsFile();
        Player player = event.getPlayer();
        int kills = 0;
        kills = killsFile.fromJson(player.getUniqueId().toString());
        KILLS.put(player.getUniqueId(), kills);
    }

    @EventHandler
    public void wand(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        KillsFile killsFile = new KillsFile();

        if (hand == null || hand.getType() == Material.AIR) {
            return;
        }
        ItemMeta handMeta = hand.getItemMeta();
        Plugin plugin = Project1.getPlugin(Project1.class);
        float r = 1f;
        float g = 1f;
        float b = 1f;

        if (hand.getType() == Material.STICK && handMeta.getDisplayName().equals("Wand") &&
                event.getAction() == Action.RIGHT_CLICK_AIR && !COOLDOWN.containsKey(player.getUniqueId())) {
            Location loc = player.getLocation();
            Vector direction = loc.getDirection().normalize();
            loc.add(0,1.5,0);
            WandSounds wandSound = new WandSounds();
            COOLDOWN.put(player.getUniqueId(), 2);
            new BukkitRunnable() {
                @Override
                public void run() {
                   if (COOLDOWN.get(player.getUniqueId()) == 0) {
                       COOLDOWN.remove(player.getUniqueId());
                       this.cancel();
                   } else {
                       int timer = COOLDOWN.get(player.getUniqueId());
                       COOLDOWN.put(player.getUniqueId(), --timer);
                   }
                }
            }.runTaskTimer(plugin, 0, 20);

            new BukkitRunnable() {
                double distance = 0.0;

                @Override
                public void run() {

                    loc.add(direction.getX() * distance, direction.getY() * distance, direction.getZ() * distance);
                    PacketPlayOutWorldParticles particles =
                            new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_NORMAL, true,
                                    (float) loc.getX(),
                                    (float) loc.getY(),
                                    (float) loc.getZ(),
                                    r,
                                    g,
                                    b,
                                    0,
                                    100,
                                    (int[]) null);
                    wandSound.wandRightClickSound(loc);

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

                        if (playerBox.c(particleBox) && onlinePlayer != player && TeamBalance.getTeam(player) !=
                                TeamBalance.getTeam(onlinePlayer)) {
                            //plugin.getServer().broadcastMessage("If statement in wand for loop worked");
                            onlinePlayer.setHealth(0.0);
                            onlinePlayer.spigot().respawn();
                            int kills = KILLS.getOrDefault(player.getUniqueId(), 0);
                            KILLS.put(player.getUniqueId(), ++kills);
                            String valueInt = String.valueOf(KILLS.get(player.getUniqueId()));
                            plugin.getServer().broadcastMessage("Player has " + valueInt + " kills");

                            killsFile.toJson(player.getUniqueId().toString(), true);
                                //Above saves kills to file
                        }

                    }
                    Location locSearch = loc;
                    boolean solid = false;

                    for (double i = -0.1; i <= 0.1; i += .1) {
                        for (double j = -0.1; j <= 0.1; j += .1) {
                            for(double k = -0.1; k <= 0.1; k += .1) {
                                locSearch.add(i, j, k);
                                if (distance > 50 || locSearch.getBlock().getType().isSolid()) {
                                    solid = true;
                                }
                            }
                        }
                    }
                    if (distance > 50 || solid == true) {
                        //plugin.getServer().broadcastMessage("If statement to cancel wand worked");
                        this.cancel();
                    }


                    loc.subtract(direction.getX() * distance, direction.getY() * distance, direction.getZ() * distance);

                    distance += 2;
                }
            }.runTaskTimer(plugin, 0, 1);
        }
    }
    /*
       UUID1 3
       UUID2 20
       UUID3 22
       Convert uuid to string, search for text ending at a space that matches the UUID, get the amount of kills in return
        */
//    public static void loadPlayerData(UUID uuid) {
//      String key = uuid.toString();
//      try {
//          FileUtilities.searchFileForKills(key);
//      }catch (IOException e) {
//          e.printStackTrace();
//      }
//    }
}
