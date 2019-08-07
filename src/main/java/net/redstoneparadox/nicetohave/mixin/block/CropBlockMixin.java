package net.redstoneparadox.nicetohave.mixin.block;

import net.minecraft.block.CropBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.redstoneparadox.nicetohave.hooks.SeedGetter;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin implements SeedGetter {

    @Shadow
    protected ItemConvertible getSeedsItem() {
        return null;
    }

    @Override
    @Nullable
    public Item getSeed() {
        return getSeedsItem().asItem();
    }
}
