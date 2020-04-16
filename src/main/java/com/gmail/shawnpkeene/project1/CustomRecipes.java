package com.gmail.shawnpkeene.project1;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

public class CustomRecipes {
    private Plugin plugin;

    public CustomRecipes(Plugin plugin) {
        this.plugin = plugin;
    }

    public void customRecipeNumberOne() {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ShapedRecipe blazePowderTest = new ShapedRecipe(new NamespacedKey(plugin, "blaze_key") ,item);
        blazePowderTest.shape(" N ","N N"," N ");
        blazePowderTest.setIngredient('N', Material.BLAZE_ROD);
        plugin.getServer().addRecipe(blazePowderTest);
    }
}
