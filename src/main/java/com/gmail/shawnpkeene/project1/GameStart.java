package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStart extends BukkitRunnable {
    private JoinVillager gettingPlayers = new JoinVillager();
    private Plugin plugin = Project1.getPlugin(Project1.class);
    private int players = gettingPlayers.getAmountOfPlayers();
    private static int counter = 5;
    public void countDownTimer() {

    }
    @Override
    public void run() {
        plugin.getServer().broadcastMessage("Run ran");
        if (counter >= 0) {
            plugin.getServer().broadcastMessage("if statement ran");
            for(Player player: Bukkit.getOnlinePlayers()) {
                plugin.getServer().broadcastMessage("for loop for players ran");
                Location loc = player.getLocation();
                if(loc.getBlockZ() > -572 && loc.getBlockZ() < -548 && loc.getBlockX() < 578 && loc.getBlockX() > 542) {
                    plugin.getServer().broadcastMessage("if statement in for loop ran");
                    String string = String.valueOf(counter);
                    player.sendTitle("Game begins in " + string, null, 0, 20, 20);
                }
            }
            --counter;
        } else{
            plugin.getServer().broadcastMessage("if statement failed");
            counter=5;
            this.cancel();
        }
    }
}
