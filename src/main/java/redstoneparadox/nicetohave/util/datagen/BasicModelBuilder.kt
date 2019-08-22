package redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import net.fabricmc.loader.api.FabricLoader
import redstoneparadox.nicetohave.NiceToHave
import java.io.File

class BasicModelBuilder {

    private var id: String = ""
    private var namespace: String = "nicetohave"
    private var type: ModelType = ModelType.BLOCK
    private var parent: String = ""
    private val textures: HashMap<String, String> = HashMap()

    // Directory
    private val currentDirectory: File?
        get() = if (FabricLoader.getInstance().isDevelopmentEnvironment) {
            File(FabricLoader.getInstance().gameDirectory.parentFile, "..\\src\\main\\resources\\assets\\$namespace\\models\\${type.directory}")
        } else {
            null
        }

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
        if (currentDirectory == null) {
            NiceToHave.warn("Attempted to generate data outside of dev environment.")
            return
        }

        val modelJson = JsonObject()
        if (parent.isNotEmpty()) {
            modelJson["parent"] = JsonPrimitive(parent)
        }

        val texturesJson = JsonObject()
        for (textureEntry in textures) {
            texturesJson[textureEntry.key] = JsonPrimitive(textureEntry.value)
        }
        modelJson["textures"] = texturesJson

        val modelString = modelJson.toJson(false, true)
        File(currentDirectory, "$id.json").bufferedWriter().use { it.write(modelString) }
    }

    enum class ModelType(val directory : String) {
        BLOCK("block")
    }

    companion object {
        private val POLE_MODEL = BasicModelBuilder()
                .setParent("nicetohave:block/pole")

        fun createPoleModel(woodPrefix: String, woodNamespace: String = "minecraft") {
            POLE_MODEL
                    .setID("${woodPrefix}_pole")
                    .addTexture("texture", "$woodNamespace:block/${woodPrefix}_log")
                    .save()
        }
    }
}