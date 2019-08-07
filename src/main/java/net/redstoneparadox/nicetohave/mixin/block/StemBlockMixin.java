package net.redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.StemBlock;
import net.minecraft.item.Item;
import net.redstoneparadox.nicetohave.hooks.SeedGetter;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StemBlock.class)
public abstract class StemBlockMixin implements SeedGetter {

    @Shadow
    protected Item getPickItem() {
        return null;
    }

    @Override
    public @Nullable Item getSeed() {
        return getPickItem();
    }
}
