package redstoneparadox.nicetohave.mixin.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import redstoneparadox.nicetohave.util.newconfig.Config;

@Mixin(DefaultBiomeFeatures.class)
public abstract class DefaultBiomeFeaturesMixin {

    private static boolean disablePonds = Config.World.INSTANCE.getDisablePonds();

    @Inject(method = "addDefaultLakes", at = @At("HEAD"), cancellable = true)
    private static void addDefaultLakes(Biome biome_1, CallbackInfo ci) {
        if (disablePonds) ci.cancel();
    }

    @Inject(method = "addDesertLakes", at = @At("HEAD"), cancellable = true)
    private static void addDesertLakes(Biome biome_1, CallbackInfo ci) {
        if (disablePonds) ci.cancel();
    }
}
