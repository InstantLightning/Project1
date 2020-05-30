/*
This class adds sounds to the wand spells
 */
package com.gmail.shawnpkeene.project1;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class WandSounds implements Listener {
    private Plugin plugin = Project1.getPlugin(Project1.class);

    public void wandRightClickSound(Location location) {
        
        for (Player player: Bukkit.getOnlinePlayers()) {
            player.playSound(location, Sound.ENTITY_ENDERDRAGON_FLAP, 1, 3);
        }
    }

    public void wandLeftClickSound(Location location) {

        for (Player player: Bukkit.getOnlinePlayers()) {
            player.playSound(location, Sound.BLOCK_GLASS_BREAK, 1, 1);
        }
    }
}
