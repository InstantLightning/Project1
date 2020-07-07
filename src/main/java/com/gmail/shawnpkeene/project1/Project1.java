package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Project1 extends JavaPlugin {

    private static Project1 instance;

    public static Project1 instance() {
       return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Commands commands = new Commands();
        getCommand(Commands.GM).setExecutor(commands);
        getCommand(Commands.LOBBY).setExecutor(commands);
        Bukkit.getPluginManager().registerEvents(new Scythe(), this);
        Bukkit.getPluginManager().registerEvents(new Healing(), this);
        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new JoinVillager(), this);
        Bukkit.getPluginManager().registerEvents(new OnLeave(), this);
        Bukkit.getPluginManager().registerEvents(new Wand(), this);
        Bukkit.getPluginManager().registerEvents(new WandLeftClick(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathInGame(), this);
        CustomRecipes items = new CustomRecipes(this);
        items.customRecipeNumberOne();
        getLogger().info("Project1 enabled");
        ArenaCoordinates arenaCoordinates = new ArenaCoordinates();
        KillsFile killsFile = new KillsFile();
        //Ungrey to create kills file
        //killsFile.toJson();
        //Ungrey to create coordinates file
        //arenaCoordinates.toJson();
        //arenaCoordinates.fromJson();
    }
}
