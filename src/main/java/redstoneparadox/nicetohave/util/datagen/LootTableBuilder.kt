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

    fun addPool(pool: PoolBuilder): LootTableBuilder {
        pools.add(pool)
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
        for (pool in pools) {
            poolsArray.add(pool.build())
        }
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
        private var roles: Int = 1
        private val entries: ArrayList<EntryBuilder> = arrayListOf()

        fun setRoles(count: Int): PoolBuilder {
            roles = count
            return this
        }

        fun addEntry(builder: EntryBuilder): PoolBuilder {
            entries.add(builder)
            return this
        }

        fun build(): JsonObject {
            val poolJson = JsonObject()
            poolJson["rolls"] = JsonPrimitive(roles)

            val entriesJson = JsonArray()
            for (entry in entries) {
                entriesJson.add(entry.build())
            }
            poolJson["entries"] = entriesJson

            val conditionsJson = JsonArray()
            //Code for conditions
            poolJson["conditions"] = conditionsJson

            return poolJson
        }
    }

    class EntryBuilder {
        private var type : EntryType = EntryType.ITEM
        private var name : String = ""

        fun setType(type: EntryType): EntryBuilder {
            this.type = type
            return this
        }

        fun setName(name: String): EntryBuilder {
            this.name = name
            return this
        }

        fun build(): JsonObject {
            val entry = JsonObject()
            entry["type"] = JsonPrimitive(type)
            entry["name"] = JsonPrimitive(name)
            return entry
        }
    }

    enum class EntryType(val id: String) {
        ITEM("minecraft:item")
    }
}