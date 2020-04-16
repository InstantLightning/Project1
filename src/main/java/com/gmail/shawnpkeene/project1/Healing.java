package com.gmail.shawnpkeene.project1;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class Healing implements Listener {

    private HashMap<Material, Material> healBool = new HashMap<Material, Material>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damagee = event.getDamager();
        entity.sendMessage("Test");

        if (entity instanceof Player && damagee instanceof Player){
            Player player = (Player)entity;
            Player player1 = (Player)damagee;
            ItemStack mainHand = player1.getPlayer().getInventory().getItemInMainHand();

            if (mainHand.hasItemMeta() && mainHand.getItemMeta().getDisplayName().equals("Bandage")
                    && mainHand.getType() == Material.PAPER) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10*20, 1));
                event.setCancelled(true);
            }
        }
    }
}
