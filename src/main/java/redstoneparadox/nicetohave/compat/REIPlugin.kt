package redstoneparadox.nicetohave.compat

import me.shedaniel.math.api.Point
import me.shedaniel.math.api.Rectangle
import me.shedaniel.rei.api.EntryStack
import me.shedaniel.rei.api.RecipeCategory
import me.shedaniel.rei.api.RecipeDisplay
import me.shedaniel.rei.api.RecipeHelper
import me.shedaniel.rei.api.plugins.REIPluginV0
import me.shedaniel.rei.gui.widget.EntryWidget
import me.shedaniel.rei.gui.widget.RecipeBaseWidget
import me.shedaniel.rei.gui.widget.Widget
import net.minecraft.block.Block
import net.minecraft.client.MinecraftClient
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import redstoneparadox.nicetohave.item.NiceToHaveItems
import redstoneparadox.nicetohave.recipe.PaintbrushRecipe
import java.util.function.Supplier

@Suppress("unused")
object REIPlugin: REIPluginV0 {
    val ID: Identifier = Identifier("nicetohave", "nicetohave")
    val PAINTING: Identifier = Identifier("nicetohave", "plugins/painting")

    private val DISPLAY_TEXTURE = Identifier("roughlyenoughitems", "textures/gui/display.png")
    private val DISPLAY_TEXTURE_DARK = Identifier("roughlyenoughitems", "textures/gui/display_dark.png")

    override fun getPluginIdentifier(): Identifier {
        return ID
    }

    override fun registerPluginCategories(recipeHelper: RecipeHelper) {
        recipeHelper.registerCategory(PaintingCategory())
    }

    override fun registerRecipeDisplays(recipeHelper: RecipeHelper) {
        val recipes = recipeHelper.recipeManager.values()

        for (recipe in recipes.filterIsInstance<PaintbrushRecipe>()) {
            val inputs: Collection<Block> = when (recipe.predicate) {
                is PaintbrushRecipe.TagPredicate -> recipe.predicate.tag.values()
                is PaintbrushRecipe.BlockPredicate -> listOf(recipe.predicate.block)
                else -> throw Exception("Huh?")
            }

            for (entry in recipe.colorMap) {
                val dye = entry.key
                val output = entry.value
                recipeHelper.registerDisplay(PAINTING, PaintingDisplay(inputs, dye, EntryStack.create(output)))
            }
        }
    }

    class PaintingDisplay(val inputs: Collection<Block>, val dye: DyeColor, val output: EntryStack): RecipeDisplay {

        override fun getRecipeCategory(): Identifier {
            return PAINTING
        }

        override fun getInputEntries(): MutableList<MutableList<EntryStack>> {
            val entries = mutableListOf<EntryStack>()

            for (block in inputs) {
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

        override fun setupDisplay(recipeDisplaySupplier: Supplier<PaintingDisplay>, bounds: Rectangle): MutableList<Widget> {
            val startPoint = Point(bounds.centerX - 41, bounds.centerY - 13)
            val widgets = mutableListOf<Widget>()
            val display = recipeDisplaySupplier.get()

            widgets.add(object: RecipeBaseWidget(bounds) {
                override fun render() {
                    super.render()
                    MinecraftClient.getInstance().textureManager.bindTexture(DISPLAY_TEXTURE)
                    blit(startPoint.x, startPoint.y, 0, 221, 82, 26)
                }
            })
            widgets.add(EntryWidget.create(startPoint.x + 4, startPoint.y + 5).entries(display.inputEntries[0]).noBackground())
            widgets.add(EntryWidget.create(startPoint.x + 61, startPoint.y + 5).entry(display.output).noBackground())
            
            return widgets
        }

        override fun getDisplayHeight(): Int {
            return 36
        }
    }
}