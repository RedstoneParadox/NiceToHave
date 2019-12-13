package redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonArray
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import java.io.File

class LootTableBuilder {

    private var id: String = ""
    private var namespace: String = "nicetohave"
    private var condition : DataConditionBuilder? = null
    private var type : LootType = LootType.GENERIC
    private val pools : ArrayList<PoolBuilder> = arrayListOf()

    // Directory
    private val currentDirectory: File?
        get() = File("C:\\Development\\Minecraft\\Mods\\NiceToHave\\src\\main\\resources\\data\\$namespace\\loot_tables\\${type.directory}")

    fun setID(id: String): LootTableBuilder {
        this.id = id
        return this
    }

    fun setCondition(conditionBuilder: DataConditionBuilder): LootTableBuilder {
        condition = conditionBuilder
        return this
    }

    fun setType(t : LootType): LootTableBuilder {
        type = t
        return this
    }

    fun setNamespace(namespace: String): LootTableBuilder {
        this.namespace = namespace
        return this
    }

    fun addPool(pool: PoolBuilder): LootTableBuilder {
        pools.add(pool)
        return this
    }

    fun save() {
        val root = JsonObject()
        root["type"] = JsonPrimitive(type.id)

        val poolsArray = JsonArray()
        for (pool in pools) {
            poolsArray.add(pool.build())
        }
        root["pools"] = poolsArray

        val lootTableString = root.toJson(false, true)
        val file = File(currentDirectory, "$id.json")
        file.writeText(lootTableString)

        if (condition != null) {
            val conditionString = condition!!.build().toJson(false, true)
            val metaFile = File(currentDirectory, "$id.json.mcmeta")
            metaFile.writeText(conditionString)
        }
    }

    class PoolBuilder {
        private var roles: Int = 1
        private val entries: ArrayList<EntryBuilder> = arrayListOf()
        private val conditions: ArrayList<ConditionBuilder> = arrayListOf()

        fun setRoles(count: Int): PoolBuilder {
            roles = count
            return this
        }

        fun addEntry(builder: EntryBuilder): PoolBuilder {
            entries.add(builder)
            return this
        }

        fun addCondition(builder: ConditionBuilder): PoolBuilder {
            conditions.add(builder)
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

            if (conditions.isNotEmpty()) {
                val conditionsJson = JsonArray()
                for (condition in conditions) {
                    conditionsJson.add(condition.build())
                }
                poolJson["conditions"] = conditionsJson
            }

            return poolJson
        }
    }

    class EntryBuilder {
        private var type : EntryType = EntryType.ITEM
        private var name : String = ""
        private val conditions: ArrayList<ConditionBuilder> = arrayListOf()

        fun setType(type: EntryType): EntryBuilder {
            this.type = type
            return this
        }

        fun setName(name: String): EntryBuilder {
            this.name = name
            return this
        }

        fun build(): JsonObject {
            val entryJson = JsonObject()
            entryJson["type"] = JsonPrimitive(type.id)
            entryJson["name"] = JsonPrimitive(name)

            if (conditions.isNotEmpty()) {
                val conditionsJson = JsonArray()
                for (condition in conditions) {
                    conditionsJson.add(condition.build())
                }
                entryJson["conditions"] = conditionsJson
            }

            return entryJson
        }
    }

    class ConditionBuilder {
        var conditions : String = ""

        fun setCondition(id: String): ConditionBuilder {
            this.conditions = id
            return this
        }

        fun build(): JsonObject {
            val conditionJson = JsonObject()
            conditionJson["condition"] = JsonPrimitive(conditions)
            // Other code here
            return conditionJson
        }
    }

    enum class LootType(val id : String, val directory : String) {
        BLOCK("minecraft:block", "blocks"),
        GENERIC("minecraft:generic", "generic");
    }

    enum class EntryType(val id: String) {
        ITEM("minecraft:item")
    }

    companion object {

        fun generatePoleDrop(poleId: String, woodNamespace: String = "minecraft") {
            val loadCondition = DataConditionBuilder()
                    .addObjectCondition("pconfig:option", "config" to "nicetohave:config.json", "option" to "blocks.poles", "value" to true)

            if (woodNamespace != "minecraft" && woodNamespace != "nicetohave") loadCondition.addCondition("libcd:mod_loaded", woodNamespace)

            LootTableBuilder()
                    .setID(poleId)
                    .setType(LootType.BLOCK)
                    .addPool(PoolBuilder()
                            .setRoles(1)
                            .addEntry(EntryBuilder()
                                    .setName("nicetohave:$poleId"))
                            .addCondition(ConditionBuilder()
                                    .setCondition("minecraft:survives_explosion")
                            )
                    )
                    .setCondition(loadCondition)
                    .save()
        }
    }
}