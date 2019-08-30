package redstoneparadox.nicetohave.item

import net.minecraft.item.BlockItem
import net.minecraft.item.BoneMealItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import redstoneparadox.nicetohave.block.Blocks
import redstoneparadox.nicetohave.item.wrench.WrenchItem
import redstoneparadox.nicetohave.util.config.Config
import redstoneparadox.nicetohave.util.initializers.ItemsInitializer

/**
 * Created by RedstoneParadox on 5/23/2019.
 */
object Items : ItemsInitializer() {

    var CHAIN_LINK : Item? = register("chain_link", Item(Item.Settings().group(ItemGroup.MATERIALS)), Config.Items.chainLink)
    var DYNAMITE : Item? = register("dynamite", DynamiteItem(), Config.Items.dynamite)
    var WRENCH : Item? = register("wrench", WrenchItem(), Config.Items.wrench)
    var FERTILIZER : Item? = register("fertilizer", BoneMealItem(Item.Settings().group(ItemGroup.MATERIALS)), Config.Items.fertilizer)

    //Food
    var SWEET_BERRY_JUICE : Item? = register("sweet_berry_juice", DrinkItem(FoodComponents.JUICE), Config.Items.sweetBerryJuice)
    var APPLE_JUICE : Item? = register("apple_juice", DrinkItem(FoodComponents.JUICE), Config.Items.appleJuice)

    //BlockItems.
    var GOLD_BUTTON : BlockItem? = registerBlockItem("gold_button", Blocks.GOLD_BUTTON, Item.Settings().group(ItemGroup.REDSTONE))
    var ANALOG_REDSTONE_EMITTER: BlockItem? = registerBlockItem("analog_redstone_emitter", Blocks.ANALOG_REDSTONE_EMITTER, Item.Settings().group(ItemGroup.REDSTONE))
    var DIRT_GOLD_ORE: BlockItem? = registerBlockItem("dirt_gold_ore", Blocks.DIRT_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    var SAND_GOLD_ORE: BlockItem? = registerBlockItem("sand_gold_ore", Blocks.SAND_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    var GRAVEL_GOLD_ORE: BlockItem? = registerBlockItem("gravel_gold_ore", Blocks.GRAVEL_GOLD_ORE, Item.Settings().group(ItemGroup.BUILDING_BLOCKS))
    var CHAIN_LINK_FENCE: BlockItem? = registerBlockItem("chain_link_fence", Blocks.CHAIN_LINK_FENCE, Item.Settings().group(ItemGroup.DECORATIONS))

    var OAK_POLE : BlockItem? = registerWoodPoleItem("oak", Blocks.OAK_POLE)
    var SPRUCE_POLE : BlockItem? = registerWoodPoleItem("spruce", Blocks.SPRUCE_POLE)
    var BIRCH_POLE : BlockItem? = registerWoodPoleItem("birch", Blocks.BIRCH_POLE)
    var JUNGLE_POLE : BlockItem? = registerWoodPoleItem("jungle", Blocks.JUNGLE_POLE)
    var ACACIA_POLE : BlockItem? = registerWoodPoleItem("acacia", Blocks.ACACIA_POLE)
    var DARK_OAK_POLE : BlockItem? = registerWoodPoleItem("dark_oak", Blocks.DARK_OAK_POLE)

    var STRIPPED_OAK_POLE : BlockItem? = registerWoodPoleItem("stripped_oak", Blocks.STRIPPED_OAK_POLE)
    var STRIPPED_SPRUCE_POLE : BlockItem? = registerWoodPoleItem("stripped_spruce", Blocks.STRIPPED_SPRUCE_POLE)
    var STRIPPED_BIRCH_POLE : BlockItem? = registerWoodPoleItem("stripped_birch", Blocks.STRIPPED_BIRCH_POLE)
    var STRIPPED_JUNGLE_POLE : BlockItem? = registerWoodPoleItem("stripped_jungle", Blocks.STRIPPED_JUNGLE_POLE)
    var STRIPPED_ACACIA_POLE : BlockItem? = registerWoodPoleItem("stripped_acacia", Blocks.STRIPPED_ACACIA_POLE)
    var STRIPPED_DARK_OAK_POLE : BlockItem? = registerWoodPoleItem("stripped_dark_oak", Blocks.STRIPPED_DARK_OAK_POLE)

    fun initItems() {
        if (DYNAMITE == null) {
            println("Dynamite is not initialized!")
        }

        println("Initializing Items")
        if (Config.Blocks.poles) {
            registerFuelForEach(arrayOf(OAK_POLE, SPRUCE_POLE, BIRCH_POLE, JUNGLE_POLE, ACACIA_POLE, DARK_OAK_POLE), 300)
            registerFuelForEach(arrayOf(STRIPPED_OAK_POLE, STRIPPED_SPRUCE_POLE, STRIPPED_BIRCH_POLE, STRIPPED_JUNGLE_POLE, STRIPPED_ACACIA_POLE, STRIPPED_DARK_OAK_POLE), 300)
        }
        println("Items initialized!")
    }

}