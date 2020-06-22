package io.github.redstoneparadox.nicetohave.item

import net.minecraft.item.FoodComponent

object FoodComponents {

    val JUICE: FoodComponent = FoodComponent.Builder().hunger(4).saturationModifier(0.4f).snack().build()
}