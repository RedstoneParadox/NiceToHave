package io.github.redstoneparadox.nicetohave.recipe

import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import com.mojang.serialization.Dynamic
import com.mojang.serialization.JsonOps
import net.minecraft.block.Block
import net.minecraft.nbt.NbtOps
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtString
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.tag.BlockTags
import net.minecraft.tag.Tag
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import io.github.redstoneparadox.nicetohave.item.PaintbrushItem
import io.github.redstoneparadox.nicetohave.tag.NiceToHaveBlockTags
import java.util.*

class PaintbrushRecipe(val predicate: PaintPredicate, val colorMap: Map<DyeColor, Block>, private val id: Identifier): Recipe<PaintbrushItem.PaintbrushInventory> {
    override fun craft(inv: PaintbrushItem.PaintbrushInventory): ItemStack {
        return ItemStack.EMPTY
    }

    override fun getId(): Identifier {
        return id
    }

    override fun getType(): RecipeType<*> {
        return TYPE
    }

    override fun fits(width: Int, height: Int): Boolean {
        return width >= 1 && height >= 1
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return SERIALIZER
    }

    override fun getOutput(): ItemStack {
        return ItemStack(colorMap.values.random())
    }

    override fun matches(inv: PaintbrushItem.PaintbrushInventory, world: World): Boolean {
        return predicate.test(inv.block)
    }

    fun matches(block: Block): Boolean {
        return predicate.test(block)
    }

    fun craft(color: DyeColor): Block? {
        return colorMap[color]
    }

    companion object {
        private val identifier: Identifier = Identifier("nicetohave", "paint")
        val TYPE: RecipeType<PaintbrushRecipe> = Registry.register(Registry.RECIPE_TYPE, identifier, Type())
        val SERIALIZER: RecipeSerializer<PaintbrushRecipe> = RecipeSerializer.register(identifier.toString(), Serializer())
    }

    class Type: RecipeType<PaintbrushRecipe> {
        override fun <C: Inventory> match(recipe: Recipe<C>, world: World, inventory: C): Optional<PaintbrushRecipe> {
            if (inventory is PaintbrushItem.PaintbrushInventory && recipe is PaintbrushRecipe) {
                val block = inventory.block
                val matches = recipe.matches(block)
                if (matches) return Optional.of(recipe)
            }
            return Optional.empty()
        }
    }

    class Serializer: RecipeSerializer<PaintbrushRecipe> {
        override fun write(buf: PacketByteBuf, recipe: PaintbrushRecipe) {
            val nbt = NbtCompound()
            nbt.put("input", recipe.predicate.serialize())
            val result = NbtCompound()
            for (entry in recipe.colorMap) {
                result.putString(entry.key.getName(), Registry.BLOCK.getId(entry.value).toString())
            }
            nbt.put("result", result)
            buf.writeNbt(nbt)
        }

        override fun read(id: Identifier, json: JsonObject): PaintbrushRecipe {
            val nbt = Dynamic.convert(JsonOps.INSTANCE, NbtOps.INSTANCE, json)
            if (nbt !is NbtCompound) throw JsonSyntaxException("Invalid recipe file!")
            return read(id, nbt)
        }

        override fun read(id: Identifier, buf: PacketByteBuf): PaintbrushRecipe {
            val nbt = buf.readNbt()
            if (nbt !is NbtCompound) throw JsonSyntaxException("Didn't find NBT on the packet from the server!")
            return read(id, nbt)
        }

        private fun read(id: Identifier, nbt: NbtCompound): PaintbrushRecipe {
            if (nbt["input"] is NbtCompound && nbt["result"] is NbtCompound) {
                try {
                    val predicate = readInput(nbt["input"] as NbtCompound)
                    val colorMap = readResult(nbt["result"] as NbtCompound)

                    return PaintbrushRecipe(predicate, colorMap, id)
                } catch (e: JsonSyntaxException) {
                    throw JsonSyntaxException("Failed while reading paint recipe $id!", e)
                }
            }

            throw JsonSyntaxException("Failed while reading paint recipe $id!")
        }

        @Throws(JsonSyntaxException::class)
        private fun readInput(nbt: NbtCompound): PaintPredicate {
            if (nbt["block"] is NbtString) {
                val blockID = Identifier(nbt["block"]?.asString())
                return BlockPredicate(blockID)
            }
            else if (nbt["tag"] is NbtString) {
                val tagID = Identifier(nbt["tag"]?.asString())
                return TagPredicate(tagID)
            }

            throw JsonSyntaxException("Paint recipe did not have input.")
        }

        private fun readResult(nbt: NbtCompound): Map<DyeColor, Block> {
            val map = mutableMapOf<DyeColor, Block>()
            for (color in DyeColor.values()) {
                val name = color.getName()
                if (nbt[name] is NbtString) {
                    val blockID = nbt[name]?.asString()
                    val block = Registry.BLOCK[Identifier(blockID)]
                    map[color] = block
                }
            }
            return map
        }
    }

    interface PaintPredicate {
        fun test(block: Block): Boolean

        fun serialize(): NbtCompound
    }

    class TagPredicate(private val tagId: Identifier): PaintPredicate {
        val tag: Tag<Block> by lazy {
            var tag = BlockTags.getTagGroup().getTag(tagId)
            if (tag == null) tag = NiceToHaveBlockTags.getBlockTag(tagId)
            if (tag != null) return@lazy tag
            else throw Exception()
        }

        override fun test(block: Block): Boolean {
            return tag.contains(block)
        }

        override fun serialize(): NbtCompound {
            val nbt = NbtCompound()
            nbt.putString("tag", tagId.toString())
            return nbt
        }
    }

    class BlockPredicate(private val blockId: Identifier): PaintPredicate {
        val block: Block by lazy { Registry.BLOCK[blockId] }

        override fun test(block: Block): Boolean {
            return this.block == block
        }

        override fun serialize(): NbtCompound {
            val nbt = NbtCompound()
            nbt.putString("block", blockId.toString())
            return nbt
        }
    }
}