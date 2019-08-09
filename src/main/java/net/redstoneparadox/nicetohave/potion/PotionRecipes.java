package net.redstoneparadox.nicetohave.potion;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.redstoneparadox.nicetohave.util.Config;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PotionRecipes {

    private static Method addMethod;
    private static Object listInstance;
    private static Constructor<?> recipeConstructor;

    static {
        try {
            Field potion_recipes_field = BrewingRecipeRegistry.class.getDeclaredField("POTION_RECIPES");
            potion_recipes_field.setAccessible(true);
            listInstance = potion_recipes_field.get(null);
            Class<List<?>> listClass = (Class<List<?>>) listInstance.getClass();
            addMethod = listClass.getDeclaredMethod("add", Object.class);
            addMethod.setAccessible(true);
            Class<?> recipeClass = Class.forName("net.minecraft.recipe.BrewingRecipeRegistry$Recipe");
            recipeConstructor = recipeClass.getDeclaredConstructor(Object.class, Ingredient.class, Object.class);
            recipeConstructor.setAccessible(true);
        }
        catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

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
            try {
                addMethod.invoke(listInstance, recipeConstructor.newInstance(base, Ingredient.ofItems(ingredient), result));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
