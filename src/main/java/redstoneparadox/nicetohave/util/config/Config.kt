package redstoneparadox.nicetohave.util.config

import blue.endless.jankson.Jankson
import blue.endless.jankson.JsonObject
import blue.endless.jankson.impl.SyntaxError
import net.fabricmc.loader.api.FabricLoader
import redstoneparadox.nicetohave.NiceToHave
import java.io.File
import java.io.IOException

object Config : ConfigCategory() {

    val boolType = Boolean::class.javaObjectType
    val doubleType = Double::class.javaObjectType
    val intType = Int::class.javaObjectType

    private var hadError = false

    private var isInitialized = false

    object Items : ConfigCategory("items", "Various Items") {
        var chainLink: Boolean by boolOption(true, "chain_link", "Chain links can be used to craft chain mail.")
        var dynamite: Boolean by boolOption(true, "dynamite","Dynamite is a throwable explosive.")
        var wrench: Boolean by boolOption(true, "wrench", "Can be used to rotate blocks.")
        var fertilizer: Boolean by boolOption(true, "fertilizer", "A bonemeal alternative that is obtained from the composter.")
        var sweetBerryJuice: Boolean by boolOption(true, "sweet_berry_juice", "A beverage made from sweet berries.")
        var appleJuice: Boolean by boolOption(true, "apple_juice", "A beverage made from apples.")
    }

    object Blocks : ConfigCategory("blocks", "Various blocks.") {
        var goldButton: Boolean by boolOption(true, "gold_button", "A button that emits a 1-tick Redstone signal when pressed.")
        var analogRedstoneEmitter: Boolean by boolOption(true, "analog_redstone_emitter", "A special redstone block that can be set to output any level of Redstone signal.")
        //var chainLinkFence: Boolean by boolOption(true, "chain_link_fence", )
        var trimmedVines: Boolean by boolOption(true, "trimmed_vine", "Vines can be trimmed by right-clicking with shears, turning them into Trimmed Vines that don't grow.")
        var poles: Boolean by boolOption(true, "poles", "Adds poles made out of logs and stripped logs.")
    }

    object Recipes : ConfigCategory("recipes", "New recipes and tweaks to existing ones.") {
        var increasedRailOutput: Boolean by boolOption(true, "increased_rail_output", "Powered, Detector, and Activator Rail recipes now give 16 instead of 6.")
        var uncraftNetherwartBlock: Boolean by boolOption(true, "uncraft_netherwart_block", "Netherwart Blocks can now be crafted back into netherwarts.")
        var melonToSlices: Boolean by boolOption(true, "melon_to_slices", "Melon blocks can be crafted into 9 melon slices.")
        var glueSlabs: Boolean by boolOption(true, "glue_slabs", "Combining two slabs with a slimeball allows you to convert them back into a full block.")
    }

    object Potions : ConfigCategory("potions", "Potions") {
        var insight: Boolean by boolOption(true, "insight", "Potions of Insight allow you to gain bonus experience.")
        //var nectar: Boolean by boolOption(true, "nectar", )
    }

    object World : ConfigCategory("world", "Various world features") {
        var goldInRivers: Boolean by boolOption(true, "gold_in_rivers", "Randomly adds patches of gold in the rivers of frozen and badlands biomes.")
        var riverGoldPercent: Double by rangeOption(10.0, 0.0, 100.0, "river_gold_percent", "How much of a river gold ore patch is gold.")
        var disablePonds: Boolean by boolOption(true, "disable_ponds", "Removes small water and lava ponds from the world.")
    }

    object Misc : ConfigCategory("misc", "Enable/Disable items.") {
        var dispenserCropPlanting: Boolean by boolOption(true, "dispenser_crop_planting", "Dispensers can plant crops, saplings, and a few other plants.")
        var dispenserLadderPlacement: Boolean by boolOption(true, "dispenser_ladder_placement", "Dispensers can place and pickup ladders and scaffolding.")
        var peacefulBambooJungle: Boolean by boolOption(true, "peaceful_bamboo_jungle", "Makes bamboo jungles peaceful places just like Mushroom Islands.")
        var vehiclePickup: Boolean by boolOption(true, "vehicle_pickup", "Allows you to pickup boats and minecarts by shift-clicking.")
        var underwaterSwitches: Boolean by boolOption(true, "underwater_switches", "Allows for the placement of levers and buttons underwater.")
    }

    fun initialize() {
        if (isInitialized) {
            return;
        }

        Items
        Items.setParent(this)
        Blocks.setParent(this)
        Recipes.setParent(this)
        Potions.setParent(this)
        World.setParent(this)
        Misc.setParent(this)

        val hjsonFile = File(FabricLoader.getInstance().configDirectory, "nicetohave.hjson")
        val json5File = File(FabricLoader.getInstance().configDirectory, "nicetohave.json5")

        var configObject = JsonObject()

        try {
            configObject = Jankson
                    .builder()
                    .build()
                    .load(if (json5File.exists()) json5File else hjsonFile)
        } catch (e : IOException) {
            NiceToHave.out("Couldn't find config file; all config values will be set to default and a new file will be created.")
        } catch (e : SyntaxError) {
            NiceToHave.error("Couldn't read the config file due to an hjson syntax error. Please fix the file or delete it to generate a new one. (Default config values will be used in the meantime).")
            e.message?.let { NiceToHave.error(it) }
            NiceToHave.error(e.lineMessage)
            hadError = true
        }

        if (hjsonFile.exists()) {
            NiceToHave.out("Nice to Have now uses JSON5 as the config format for better editor support. Please delete the hjson file as all files will now be written to the json5 file.")
        }

        deserialize(configObject)

        save()

        wasInitialized = true;
    }

    private fun save() {
        if (!hadError) {
            val configString = serialize().toJson(true, true)
            //File(FabricLoader.INSTANCE.configDirectory, "nicetohave.json5").bufferedWriter().use { it.write(configString) }
        }
    }

    fun stringifyType(type : Class<*>): String = when (type) {
        boolType -> "boolean"
        doubleType -> "double"
        intType -> "integer"
        else -> "unknown"
    }

}