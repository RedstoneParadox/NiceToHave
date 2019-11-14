package redstoneparadox.nicetohave.util.newconfig

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.Identifier
import redstoneparadox.paradoxconfig.config.RootConfigCategory
import redstoneparadox.paradoxconfig.forceReloadConfig
import redstoneparadox.paradoxconfig.serialization.ConfigDeserializer
import redstoneparadox.paradoxconfig.serialization.ConfigSerializer
import redstoneparadox.paradoxconfig.serialization.jankson.JanksonConfigDeserializer
import redstoneparadox.paradoxconfig.serialization.jankson.JanksonConfigSerializer
import java.io.File

object Config: RootConfigCategory("config.json5") {
    override val deserializer: ConfigDeserializer = JanksonConfigDeserializer()
    override val serializer: ConfigSerializer = JanksonConfigSerializer()

    var initialized = false

    fun transferFile() {
        if (initialized) return
        initialized = true

        val file = File(FabricLoader.getInstance().configDirectory, "nicetohave.json5")

        if (file.exists()) {
            val data = file.readText()
            val newFile = File(FabricLoader.getInstance().configDirectory, "nicetohave/config.json5")
            if (newFile.exists()) {
                newFile.writeText(data)
                forceReloadConfig(Identifier("nicetohave", "config.json5"))
            }
        }
    }
}