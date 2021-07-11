package io.github.redstoneparadox.nicetohave.config

import io.github.redstoneparadox.paradoxconfig.config.ConfigCategory

object Config: ConfigCategory("config.json5") {
    object Items: ConfigCategory("items", "Various items") {
        var chainLink: Boolean by option(true, "chain_link", "Chain links can be used to craft chain mail.")
        var dynamite: Boolean by option(true, "dynamite", "Dynamite is a throwable explosive.")
        var wrench: Boolean by option(true, "wrench", "Can be used to rotate blocks.")
        var fertilizer: Boolean by option(true, "fertilizer", "A bonemeal alternative that is obtained from the composter.")
        var sweetBerryJuice: Boolean by option(true, "sweet_berry_juice", "A beverage made from sweet berries.")
        var appleJuice: Boolean by option(true, "apple_juice", "A beverage made from apples.")
        var paintBrush: Boolean by option(true, "paint_brush", "A tool that can be used to paint certain blocks.")
    }

    object Blocks: ConfigCategory("blocks", "Various blocks") {
        var trimmedVines: Boolean by option(true, "trimmed_vine", "Vines can be trimmed by right-clicking with shears, turning them into Trimmed Vines that don't grow.")
        var poles: Boolean by option(true, "poles", "Adds poles made out of logs and stripped logs.")
    }

    object Redstone: ConfigCategory("redstone", "Redstone tweaks and additions") {
        var goldButton: Boolean by option(true, "gold_button", "A button that emits a 1-tick Redstone signal when pressed.")
        var analogRedstoneEmitter: Boolean by option(true, "analog_redstone_emitter", "A special redstone block that can be set to output any level of Redstone signal.")
        var dispenserCropPlanting: Boolean by option(true, "dispenser_crop_planting", "Dispensers can plant crops, saplings, and a few other plants.")
        var dispenserLadderPlacement: Boolean by option(true, "dispenser_ladder_placement", "Dispensers can place and pickup ladders and scaffolding.")
        // var underwaterSwitches: Boolean by option(true, "underwater_switches", "Allows for the placement of levers and buttons underwater.")
    }

    object Recipes: ConfigCategory("recipes", "New recipes and tweaks to existing ones") {
        var increasedRailOutput: Boolean by option(true, "increased_rail_output", "Powered, Detector, and Activator Rail recipes now give 16 instead of 6.")
        var uncraftNetherwartBlock: Boolean by option(true, "uncraft_netherwart_block", "Netherwart Blocks can now be crafted back into netherwarts.")
        var melonToSlices: Boolean by option(true, "melon_to_slices", "Melon blocks can be crafted into 9 melon slices.")
        var glueSlabs: Boolean by option(true, "glue_slabs", "Combining two slabs with a slimeball allows you to convert them back into a full block.")
    }

    object Potions: ConfigCategory("potions", "Potions") {
        var insight: Boolean by option(true, "insight", "Potions of Insight allow you to gain bonus experience.")
    }

    object Enchantments: ConfigCategory("enchantments", "Enchantments") {
        var flurry: Boolean by option(true, "flurry", "Flurry is an enchantment that randomly gives you haste when attacking.")
        var flurryMaxLevels: Long by option(3, "flurry_max_levels", "Sets the maximum levels of the Flurry enchant.")
        var flurryHasteChance: Double by option(10.0, "flurry_haste_chance", "Sets the chance per level that Flurry will give you haste.")
    }

    object World: ConfigCategory("world", "Various world features") {
        var disablePonds: Boolean by option(true, "disable_ponds", "Removes small water and lava ponds from the world.")
    }

    object Misc: ConfigCategory("misc", "Stuff that doesn't belong anywhere else.") {
        var vehiclePickup: Boolean by option(true, "vehicle_pickup", "Allows you to pickup boats and minecarts by shift-clicking.")
        var fertilizeMorePlants: Boolean by option(true, "fertilizer_more_plants", "Allows for Cacti, Sugar Cane, Kelp, and Netherwart to be fertilized by bonemeal or fertilizer.")
        var stuckCommand: Boolean by option(true, "stuck_command", "If you're stuck somewhere, use this command to die and respawn at your spawn point.")
    }
}