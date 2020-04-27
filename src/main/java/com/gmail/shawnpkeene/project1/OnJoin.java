package com.gmail.shawnpkeene.project1;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");
        player.sendMessage(ChatColor.GOLD + "Welcome back to your server");
        Location spawn = new Location(player.getWorld(), 565.5D,4.2D,-535.5D);
        player.teleport(spawn);
    }
}
