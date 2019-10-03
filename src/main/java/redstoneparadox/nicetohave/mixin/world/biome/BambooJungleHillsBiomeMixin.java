package redstoneparadox.nicetohave.mixin.world.biome;

import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.BambooJungleHillsBiome;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import redstoneparadox.nicetohave.util.config.Config;

@Mixin(BambooJungleHillsBiome.class)
public abstract class BambooJungleHillsBiomeMixin extends Biome {

    protected BambooJungleHillsBiomeMixin(Settings biome$Settings_1) {
        super(biome$Settings_1);
    }

    @Override
    protected void addSpawn(EntityCategory category, SpawnEntry spawnEntry) {
        if (!Config.Misc.INSTANCE.getPeacefulBambooJungle() || (spawnEntry.type == EntityType.OCELOT || category != EntityCategory.MONSTER)) super.addSpawn(category, spawnEntry);
    }
}
