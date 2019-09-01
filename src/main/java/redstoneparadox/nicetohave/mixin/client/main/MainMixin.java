package redstoneparadox.nicetohave.mixin.client.main;

import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import redstoneparadox.nicetohave.util.config.Config;

@Mixin(Main.class)
public abstract class MainMixin {

    @Inject(method = "main", at = @At("HEAD"))
    private static void main(String[] strings_1, CallbackInfo ci) {
        Config.INSTANCE.initialize();
    }
}
