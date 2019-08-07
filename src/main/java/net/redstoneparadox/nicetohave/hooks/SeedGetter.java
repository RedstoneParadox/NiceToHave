package net.redstoneparadox.nicetohave.hooks;

import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;

public interface SeedGetter {

    @Nullable
    Item getSeed();

}
