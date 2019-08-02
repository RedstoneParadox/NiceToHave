package net.redstoneparadox.nicetohave.world.biome

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes as VanillaBiomes


object Biomes {

    val GOLD_RIVER = GoldRiverBiome()
    val FROZEN_GOLD_RIVER = GoldRiverBiome(0.0f, Biome.Precipitation.SNOW, true)
    
    fun registerBiomes() {
        register("gold_river", GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.BADLANDS_PLATEAU, GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.BADLANDS, GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.ERODED_BADLANDS, GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.MODIFIED_BADLANDS_PLATEAU, GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.MODIFIED_WOODED_BADLANDS_PLATEAU, GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.WOODED_BADLANDS_PLATEAU, GOLD_RIVER)

        register("frozen_gold_river", FROZEN_GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.SNOWY_TUNDRA, FROZEN_GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.SNOWY_MOUNTAINS, FROZEN_GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.SNOWY_TAIGA, FROZEN_GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.SNOWY_TAIGA_HILLS, FROZEN_GOLD_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.SNOWY_TAIGA_MOUNTAINS, FROZEN_GOLD_RIVER)
    }

    private fun register(id : String, biome : Biome) {
        Registry.register(Registry.BIOME, "nicetohave:$id", biome)
    }
}