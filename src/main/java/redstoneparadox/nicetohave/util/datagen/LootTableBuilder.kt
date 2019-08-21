package redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonArray
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import net.fabricmc.loader.api.FabricLoader
import redstoneparadox.nicetohave.NiceToHave
import java.io.File

class LootTableBuilder {

    private var id = ""

    private var condition : DataConditionBuilder? = null

    private var type : Type = Type.BLOCK

    // Directory
    private var currentDirectory: File? = if (FabricLoader.getInstance().isDevelopmentEnvironment) {
        File(FabricLoader.getInstance().gameDirectory.parentFile, "..\\src\\main\\resources\\data\\nicetohave\\loot_tables\\${type.directory}")
    } else {
        null
    }

    private val pools : ArrayList<PoolBuilder> = arrayListOf()

    fun setCondition(conditionBuilder: DataConditionBuilder): LootTableBuilder {
        condition = conditionBuilder
        return this
    }

    fun setType(t : Type): LootTableBuilder {
        type = t
        return this
    }

    fun save() {
        if (currentDirectory == null) {
            NiceToHave.warn("Attempted to generate data outside of the dev environment.")
            return
        }

        val root = JsonObject()
        root["type"] = JsonPrimitive(type.id)

        val poolsArray = JsonArray()
        //Code to put pools into JsonArray
        root["pools"] = poolsArray

        val lootTableString = root.toJson(false, true)
        File(currentDirectory, "$id.json").bufferedWriter().use { it.write(lootTableString) }

        if (condition != null) {
            val conditionString = condition!!.build().toJson(false, true)
            File(currentDirectory, "$id.json.mcmeta").bufferedWriter().use { it.write(conditionString) }
        }
    }

    enum class Type(val id : String, val directory : String) {
        BLOCK("minecraft:block", "blocks");
    }

    class PoolBuilder {

    }
}