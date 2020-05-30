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

        Player player = event.getEntity();
        event.getDrops().clear();
        Location loc = player.getLocation();
        Location location = new Location(player.getWorld(), 558.8f, 5f, -570.5f);
        GameStart game = new GameStart();
        int amountOfDeadPlayersRed = 0;
        int amountOfDeadPlayersBlue = 0;

        if (loc.getBlockZ() > -572 && loc.getBlockZ() < -548 && loc.getBlockX() < 578 && loc.getBlockX() > 542) {
            player.teleport(location);

            if (TeamBalance.getTeam(player).equalsIgnoreCase("Red Team")) {
                ++amountOfDeadPlayersRed;

                if (amountOfDeadPlayersRed == TeamBalance.getAmountPlayers(player)) {
                    for (Player player2: Bukkit.getOnlinePlayers()) {
                        Location loc2 = player2.getLocation();

                        if (loc2.getBlockZ() > -572 && loc2.getBlockZ() < -548 &&
                                loc2.getBlockX() < 578 && loc2.getBlockX() > 542) {
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

                        if (TeamBalance.getTeam(player).equalsIgnoreCase("Red Team") ||
                                TeamBalance.getTeam(player).equalsIgnoreCase("Blue Team")) {
                            TeamBalance.onLeaveGame(player2);
                        }
                    }
                }
            } else if (TeamBalance.getTeam(player).equalsIgnoreCase("Blue Team")) {
                ++amountOfDeadPlayersBlue;

                if (amountOfDeadPlayersBlue == TeamBalance.getAmountPlayers(player)) {
                    for (Player player2: Bukkit.getOnlinePlayers()) {
                        Location loc2 = player2.getLocation();

                        if (loc2.getBlockZ() > -572 && loc2.getBlockZ() < -548 &&
                                loc2.getBlockX() < 578 && loc2.getBlockX() > 542) {
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

                        if (TeamBalance.getTeam(player).equalsIgnoreCase("Red Team") ||
                                TeamBalance.getTeam(player).equalsIgnoreCase("Blue Team")) {
                            TeamBalance.onLeaveGame(player2);
                        }
                    }
                }
            }
        }

    }
}
