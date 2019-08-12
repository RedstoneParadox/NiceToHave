package redstoneparadox.nicetohave.util

import net.minecraft.entity.vehicle.AbstractMinecartEntity
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by RedstoneParadox on 5/25/2019.
 */
object MinecartTracker {

    private val minecarts : ArrayList<AbstractMinecartEntity> = ArrayList()

    fun addCart(cart : AbstractMinecartEntity) {
        minecarts.add(cart)
    }

    fun removeCart(cart: AbstractMinecartEntity) {
        minecarts.remove(cart)
    }

    fun connectCarts(cart: AbstractMinecartEntity): Optional<AbstractMinecartEntity> {

        var resultCart : Optional<AbstractMinecartEntity> = Optional.empty()

        for (minecart in minecarts) {

            if (cart.dimension == minecart.dimension) {

                cart.movementDirection
            }
        }

        return resultCart
    }
}