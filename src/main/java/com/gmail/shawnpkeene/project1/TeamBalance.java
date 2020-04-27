package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Random;

public class TeamBalance {
    private static ScoreboardManager scoreBoard = Bukkit.getScoreboardManager();
    private static Scoreboard teamScoreboard = scoreBoard.getNewScoreboard();
    private static Team team = teamScoreboard.registerNewTeam("Red Team");
    private static Team team2 = teamScoreboard.registerNewTeam("Blue Team");

    public static void teamBalance(Player player) {
        int randomNumber = 0;
        Random random = new Random();
        team.setPrefix(ChatColor.RED + "[R] ");
        team2.setPrefix(ChatColor.BLUE + "[B] ");
        player.setScoreboard(teamScoreboard);

        //Bukkit.broadcastMessage("teamBalance was called");

        if (team.getSize() > team2.getSize()) {
            team2.addEntry(player.getName());
        } else if (team.getSize() == team2.getSize()) {
            randomNumber = random.nextInt(2) + 1;
            String debug = String.valueOf(randomNumber);
            //Bukkit.broadcastMessage(debug);
            if (randomNumber == 2) {
                team.addEntry(player.getName());
            } else {
                team2.addEntry(player.getName());
            }
        } else {
            team.addEntry(player.getName());
        }
        String debug2 = String.valueOf(team.getSize());
        String debug3 = String.valueOf(team2.getSize());
        //Bukkit.broadcastMessage("Red team has " + debug2 + "players.");
        //Bukkit.broadcastMessage("Blue team has " + debug3 + "players");
    }


    public static void onLeaveGame(Player player) {
        teamScoreboard.getEntryTeam(player.getName()).removeEntry(player.getName());
        //Bukkit.broadcastMessage("onLeaveGame was called");
    }
}
