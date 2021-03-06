/*
This class uses a runnable for the countdown of the game.
It is also responsible for teleporting players to the arena
 */
package com.gmail.shawnpkeene.project1;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStart extends BukkitRunnable {
    private JoinVillager gettingPlayers = new JoinVillager();
    private Plugin plugin = Project1.getPlugin(Project1.class);
    private int players = gettingPlayers.getAmountOfPlayers();
    private static int counter = 5;
    private boolean gameStarted = false;
    @Override
    public void run() {
        //Bukkit.broadcastMessage("Run ran");
        int randomNumber = 0;

        if (counter >= 0) {
            //Bukkit.broadcastMessage("if statement ran");
            for (Player player: Bukkit.getOnlinePlayers()) {
                //Bukkit.broadcastMessage("for loop for players ran");
                Location loc = player.getLocation();
                ArenaCoordinates coordinates = new ArenaCoordinates();
                int[] redLoc = coordinates.getRedTeamCoordinates();
                int[] blueLoc = coordinates.getBlueTeamCoordinates();
                int[] arenaLoc = coordinates.getArenaCoordinates();
                Location location = new Location(player.getWorld(), redLoc[0], redLoc[1], redLoc[2]);
                Location location2 = new Location(player.getWorld(), blueLoc[0], blueLoc[1], blueLoc[2]);
                //542 x1, 578 x2, -573 z1, -548 z2
                //z1 first, z2 second, x2 third, x1 last
                if (loc.getBlockZ() > arenaLoc[2] && loc.getBlockZ() < arenaLoc[3] &&
                        loc.getBlockX() < arenaLoc[1] && loc.getBlockX() > arenaLoc[0]) {
                    //Bukkit.broadcastMessage("if statement in for loop ran");
                    String string = String.valueOf(counter);
                    player.sendTitle(ChatColor.GOLD + "Game begins in " + string, null, 0, 20, 20);
                    player.playSound(loc, Sound.BLOCK_NOTE_PLING, 1, 1);

                    if (counter == 5) {
                        player.getInventory().clear();
                    }

                    if (counter == 0) {
                        ItemStack stick = new ItemStack(Material.STICK);
                        ItemMeta stickMeta = stick.getItemMeta();
                        stickMeta.setDisplayName("Wand");
                        stick.setItemMeta(stickMeta);
                        player.getInventory().setItemInMainHand(stick);
                        TeamBalance.teamBalance(player);

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
            gameStarted = true;
            this.cancel();
        }
    }
    public boolean getGameStarted () {
        return gameStarted;
    }
}
