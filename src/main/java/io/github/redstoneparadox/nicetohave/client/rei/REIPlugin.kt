package io.github.redstoneparadox.nicetohave.client.rei

import me.shedaniel.math.Point
import me.shedaniel.math.Rectangle
import net.minecraft.block.Block
import net.minecraft.client.resource.language.I18n
import net.minecraft.item.DyeItem
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import io.github.redstoneparadox.nicetohave.config.Config
import io.github.redstoneparadox.nicetohave.item.NiceToHaveItems
import io.github.redstoneparadox.nicetohave.recipe.PaintbrushRecipe
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.display.DisplayCategory
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.Display
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.entry.EntryStack
import me.shedaniel.rei.api.common.util.EntryStacks
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText

@Suppress("unused")
object REIPlugin: REIClientPlugin {
    val ID: Identifier = Identifier("nicetohave", "nicetohave")
    val PAINTING: CategoryIdentifier<PaintingDisplay> = CategoryIdentifier.of("nicetohave", "plugins/painting")

    private val DISPLAY_TEXTURE = Identifier("nicetohave", "textures/gui/rei_gui.png")
    private val DISPLAY_TEXTURE_DARK = Identifier("roughlyenoughitems", "textures/gui/display_dark.png")

    override fun registerCategories(registry: CategoryRegistry) {
        if (Config.Items.paintBrush) registry.add(
            PaintingCategory()
        )
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        if (!Config.Items.paintBrush) return

        val recipes = registry.recipeManager.values()

        for (recipe in recipes.filterIsInstance<PaintbrushRecipe>()) {
            val inputs: Collection<Block> = when (recipe.predicate) {
                is PaintbrushRecipe.TagPredicate -> recipe.predicate.tag.values()
                is PaintbrushRecipe.BlockPredicate -> listOf(recipe.predicate.block)
                else -> throw Exception("Huh?")
            }

            for (entry in recipe.colorMap) {
                val dye = entry.key
                val output = entry.value
                registry.add(PaintingDisplay(inputs, dye, EntryStacks.of(output)))
            }
        }
    }

    class PaintingDisplay(private val inputs: Collection<Block>, val dye: DyeColor, val output: EntryStack<*>): Display {
        override fun getCategoryIdentifier(): CategoryIdentifier<*> {
            return PAINTING
        }

        override fun getInputEntries(): MutableList<EntryIngredient> {
            val entries = mutableListOf<EntryIngredient>()

            for (block in inputs) {
                val stack = EntryStacks.of(block)
                entries.add(EntryIngredient.of(stack))
            }

            return entries
        }

        override fun getOutputEntries(): MutableList<EntryIngredient> {
            return mutableListOf(EntryIngredient.of(output))
        }

        override fun getRequiredEntries(): MutableList<EntryIngredient> {
            return inputEntries
        }
    }

    class PaintingCategory: DisplayCategory<PaintingDisplay> {
        override fun getIcon(): Renderer {
            return EntryStacks.of(NiceToHaveItems.PAINT_BRUSH)
        }

        override fun getTitle(): Text {
            return TranslatableText("category.nicetohave.painting")
        }

        override fun getCategoryIdentifier(): CategoryIdentifier<out PaintingDisplay> {
            return PAINTING
        }

        override fun setupDisplay(recipeDisplay: PaintingDisplay, bounds: Rectangle): MutableList<Widget> {
            val startPoint = Point(bounds.centerX - 51, bounds.centerY - 13)
            val widgets = mutableListOf<Widget>()

            widgets.add(Widgets.createRecipeBase(bounds))
            widgets.add(Widgets.createTexturedWidget(DISPLAY_TEXTURE, startPoint.x, startPoint.y, 102, 26))

            widgets.add(Widgets.createSlot(Point(startPoint.x + 4, startPoint.y + 5)).entries(recipeDisplay.inputEntries[0]).backgroundEnabled(false))
            widgets.add(Widgets.createSlot(Point(startPoint.x + 24, startPoint.y + 5)).entries(getDyeItems(recipeDisplay.dye)).backgroundEnabled(false))
            widgets.add(Widgets.createSlot(Point(startPoint.x + 81, startPoint.y + 5)).entry(recipeDisplay.output).backgroundEnabled(false))

            return widgets
        }

        private fun getDyeItems(dye: DyeColor): List<EntryStack<*>> {
            return Registry.ITEM.filter { it is DyeItem && it.color == dye }.map { EntryStacks.of(it) }
        }

        override fun getDisplayHeight(): Int {
            return 36
        }
    }
}