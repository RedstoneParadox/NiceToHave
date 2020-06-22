package io.github.redstoneparadox.nicetohave.mixin.client.recipebook;

import io.github.redstoneparadox.nicetohave.recipe.PaintbrushRecipe;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public abstract class MixinClientRecipeBook {
	@Inject(method = "getGroupForRecipe", at = @At("HEAD"), cancellable = true)
	private static void getGroupForRecipe(Recipe<?> recipe, CallbackInfoReturnable<RecipeBookGroup> cir) {
		if (recipe instanceof PaintbrushRecipe) {
			cir.setReturnValue(RecipeBookGroup.UNKNOWN);
		}
	}
}
