/*
This class deals with the join villager and the items within the gui to teleport the player to the arena
 */
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
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
public class JoinVillager implements Listener {

    private Plugin plugin = Project1.getPlugin(Project1.class);
    private Object Villager;
    private static int amountOfPlayers;


    @EventHandler
    public void villager(PlayerInteractEntityEvent event) {

        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        InventoryCommands inventory = new InventoryCommands();

        if (entity instanceof Villager) {
            event.setCancelled(true);
            inventory.newGUI(player);
        }
        else{
            //player.sendMessage("This did not execute");
        }
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        ArenaCoordinates coordinates = new ArenaCoordinates();
        int[] gameLobbyLoc = coordinates.getGameLobbyCoordinates();
        Location loc = new Location(player.getWorld(), gameLobbyLoc[0], gameLobbyLoc[1], gameLobbyLoc[2]);
        Inventory gui = event.getClickedInventory();

        if (event.getCurrentItem() == null) {
            return;
        }

        if (gui.getName().equals("Sharks and Minnows Games")) {
            if (event.getCurrentItem().getType() == Material.ENDER_PEARL){
                player.teleport(loc);
                setPlayersInZone(1);
            }
            event.setCancelled(true);
        }
    }

    public void setPlayersInZone(int i) {

        GameStart game = new GameStart();
        amountOfPlayers += i;

        if (amountOfPlayers < 0) {
            amountOfPlayers = 0;
            game.cancel();
        }

        if (amountOfPlayers >= 2) {
            game.runTaskTimer(plugin, 0, 20);
            //Bukkit.broadcastMessage("if statement in setPlayersInZone ran");
        }
    }

    public int getAmountOfPlayers() {
        return amountOfPlayers;
    }

    public void runGame() {

    }
}
