package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Project1 extends JavaPlugin {
    private Commands commands = new Commands();
    @Override
    public void onEnable(){
        getCommand(commands.gm).setExecutor(commands);
        getCommand(commands.lobby).setExecutor(commands);
        Bukkit.getPluginManager().registerEvents(new Project1Listener(), this);
        Bukkit.getPluginManager().registerEvents(new Scythe(), this);
        Bukkit.getPluginManager().registerEvents(new Healing(), this);
        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new JoinVillager(), this);
        Bukkit.getPluginManager().registerEvents(new OnLeave(), this);
        Bukkit.getPluginManager().registerEvents(new Wand(), this);
        CustomRecipes items =new CustomRecipes();
        items.customRecipeNumberOne();
        getLogger().info("Project1 enabled");

    }
}
