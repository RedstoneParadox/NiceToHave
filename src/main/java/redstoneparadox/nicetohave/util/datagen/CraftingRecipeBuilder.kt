package redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonArray
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import net.fabricmc.loader.api.FabricLoader
import redstoneparadox.nicetohave.NiceToHave
import java.io.File
import kotlin.math.min

class CraftingRecipeBuilder {

    // Directory
    private var currentDirectory: File? = if (FabricLoader.getInstance().isDevelopmentEnvironment) {
        File(FabricLoader.getInstance().gameDirectory.parentFile, "..\\src\\main\\resources\\data\\nicetohave\\recipes")
    } else {
        null
    }

    // Recipe ID
    private var recipeID : String = ""

    // Pattern
    private var firstLine : String = ""
    private var secondLine : String = ""
    private var thirdLine : String = ""

    //Ingredients
    private val ingredients : ArrayList<String> = ArrayList()
    private val keyCharacters : List<String> = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i")

    // Output
    private var output : String = ""
    private var count : Int = 0

    //Condition
    private var condition : DataConditionBuilder? = null

    fun setNamespace(namespace : String): CraftingRecipeBuilder {
        currentDirectory = if (FabricLoader.getInstance().isDevelopmentEnvironment) {
            File(FabricLoader.getInstance().gameDirectory.parentFile, "..\\src\\main\\resources\\data\\$namespace\\recipes")
        } else {
            null
        }
        return this
    }

    fun setID(string: String): CraftingRecipeBuilder {
        recipeID = string
        return this
    }

    fun setOutput(id : String, count : Int, isAlsoName : Boolean = false): CraftingRecipeBuilder {
        output = id
        this.count = count
        if (isAlsoName) {
            recipeID = id.split(":").last()
        }
        return this
    }

    fun setOutput(id : String, isAlsoName: Boolean = false): CraftingRecipeBuilder {
        return setOutput(id, count, isAlsoName)
    }

    fun setPatternLine(pattern : String, line : Int): CraftingRecipeBuilder {
        when(line) {
            1 -> firstLine = pattern
            2 -> secondLine = pattern
            3 -> thirdLine = pattern
            else -> NiceToHave.error("Recipes only have 3 lines!")
        }
        return this
    }

    fun setIngredients(vararg ids: String): CraftingRecipeBuilder {
        if (ids.size > 9) {
            NiceToHave.warn("Crafting recipes can't have more than 9 ingredients. Any ingredients past the first 9 will be ignored.")
        }
        ingredients.clear()
        ingredients.addAll(ids)
        return this
    }

    fun setCondition(condition : DataConditionBuilder): CraftingRecipeBuilder {
        this.condition = condition
        return this
    }

    fun save() {
        if (currentDirectory == null) {
            NiceToHave.warn("Attempted to generate data outside of the dev environment.")
            return
        }

        val rootObject = JsonObject()

        rootObject["type"] = JsonPrimitive("minecraft:crafting_shaped")
        val patternArray = JsonArray()
        if (firstLine.isNotEmpty()) {
            patternArray.add(JsonPrimitive(firstLine))
            if (secondLine.isNotEmpty()) {
                patternArray.add(JsonPrimitive(secondLine))
                if (thirdLine.isNotEmpty()) {
                    patternArray.add(JsonPrimitive(thirdLine))
                }
            }
        }
        rootObject["pattern"] = patternArray

        val keyObject = JsonObject()
        for (i in 0 until min(9, ingredients.size)) {
            val keyCharacter = keyCharacters[i]
            val ingredientID = ingredients[i]
            val ingredientObject = JsonObject()
            ingredientObject["item"] = JsonPrimitive(ingredientID)
            keyObject[keyCharacter] = ingredientObject
        }
        rootObject["key"] = keyObject

        val resultObject = JsonObject()
        resultObject["item"] = JsonPrimitive(output)
        resultObject["count"] = JsonPrimitive(count)
        rootObject["result"] = resultObject

        val recipeString = rootObject.toJson(false, true)
        File(currentDirectory, "$recipeID.json").bufferedWriter().use { it.write(recipeString) }

        if (condition != null) {
            val conditionString = condition!!.build().toJson(false, true)
            File(currentDirectory, "$recipeID.json.mcmeta").bufferedWriter().use { it.write(conditionString) }
        }
    }

    companion object {
        private val POLE_RECIPE = CraftingRecipeBuilder()
                .setPatternLine("a", 1)
                .setPatternLine("a", 2)
                .setPatternLine("a", 3)
                .setOutput("", 12)
                .setCondition(DataConditionBuilder()
                        .addCondition("nicetohave:config_true", "blocks.poles"))

        fun generatePoleRecipe(woodPrefix : String, logModId : String = "minecraft") {
            POLE_RECIPE
                    .setIngredients("$logModId:${woodPrefix}_log")
                    .setOutput("nicetohave:${woodPrefix}_pole", true)
                    .save()
        }
    }
}