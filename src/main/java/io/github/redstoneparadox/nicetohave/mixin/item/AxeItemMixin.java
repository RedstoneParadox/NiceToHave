package io.github.redstoneparadox.nicetohave.mixin.item;

import com.google.common.collect.ImmutableMap;
import io.github.redstoneparadox.nicetohave.compat.TerrestriaCompat;
import io.github.redstoneparadox.nicetohave.compat.TraverseCompat;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.redstoneparadox.nicetohave.block.NiceToHaveBlocks;
import io.github.redstoneparadox.nicetohave.config.Config;

import java.util.Map;

@Mixin(AxeItem.class)
public class AxeItemMixin {

    @Mutable
    @Shadow
    @Final
    protected static Map<Block, Block> STRIPPED_BLOCKS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void staticInit(CallbackInfo ci) {
        if (Config.Blocks.INSTANCE.getPoles()) {
            ImmutableMap.Builder<Block, Block> builder = ImmutableMap.builder();
            builder.putAll(STRIPPED_BLOCKS);
            builder.putAll(NiceToHaveBlocks.INSTANCE.mapPolesToStrippedPoles());

            if (FabricLoader.getInstance().isModLoaded("terrestria")) TerrestriaCompat.CompatBlocks.INSTANCE.mapPolesToStrippedPoles();
            if (FabricLoader.getInstance().isModLoaded("traverse")) TraverseCompat.CompatBlocks.INSTANCE.mapPolesToStrippedPoles();

            STRIPPED_BLOCKS = builder.build();
        }
    }
}
