package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
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
        //Bukkit.broadcastMessage("Run ran");
        int randomNumber = 0;
        if (counter >= 0) {
            //Bukkit.broadcastMessage("if statement ran");
            for (Player player: Bukkit.getOnlinePlayers()) {
                //Bukkit.broadcastMessage("for loop for players ran");
                Location loc = player.getLocation();
                Location location = new Location(player.getWorld(), 544f, 4f, -558f);
                Location location2 = new Location(player.getWorld(), 576f, 4f, -558f);
                if (loc.getBlockZ() > -572 && loc.getBlockZ() < -548 && loc.getBlockX() < 578 && loc.getBlockX() > 542) {
                    //Bukkit.broadcastMessage("if statement in for loop ran");
                    String string = String.valueOf(counter);
                    player.sendTitle("Game begins in " + string, null, 0, 20, 20);
                    if (counter == 0) {
                        if (TeamBalance.getTeam(player).equalsIgnoreCase( "Red Team") ) {
                            player.teleport(location);
                        } else if (TeamBalance.getTeam(player).equalsIgnoreCase( "Blue Team")) {
                            player.teleport(location2);
                        }
                    }
                }
            }
            --counter;
        } else {
            //Bukkit.broadcastMessage("if statement failed");
            counter = 5;
            this.cancel();
        }
    }
}
