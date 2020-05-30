
/* This class deals with the wand when a player left clicks the wand. It creates a shield in front of the player.
 */

package com.gmail.shawnpkeene.project1;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WandLeftClick implements Listener {
    private static final Map<Location, Block> BLOCKLOCATION = new HashMap<>();
    private static final Map<UUID, Integer> COOLDOWN = new HashMap<>();
    @EventHandler
    public void wandLeftClick(PlayerInteractEvent event) {
        Plugin plugin = Project1.getPlugin(Project1.class);
        Player player = event.getPlayer();
        ItemStack hand = player.getInventory().getItemInMainHand();
        boolean north = false;
        boolean east = false;
        boolean south = false;
        boolean west = false;
        Location loc = player.getLocation();
        Location blockLoc = loc;
        Block purpleBlock = blockLoc.getBlock();
        loc.setX(loc.getBlockX() + 0.5);
        loc.setZ(loc.getBlockZ() + 0.5);

        if (hand == null || hand.getType() == Material.AIR) {
            return;
        }

        ItemMeta handMeta = hand.getItemMeta();
        float degrees = player.getLocation().getYaw() + 360;

        if (degrees > 360) {
            degrees -= 360;
        }
        if (degrees >= 0 && degrees <= 45.5) {
            //Bukkit.broadcastMessage(" You are clicking south");
            south = true;
        } else if (degrees > 45.5 && degrees <= 135.5) {
            //Bukkit.broadcastMessage(" You are west");
            west = true;
        } else if (degrees > 135.5 && degrees <= 225.5) {
            //Bukkit.broadcastMessage(" You are clicking north");
            north = true;
        } else if (degrees > 225.5 && degrees <= 315.5) {
            //Bukkit.broadcastMessage("You are clicking east");
            east = true;
        } else if (degrees > 315.5 && degrees <= 360) {
            //Bukkit.broadcastMessage(" You are clicking south");
            south = true;
        }

        if (hand.getType() == Material.STICK && handMeta.getDisplayName().equals("Wand") &&
                event.getAction() == Action.LEFT_CLICK_AIR && !COOLDOWN.containsKey(player.getUniqueId())) {
            COOLDOWN.put(player.getUniqueId(), 1);
            WandSounds wandSounds = new WandSounds();
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (COOLDOWN.get(player.getUniqueId()) == 0) {
                        COOLDOWN.remove(player.getUniqueId());
                        this.cancel();
                    } else {
                        int timer = COOLDOWN.get(player.getUniqueId());
                        COOLDOWN.put(player.getUniqueId(), --timer);
                    }
                }
            }.runTaskTimer(plugin, 0, 20);

            if (north) {

                for (int i = 0; i <= 1; ++i) {
                    //Blocks directly 3 blocks east of the player
                    changeBlock(purpleBlock.getRelative(3, i, 0));
                    //Blocks directly 3 blocks west of the player
                    changeBlock(purpleBlock.getRelative(-3, i, 0));
                    //Blocks one north and 2 west of player
                     changeBlock(purpleBlock.getRelative(-2, i, -1));
                    //Blocks one north and 2 east of the player

                    changeBlock(purpleBlock.getRelative(2, i, -1));
                    //Blocks 2x3 directly in front of player
                    for (int j = -1; j <= 1; ++j ) {
                        changeBlock(purpleBlock.getRelative(j, i, -2));
                    }
                }
                //Blocks 1x3 directly on top of the player
                for (int i = -1; i <= 1; ++i) {
                    changeBlock(purpleBlock.getRelative(i, 2, -1));
                    changeBlock(purpleBlock.getRelative(i, 3, 0));

                }
                //Corner to blocks

                changeBlock(purpleBlock.getRelative(2, 2, 0));
                changeBlock(purpleBlock.getRelative(-2, 2, 0));
                wandSounds.wandLeftClickSound(loc);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Map.Entry<Location, Block> entry: BLOCKLOCATION.entrySet()) {
                            entry.getKey().getBlock().setType(Material.AIR);
                        }
                    }
                }.runTaskLater(plugin, 5);

            }

            if (east) {
                for (int i = 0; i <= 1; ++i) {
                    //Blocks directly 3 blocks north of the player
                   changeBlock(purpleBlock.getRelative(0, i, 3));
                    //Blocks directly 3 blocks south of the player
                   changeBlock(purpleBlock.getRelative(0, i, -3));
                    //Blocks one east and 2 south of player
                   changeBlock(purpleBlock.getRelative(1, i, 2));
                    //Blocks one east and 2 north of the player
                   changeBlock(purpleBlock.getRelative(1, i, -2));
                    for (int j = -1; j <= 1; ++j ) {
                        changeBlock(purpleBlock.getRelative(2, i, j));
                    }
                }
                //Blocks 1x3 directly on top of the player
                for (int i = -1; i <= 1; ++i) {
                   changeBlock(purpleBlock.getRelative(1, 2, i));
                   changeBlock(purpleBlock.getRelative(0, 3, i));
                }
                //Corner to blocks
                changeBlock(purpleBlock.getRelative(0, 2, 2));
                changeBlock(purpleBlock.getRelative(0, 2, -2));
                wandSounds.wandLeftClickSound(loc);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Map.Entry<Location, Block> entry: BLOCKLOCATION.entrySet()) {
                            entry.getKey().getBlock().setType(Material.AIR);
                        }
                    }
                }.runTaskLater(plugin, 5);

            }

            if (west) {
                for (int i = 0; i <= 1; ++i) {
                    //Blocks directly 3 blocks north of the player
                    changeBlock(purpleBlock.getRelative(0, i, 3));
                    //Blocks directly 3 blocks south of the player
                    changeBlock(purpleBlock.getRelative(0, i, -3));
                    //Blocks one west and 2 south of player
                    changeBlock(purpleBlock.getRelative(-1, i, 2));
                    //Blocks one west and 2 north of the player
                    changeBlock(purpleBlock.getRelative(-1, i, -2));
                    //Blocks 2x3 directly in front of player
                    for (int j = -1; j <= 1; ++j ) {
                       changeBlock(purpleBlock.getRelative(-2, i, j));
                    }
                }
                //Blocks 1x3 directly on top of the player
                for (int i = -1; i <= 1; ++i) {
                    changeBlock(purpleBlock.getRelative(-1, 2, i));
                    changeBlock(purpleBlock.getRelative(0, 3, i));
                }
                //Corner to blocks
                changeBlock(purpleBlock.getRelative(0, 2, 2));
                changeBlock(purpleBlock.getRelative(0, 2, -2));
                wandSounds.wandLeftClickSound(loc);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Map.Entry<Location, Block> entry: BLOCKLOCATION.entrySet()) {
                            entry.getKey().getBlock().setType(Material.AIR);
                        }
                    }
                }.runTaskLater(plugin, 5);
            }
            if (south) {
                for (int i = 0; i <= 1; ++i) {
                    //Blocks directly 3 blocks east of the player
                   changeBlock(purpleBlock.getRelative(3, i, 0));
                    //Blocks directly 3 blocks west of the player
                    changeBlock(purpleBlock.getRelative(-3, i, 0));
                    //Blocks one south and 2 west of player
                   changeBlock(purpleBlock.getRelative(-2, i, 1));
                    //Blocks one south and 2 east of the player
                    changeBlock(purpleBlock.getRelative(2, i, 1));
                    //Blocks 2x3 directly in front of player
                    for (int j = -1; j <= 1; ++j ) {
                        changeBlock(purpleBlock.getRelative(j, i, 2));
                    }
                }
                //Blocks 1x3 directly on top of the player
                for (int i = -1; i <= 1; ++i) {
                    changeBlock(purpleBlock.getRelative(i, 2, 1));
                    changeBlock(purpleBlock.getRelative(i, 3, 0));
                }
                //Corner to blocks
                changeBlock(purpleBlock.getRelative(2, 2, 0));
                changeBlock(purpleBlock.getRelative(-2, 2, 0));
                wandSounds.wandLeftClickSound(loc);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (Map.Entry<Location, Block> entry: BLOCKLOCATION.entrySet()) {
                            entry.getKey().getBlock().setType(Material.AIR);
                        }
                    }
                }.runTaskLater(plugin, 5);

            }
        }
    }

    public static void changeBlock(Block purpleBlock){
        if (purpleBlock.getType() == Material.AIR) {
            BLOCKLOCATION.put(purpleBlock.getLocation(), purpleBlock);
            purpleBlock.setType(Material.STAINED_GLASS);
            purpleBlock.setData((byte) 10);
        }
    }
}
