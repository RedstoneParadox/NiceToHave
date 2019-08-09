package net.redstoneparadox.nicetohave.potion;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.redstoneparadox.nicetohave.mixin.recipe.BrewingRecipeRegistryAccessor;
import net.redstoneparadox.nicetohave.util.Config;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PotionRecipes {

    public static void registerRecipes() {
        register("insight", net.minecraft.potion.Potions.THICK, Items.EXPERIENCE_BOTTLE, Potions.INSTANCE.getINSIGHT());
        register("insight", Potions.INSTANCE.getINSIGHT(), Items.REDSTONE, Potions.INSTANCE.getLONG_INSIGHT());
        register("insight", Potions.INSTANCE.getINSIGHT(), Items.GLOWSTONE_DUST, Potions.INSTANCE.getSTRONG_INSIGHT());
        register("insight", Potions.INSTANCE.getSTRONG_INSIGHT(), Items.REDSTONE, Potions.INSTANCE.getLONG_INSIGHT());
        register("insight", Potions.INSTANCE.getLONG_INSIGHT(), Items.GLOWSTONE_DUST, Potions.INSTANCE.getSTRONG_INSIGHT());

        //register("nectar", net.minecraft.potion.Potions.STRONG_HEALING, Items.ENCHANTED_GOLDEN_APPLE, Potions.INSTANCE.getNECTAR());
    }

    private static void register(String configKey, Potion base, Item ingredient, Potion result) {
        if (Config.INSTANCE.getPotionOption(configKey, Boolean.class, true)) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(base, ingredient, result);
        }
    }
}
