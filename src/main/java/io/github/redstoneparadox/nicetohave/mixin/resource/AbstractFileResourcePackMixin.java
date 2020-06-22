package io.github.redstoneparadox.nicetohave.mixin.resource;

import net.minecraft.resource.AbstractFileResourcePack;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import io.github.redstoneparadox.nicetohave.config.Config;

@Mixin(AbstractFileResourcePack.class)
public abstract class AbstractFileResourcePackMixin implements ResourcePack {

    private final Identifier POWERED_RAIL = new Identifier("minecraft:recipes/powered_rail.json");
    private final Identifier DETECTOR_RAIL = new Identifier("minecraft:recipes/detector_rail.json");
    private final Identifier ACTIVATOR_RAIL = new Identifier("minecraft:recipes/activator_rail.json");

    @Shadow public abstract String getName();

    @Inject(method = "contains", at = @At("HEAD"), cancellable = true)
    private void contains(ResourceType resourceType, Identifier identifier, CallbackInfoReturnable<Boolean> cir) {
        if (resourceType == ResourceType.SERVER_DATA && getName().equals("Nice to Have")) {
            if (identifier.equals(POWERED_RAIL) || identifier.equals(DETECTOR_RAIL) || identifier.equals(ACTIVATOR_RAIL)) {
                cir.setReturnValue(Config.Recipes.INSTANCE.getIncreasedRailOutput());
                cir.cancel();
            }
        }
    }
}
