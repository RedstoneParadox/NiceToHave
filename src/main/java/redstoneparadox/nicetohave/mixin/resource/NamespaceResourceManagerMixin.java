package redstoneparadox.nicetohave.mixin.resource;

import net.minecraft.resource.NamespaceResourceManager;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(NamespaceResourceManager.class)
public abstract class NamespaceResourceManagerMixin {

    @Shadow @Final protected List<ResourcePack> packList;
    private ResourcePack capturedPack = null;
    private boolean hasVanillaGone = false;

    @Inject(method = "addPack", at = @At("HEAD"))
    private void captureResourcePack(ResourcePack resourcePack_1, CallbackInfo ci) {
        if (resourcePack_1.getName().equals("Default")) {
            hasVanillaGone = true;
        }
        if (resourcePack_1.getName().equals("Nice to Have") && !hasVanillaGone) {
            capturedPack = resourcePack_1;
        }
    }

    @Inject(method = "addPack", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", shift = At.Shift.AFTER))
    private void addCapturedPack(ResourcePack resourcePack_1, CallbackInfo ci) {
        if (hasVanillaGone && capturedPack != null) {
            NamespaceResourceManager self = ((NamespaceResourceManager)(Object)this);
            ResourcePack pack = capturedPack;
            packList.remove(capturedPack);
            capturedPack = null;
            self.addPack(pack);
        }
    }
}
