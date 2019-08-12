package redstoneparadox.nicetohave.mixin.world.biome;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BambooJungleBiome;
import net.minecraft.world.biome.Biome;
import net.redstoneparadox.nicetohave.util.Config;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BambooJungleBiome.class)
public abstract class BambooJungleBiomeMixin extends Biome {

    protected BambooJungleBiomeMixin(Settings biome$Settings_1) {
        super(biome$Settings_1);
    }

    @Override
    protected void addSpawn(EntityCategory category, SpawnEntry spawnEntry) {
        if (!Config.INSTANCE.getMiscOption("peaceful_bamboo_jungle", Boolean.class, true) || (spawnEntry.type == EntityType.OCELOT || category != EntityCategory.MONSTER)) super.addSpawn(category, spawnEntry);
    }
}
