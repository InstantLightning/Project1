package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeave implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Bukkit.broadcastMessage("That worked");
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        JoinVillager villager = new JoinVillager();
        if(loc.getBlockZ() > -572 && loc.getBlockZ() < -548 && loc.getBlockX() < 578 && loc.getBlockX() > 542) {
            villager.setPlayersInZone(-1);
            //Bukkit.broadcastMessage("If statement worked");
        }
        else{
            //Bukkit.broadcastMessage("That didnt work");
            return;
        }
    }
}
