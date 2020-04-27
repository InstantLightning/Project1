package com.gmail.shawnpkeene.project1;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Commands implements Listener, CommandExecutor {
    public static final String LOBBY = "lobby";
    public static final String GM = "gm";
    @Override
    public boolean onCommand(CommandSender sender, Command gamemode, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        TeamBalance team = new TeamBalance();

        //LOBBY command
        if (gamemode.getName().equalsIgnoreCase(LOBBY)) {
            Location loc = new Location(((Player) sender).getWorld(),566.5f, 4, -535.5f);
            player.teleport(loc);
            JoinVillager villager = new JoinVillager();
            team.onLeaveGame(player);
            villager.setPlayersInZone(-1);
            sender.sendMessage(ChatColor.GOLD + "woosh!");
            return true;
        }

        //gamemode command
        if (gamemode.getName().equalsIgnoreCase(GM) && sender.isOp()) {
            if (args.length!=0) {
                String number = args[0];
                if (number.equals("1")) {
                    player.setGameMode(GameMode.CREATIVE);
                    player.sendMessage("You set your gamemode to 1!");
                }
                if (number.equals("0")) {
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage("You set your gamemode to 0!");
                }
                if (number.equals("2")) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.sendMessage("You set your gamemode to 2!");
                }
                if (number.equals("3")) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("You set your gamemode to 3!");
                }
                return true;
            } else {
                player.sendMessage("That didnt work!");
                return true;
            }

        } else {
            sender.sendMessage(ChatColor.RED + "That didn't work!");
            return true;
        }
    }
}
