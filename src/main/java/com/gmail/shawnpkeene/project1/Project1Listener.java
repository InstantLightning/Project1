package com.gmail.shawnpkeene.project1;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Project1Listener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand() == null) {
            return;
        }

        Location locBelow = player.getLocation().subtract(0,1,0);
        Block blockBelow = locBelow.getBlock();
        Material type = blockBelow.getType();
        Material hand = player.getInventory().getItemInMainHand().getType();

        if (type != Material.AIR && hand != Material.AIR && hand.isBlock() && hand.isSolid() && type.isSolid()) {
            blockBelow.setType(hand);
        }
    }
    
}
