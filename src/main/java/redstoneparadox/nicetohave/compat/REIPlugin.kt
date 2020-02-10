package redstoneparadox.nicetohave.compat

import me.shedaniel.math.api.Rectangle
import me.shedaniel.rei.api.*
import me.shedaniel.rei.api.plugins.REIPluginV0
import me.shedaniel.rei.gui.widget.Widget
import net.minecraft.block.Block
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier
import redstoneparadox.nicetohave.item.NiceToHaveItems
import java.util.function.Supplier

object REIPlugin: REIPluginV0 {
    val ID: Identifier = Identifier("nicetohave", "nicetohave")
    val PAINTING: Identifier = Identifier("nicetohave", "plugins/painting")

    override fun getPluginIdentifier(): Identifier {
        return ID
    }

    override fun registerPluginCategories(recipeHelper: RecipeHelper) {
        recipeHelper.registerCategory(PaintingCategory())
    }

    override fun registerRecipeDisplays(recipeHelper: RecipeHelper) {

    }

    class PaintingDisplay(val input: Tag<Block>, val output: EntryStack): RecipeDisplay {

        override fun getRecipeCategory(): Identifier {
            return PAINTING
        }

        override fun getInputEntries(): MutableList<MutableList<EntryStack>> {
            val blocks = input.values()
            val entries = mutableListOf<EntryStack>()

            for (block in blocks) {
                val stack = EntryStack.create(block)
                entries.add(stack)
            }

            return mutableListOf(entries)
        }

        override fun getOutputEntries(): MutableList<EntryStack> {
            return mutableListOf(output)
        }

        override fun getRequiredEntries(): MutableList<MutableList<EntryStack>> {
            return inputEntries
        }
    }

    class PaintingCategory: RecipeCategory<PaintingDisplay> {
        override fun getIdentifier(): Identifier {
            return PAINTING
        }

        override fun getLogo(): EntryStack {
            return EntryStack.create(NiceToHaveItems.PAINT_BRUSH)
        }

        override fun getCategoryName(): String {
            return "category.nicetohave.painting"
        }

        override fun setupDisplay(recipeDisplaySupplier: Supplier<PaintingDisplay>?, bounds: Rectangle?): MutableList<Widget> {
            return super.setupDisplay(recipeDisplaySupplier, bounds)
        }

        override fun getDisplayHeight(): Int {
            return 36
        }
    }
}