package net.redstoneparadox.nicetohave.world.gen.decorator

import com.mojang.datafixers.Dynamic
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.DecoratorConfig
import net.minecraft.world.gen.decorator.NopeDecoratorConfig
import java.util.function.Function

object Decorators {

    val SURFACE = SurfaceDecorator(Function<Dynamic<*>, NopeDecoratorConfig> { t -> NopeDecoratorConfig.deserialize(t) })

    fun registerDecorators() {
        register("surface", SURFACE)
    }

    private fun <T : DecoratorConfig, G : Decorator<T>> register(id: String, decorator: G) {
        Registry.register(Registry.DECORATOR, "nicetohave:$id", decorator)
    }
}