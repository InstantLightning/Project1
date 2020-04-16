package com.gmail.shawnpkeene.project1;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
public class JoinVillager implements Listener {

    private Plugin plugin = Project1.getPlugin(Project1.class);
    private Object Villager;
    public static int amountOfPlayers;


    @EventHandler
    public void villager(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        InventoryCommands inventory = new InventoryCommands();
        if(entity instanceof Villager) {
            event.setCancelled(true);
            inventory.newGUI(player);
        }
        else{
            //player.sendMessage("This did not execute");
        }
    }

    @EventHandler
    public void clickInventory (InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        Location loc = new Location(player.getWorld(), 558.8f, 5, -570.5f);
        Inventory gui = event.getClickedInventory();
        if(event.getCurrentItem() == null) {
            return;
        }
        if (gui.getName().equals("Sharks and Minnows Games")) {
            if (event.getCurrentItem().getType().equals(Material.ENDER_PEARL)){
                player.teleport(loc);
                setPlayersInZone(1);
            }
            event.setCancelled(true);
        }
    }

    /*@EventHandler
    public void playerInZone (PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("playerInZone called");
        Location loc = player.getLocation();
        if(loc.getBlockZ() > -572 && loc.getBlockZ() < -548 && loc.getBlockX() < 578 && loc.getBlockX() > 542) {
            setPlayersInZone(1);
        }
        else{
            player.sendMessage("Location failed");
            setPlayersInZone(-1);
        }
    } */
    public void setPlayersInZone(int i) {
        GameStart game = new GameStart();
        amountOfPlayers = amountOfPlayers+i;
        if(amountOfPlayers < 0) {
            amountOfPlayers = 0;
        }
        if(amountOfPlayers >= 2){
            game.runTaskTimer(plugin, 0, 20);
            plugin.getServer().broadcastMessage("if statement in setPlayersInZone ran");
        }
    }
    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public void runGame(){

    }
}
