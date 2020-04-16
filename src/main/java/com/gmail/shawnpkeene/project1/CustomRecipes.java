package com.gmail.shawnpkeene.project1;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class CustomRecipes implements Listener {
    private Plugin plugin = Project1.getPlugin(Project1.class);
    public void customRecipeNumberOne() {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ShapedRecipe blazePowderTest = new ShapedRecipe(new NamespacedKey(plugin, "blaze_key") ,item);
        blazePowderTest.shape(" N ","N N"," N ");
        blazePowderTest.setIngredient('N', Material.BLAZE_ROD);
        plugin.getServer().addRecipe(blazePowderTest);
    }
}
