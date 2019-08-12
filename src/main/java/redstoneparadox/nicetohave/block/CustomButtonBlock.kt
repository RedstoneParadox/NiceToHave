package redstoneparadox.nicetohave.block

import net.minecraft.block.AbstractButtonBlock
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.ViewableWorld

class CustomButtonBlock(val ticks : Int, settings: Settings?) : AbstractButtonBlock(false, settings) {

    override fun getClickSound(press: Boolean): SoundEvent {
        return if (press) SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON else SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF
    }

    override fun getTickRate(viewableWorld_1: ViewableWorld?): Int {
        return ticks
    }
}