package redstoneparadox.nicetohave.mixin.world.biome;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BambooJungleBiome;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import redstoneparadox.nicetohave.config.Config;

@Mixin(BambooJungleBiome.class)
public abstract class BambooJungleBiomeMixin extends Biome {

    protected BambooJungleBiomeMixin(Settings biome$Settings_1) {
        super(biome$Settings_1);
    }

    @Override
    protected void addSpawn(SpawnGroup category, SpawnEntry spawnEntry) {
        if (Config.World.INSTANCE.getPeacefulBambooJungle() || (spawnEntry.type == EntityType.OCELOT || category != SpawnGroup.MONSTER)) super.addSpawn(category, spawnEntry);
    }
}
