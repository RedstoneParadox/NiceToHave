package redstoneparadox.nicetohave.block

import net.fabricmc.fabric.api.block.FabricBlockSettings
import net.minecraft.block.AbstractButtonBlock
import net.minecraft.block.Material
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.WorldView

class CustomButtonBlock(val ticks : Int) : AbstractButtonBlock(false, FabricBlockSettings.of(Material.PART).noCollision().strength(0.5f, 0.1f).breakByHand(true).build()) {

    override fun getClickSound(press: Boolean): SoundEvent {
        return if (press) SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON else SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF
    }

    override fun getTickRate(worldView: WorldView?): Int {
        return ticks
    }
}