package redstoneparadox.nicetohave.mixin.world.biome;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BambooJungleHillsBiome;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import redstoneparadox.nicetohave.config.Config;

@Mixin(BambooJungleHillsBiome.class)
public abstract class BambooJungleHillsBiomeMixin extends Biome {

    protected BambooJungleHillsBiomeMixin(Settings biome$Settings_1) {
        super(biome$Settings_1);
    }

    @Override
    protected void addSpawn(SpawnGroup category, SpawnEntry spawnEntry) {
        if (!Config.World.INSTANCE.getPeacefulBambooJungle() || (spawnEntry.type == EntityType.OCELOT || category != SpawnGroup.MONSTER)) super.addSpawn(category, spawnEntry);
    }
}
