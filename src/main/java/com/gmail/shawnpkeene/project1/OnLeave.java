/*
This class takes a player out of the game if they leave
 */
package com.gmail.shawnpkeene.project1;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeave implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        //Bukkit.broadcastMessage("That worked");
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        JoinVillager villager = new JoinVillager();
        ArenaCoordinates coordinates = new ArenaCoordinates();
        int[] arenaLoc = coordinates.getArenaCoordinates();
        //z1 first, z2 second, x2 third, x1 fourth
        if (loc.getBlockZ() >= arenaLoc[2] && loc.getBlockZ() <= arenaLoc[3] &&
                loc.getBlockX() <= arenaLoc[1] && loc.getBlockX() >= arenaLoc[0]) {
            TeamBalance.onLeaveGame(player);
            villager.setPlayersInZone(-1);

            //Bukkit.broadcastMessage("If statement worked");
        } else {
            //Bukkit.broadcastMessage("That didnt work");
        }
    }
}
