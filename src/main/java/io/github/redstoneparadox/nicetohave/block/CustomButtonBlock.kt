package io.github.redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.AbstractButtonBlock
import net.minecraft.block.Material
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

class CustomButtonBlock(val ticks: Int): AbstractButtonBlock(false, FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5f, 0.1f).breakByHand(true)) {

    override fun getClickSound(press: Boolean): SoundEvent {
        return if (press) SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON else SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF
    }

    fun getPressTicks(): Int {
        return ticks * 2
    }
}