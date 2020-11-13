package io.github.redstoneparadox.nicetohave.mixin.resource;

import io.github.redstoneparadox.nicetohave.data.DataManager;
import kotlin.jvm.functions.Function0;
import net.minecraft.resource.AbstractFileResourcePack;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFileResourcePack.class)
public abstract class AbstractFileResourcePackMixin implements ResourcePack {
    @Shadow public abstract String getName();

    @Inject(method = "contains", at = @At("HEAD"), cancellable = true)
    private void contains(ResourceType resourceType, Identifier identifier, CallbackInfoReturnable<Boolean> cir) {
        if (resourceType == ResourceType.SERVER_DATA && getName().equals("Nice to Have")) {
            @Nullable Function0<Boolean> condition = DataManager.INSTANCE.getCondition(identifier);

            if (condition != null) {
                cir.setReturnValue(condition.invoke());
                cir.cancel();
            }
        }
    }
}
