package net.redstoneparadox.nicetohave.world.biome

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes as VanillaBiomes
import net.redstoneparadox.nicetohave.world.biome.Biomes


object Biomes {

    val BADLANDS_RIVER = BadlandsRiverBiome()
    
    fun registerBiomes() {
        register("badlands_river", BADLANDS_RIVER)
        OverworldBiomes.setRiverBiome(VanillaBiomes.BADLANDS_PLATEAU, BADLANDS_RIVER)
    }

    private fun register(id : String, biome : Biome) {
        Registry.register(Registry.BIOME, "nicetohave:$id", biome)
    }
}