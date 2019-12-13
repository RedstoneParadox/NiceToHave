package redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import java.io.File

class VariantBlockStateBuilder {

    private var id: String = ""
    private var namespace: String = "nicetohave"
    private val variants: HashMap<String, VariantModel> = HashMap()

    // Directory
    private val currentDirectory: File?
        get() = File("", "..\\src\\main\\resources\\assets\\$namespace\\blockstates")

    fun setID(id: String): VariantBlockStateBuilder {
        this.id = id
        return this
    }

    fun setNamespace(namespace: String): VariantBlockStateBuilder {
        this.namespace = namespace
        return this
    }

    fun addVariant(variantString: String, model: VariantModel): VariantBlockStateBuilder {
        variants[variantString] = model
        return this
    }

    fun save() {
        val blockStateJson = JsonObject()
        val variantsJson = JsonObject()
        for (variantsEntry in variants) {
            variantsJson[variantsEntry.key] = variantsEntry.value.toJson()
        }
        blockStateJson["variants"] = variantsJson

        val blockStateString = blockStateJson.toJson(false, true)
        File(currentDirectory, "$id.json").bufferedWriter().use { it.write(blockStateString) }
    }

    class VariantModel(val model: String, val x: Int = 0, val y: Int = 0, val z : Int = 0) {

        fun toJson(): JsonObject {
            val variantJson = JsonObject()
            variantJson["model"] = JsonPrimitive(model)

            if (x != 0) {
                variantJson["x"] = JsonPrimitive(x)
            }

            if (y != 0) {
                variantJson["y"] = JsonPrimitive(y)
            }

            if (z != 0) {
                variantJson["z"] = JsonPrimitive(0)
            }

            return variantJson
        }
    }

    companion object {

        fun generatePoleBlockState(woodSuffix: String) {
            val fullID = "${woodSuffix}_pole"
            val modelString = "nicetohave:block/$fullID"

            VariantBlockStateBuilder()
                    .setID(fullID)
                    .addVariant("axis=y", VariantModel(modelString))
                    .addVariant("axis=z", VariantModel(modelString, 90))
                    .addVariant("axis=x", VariantModel(modelString, 90, 90))
                    .save()
        }
    }
}