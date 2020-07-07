/*
This class is called when a player dies. It checks whether a player is
 */
package com.gmail.shawnpkeene.project1;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeathInGame implements Listener {
    private Plugin plugin = Project1.getPlugin(Project1.class);
    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent event) {
        ArenaCoordinates coordinates = new ArenaCoordinates();
        int[] gameLobbyLoc = coordinates.getGameLobbyCoordinates();
        int[] arenaLoc = coordinates.getArenaCoordinates();
        Player player = event.getEntity();
        event.getDrops().clear();
        Location loc = player.getLocation();
        Location location = new Location(player.getWorld(), gameLobbyLoc[0], gameLobbyLoc[1], gameLobbyLoc[2]);
        GameStart game = new GameStart();
        int amountOfDeadPlayersRed = 0;
        int amountOfDeadPlayersBlue = 0;
        // z1 first, z2 second, x2 third, x1 last
        if (loc.getBlockZ() > arenaLoc[2] && loc.getBlockZ() < arenaLoc[3] &&
                loc.getBlockX() < arenaLoc[1] && loc.getBlockX() > arenaLoc[0]) {
            player.teleport(location);

            if (TeamBalance.getTeam(player).equalsIgnoreCase("Red Team")) {
                ++amountOfDeadPlayersRed;

                if (amountOfDeadPlayersRed == TeamBalance.getAmountPlayers(player)) {
                    for (Player player2: Bukkit.getOnlinePlayers()) {
                        Location loc2 = player2.getLocation();
                        //z1 first, z2 second, x2 third, x1 last
                        if (loc2.getBlockZ() > arenaLoc[2] && loc2.getBlockZ() <arenaLoc[3] &&
                                loc2.getBlockX() < arenaLoc[1] && loc2.getBlockX() > arenaLoc[0]) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player2.teleport(location);
                                }
                            }.runTaskLater(plugin,1);
                        }
                    }
                    game.runTaskTimer(plugin, 0, 20);
                    for (Player player2: Bukkit.getOnlinePlayers()) {

                        if (TeamBalance.getTeam(player2).equalsIgnoreCase("Red Team") ||
                                TeamBalance.getTeam(player2).equalsIgnoreCase("Blue Team")) {
                            TeamBalance.onLeaveGame(player2);
                        }
                    }
                }
            } else if (TeamBalance.getTeam(player).equalsIgnoreCase("Blue Team")) {
                ++amountOfDeadPlayersBlue;

                if (amountOfDeadPlayersBlue == TeamBalance.getAmountPlayers(player)) {
                    for (Player player2: Bukkit.getOnlinePlayers()) {
                        Location loc2 = player2.getLocation();
                        //z1 first, z2 second, x2 third, x1 last
                        if (loc2.getBlockZ() > arenaLoc[2] && loc2.getBlockZ() < arenaLoc[3] &&
                                loc2.getBlockX() < arenaLoc[1] && loc2.getBlockX() > arenaLoc[0]) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player2.teleport(location);
                                }
                            }.runTaskLater(plugin,1);
                        }
                    }
                    game.runTaskTimer(plugin, 0, 20);
                    for (Player player2: Bukkit.getOnlinePlayers()) {

                        if (TeamBalance.getTeam(player2).equalsIgnoreCase("Red Team") ||
                                TeamBalance.getTeam(player2).equalsIgnoreCase("Blue Team")) {
                            TeamBalance.onLeaveGame(player2);
                        }
                    }
                }
            }
        }

    }
}
