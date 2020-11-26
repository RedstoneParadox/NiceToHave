package io.github.redstoneparadox.nicetohave.util.datagen

import blue.endless.jankson.JsonArray
import blue.endless.jankson.JsonObject
import blue.endless.jankson.JsonPrimitive
import java.io.File
import kotlin.math.min

class CraftingRecipeBuilder {

    var namespace: String = "nicetohave"

    // Directory
    private val currentDirectory: File?
        get() = File("C:\\Development\\Minecraft\\Mods\\NiceToHave\\src\\main\\resources\\data\\$namespace\\recipes")

    // Recipe ID
    private var id : String = ""

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

    fun setNamespace(namespace: String): CraftingRecipeBuilder {
        this.namespace = namespace
        return this
    }

    fun setID(string: String): CraftingRecipeBuilder {
        id = string
        return this
    }

    fun setOutput(id : String, count : Int, isAlsoName : Boolean = false): CraftingRecipeBuilder {
        output = id
        this.count = count
        if (isAlsoName) {
            this.id = id.split(":").last()
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
            else -> println("Recipes only have 3 lines!")
        }
        return this
    }

    fun setIngredients(vararg ids: String): CraftingRecipeBuilder {
        if (ids.size > 9) {
            println("Crafting recipes can't have more than 9 ingredients. Any ingredients past the first 9 will be ignored.")
        }
        ingredients.clear()
        ingredients.addAll(ids)
        return this
    }

    fun save() {
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

        val file = File(currentDirectory, "$id.json")
        file.writeText(recipeString)
    }

    companion object {
        private val POLE_RECIPE = CraftingRecipeBuilder()
                .setPatternLine("a", 1)
                .setPatternLine("a", 2)
                .setPatternLine("a", 3)
                .setOutput("", 12)

        private val SLAB_GLUE_RECIPE = CraftingRecipeBuilder()
                .setPatternLine("a", 1)
                .setPatternLine("b", 2)
                .setPatternLine("a", 3)
                .setOutput("", 1)

        fun generatePoleRecipe(woodPrefix: String, logModId: String = "minecraft", logSuffix: String = "log") {
            POLE_RECIPE
                    .setIngredients("$logModId:${woodPrefix}_$logSuffix")
                    .setOutput("nicetohave:${woodPrefix}_pole", true)
                    .save()
        }


        // Generates recipe for gluing slabs back into planks.
        fun woodSlabGlueRecipe(prefix: String, mod: String = "minecraft") {
            slabGlueRecipe("${prefix}_slab", "${prefix}_planks", mod)
        }

        fun regularSlabGlueRecipe(block: String, plural : Boolean = false, mod: String = "minecraft") {
            val prefix = if (plural) block.removeSuffix("s") else block

            slabGlueRecipe("${prefix}_slab", block, mod)
        }

        fun slabGlueRecipe(slabID: String, blockID: String, mod: String) {
            SLAB_GLUE_RECIPE
                    .setIngredients("$mod:$slabID", "minecraft:slime_ball")
                    .setOutput("$mod:$blockID")
                    .setID("glue_$slabID")
                    .save()
        }
    }
}