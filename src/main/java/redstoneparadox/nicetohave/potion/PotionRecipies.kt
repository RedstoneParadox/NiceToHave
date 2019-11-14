package redstoneparadox.nicetohave.potion

import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.potion.Potion
import redstoneparadox.nicetohave.mixin.recipe.BrewingRecipeRegistryAccessor
import redstoneparadox.nicetohave.util.config.OldConfig

object PotionRecipes {

    fun registerRecipes() {
        if (OldConfig.Potions.insight) register(net.minecraft.potion.Potions.THICK, Items.EXPERIENCE_BOTTLE, Potions.INSIGHT, Potions.LONG_INSIGHT, Potions.STRONG_INSIGHT)

        //register("nectar", net.minecraft.potion.Potions.STRONG_HEALING, Items.ENCHANTED_GOLDEN_APPLE, Potions.INSTANCE.getNECTAR());
    }

    private fun register(base: Potion, ingredient: Item, result: Potion) {
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(base, ingredient, result)
    }

    private fun register(base: Potion, ingredient: Item, regular: Potion, long: Potion? = null, strong: Potion? = null) {
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(base, ingredient, regular)

        if (long != null) BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(base, Items.REDSTONE, long)
        if (strong != null) BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(base, Items.GLOWSTONE, strong)

        if (long != null && strong != null) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(strong, Items.REDSTONE, long)
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(long, Items.GLOWSTONE, strong)
        }
    }
}