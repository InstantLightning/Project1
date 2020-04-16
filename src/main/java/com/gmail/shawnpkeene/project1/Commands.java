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
    public String gm = "gm";
    public String lobby = "lobby";
    @Override
    public boolean onCommand(CommandSender sender, Command gamemode, String label, String[] args) {
        if (sender instanceof Player) {
            //lobby command
            if(gamemode.getName().equalsIgnoreCase(lobby)) {
                Location loc = new Location(((Player) sender).getWorld(),566.5f, 4, -535.5f);
                ((Player) sender).teleport(loc);
                JoinVillager villager = new JoinVillager();
                villager.setPlayersInZone(-1);
                sender.sendMessage(ChatColor.GOLD + "woosh!");
                return true;
            }
            //gamemode command
            if(gamemode.getName().equalsIgnoreCase(gm) && sender.isOp()) {
                if(args.length!=0) {
                    String number = args[0];
                    if (number.equals("1")) {
                        ((Player) sender).getPlayer().setGameMode(GameMode.CREATIVE);
                        ((Player) sender).getPlayer().sendMessage("You set your gamemode to 1!");
                    }
                    if (number.equals("0")) {
                        ((Player) sender).getPlayer().setGameMode(GameMode.SURVIVAL);
                        ((Player) sender).getPlayer().sendMessage("You set your gamemode to 0!");
                    }
                    if (number.equals("2")) {
                        ((Player) sender).getPlayer().setGameMode(GameMode.ADVENTURE);
                        ((Player) sender).getPlayer().sendMessage("You set your gamemode to 2!");
                    }
                    if (number.equals("3")) {
                        ((Player) sender).getPlayer().setGameMode(GameMode.SPECTATOR);
                        ((Player) sender).getPlayer().sendMessage("You set your gamemode to 3!");
                    }
                    return true;
                } else {
                    ((Player) sender).getPlayer().sendMessage("That didnt work!");
                    return false;
                }
            }
        }else {
            sender.sendMessage(ChatColor.RED + "That didn't work!");
            return true;
        }
        return false;
    }
}
