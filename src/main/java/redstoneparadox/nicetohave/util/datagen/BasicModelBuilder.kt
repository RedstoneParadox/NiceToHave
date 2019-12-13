package redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import java.io.File

class BasicModelBuilder {

    private var id: String = ""
    private var namespace: String = "nicetohave"
    private var type: ModelType = ModelType.BLOCK
    private var parent: String = ""
    private val textures: HashMap<String, String> = HashMap()

    // Directory
    private val currentDirectory: File?
        get() = File("C:\\Development\\Minecraft\\Mods\\NiceToHave\\src\\main\\resources\\assets\\$namespace\\models\\${type.directory}")

    fun setID(id: String): BasicModelBuilder {
        this.id = id
        return this
    }

    fun setNamespace(namespace: String): BasicModelBuilder {
        this.namespace = namespace
        return this
    }

    fun setType(type: ModelType): BasicModelBuilder {
        this.type = type
        return this
    }

    fun setParent(parent: String): BasicModelBuilder {
        this.parent = parent
        return this
    }

    fun addTexture(layer: String, texture: String): BasicModelBuilder {
        textures.put(layer, texture)
        return this
    }

    fun save() {
        val modelJson = JsonObject()
        if (parent.isNotEmpty()) {
            modelJson["parent"] = JsonPrimitive(parent)
        }

        if (textures.isNotEmpty()) {
            val texturesJson = JsonObject()
            for (textureEntry in textures) {
                texturesJson[textureEntry.key] = JsonPrimitive(textureEntry.value)
            }
            modelJson["textures"] = texturesJson
        }

        val modelString = modelJson.toJson(false, true)
        File(currentDirectory, "$id.json").writeText(modelString)
    }

    enum class ModelType(val directory : String) {
        BLOCK("block"),
        ITEM("item");
    }

    companion object {
        private val POLE_BLOCK_MODEL = BasicModelBuilder()
                .setParent("nicetohave:block/pole")
        private val POLE_ITEM_MODEL = BasicModelBuilder()
                .setType(ModelType.ITEM)

        fun createPoleBlockModel(woodPrefix: String, woodNamespace: String = "minecraft") {
            POLE_BLOCK_MODEL
                    .setID("${woodPrefix}_pole")
                    .addTexture("texture", "$woodNamespace:block/${woodPrefix}_log")
                    .save()
        }

        fun createPoleItemModel(woodPrefix: String) {
            POLE_ITEM_MODEL
                    .setID("${woodPrefix}_pole")
                    .setParent("nicetohave:block/${woodPrefix}_pole")
                    .save()
        }
    }
}