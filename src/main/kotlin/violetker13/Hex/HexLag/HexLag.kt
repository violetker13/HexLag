package violetker13.Hex.HexLag

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import violetker13.Hex.HexLag.casting.HexLagPatterns

object HexLag : ModInitializer {
    const val MOD_ID = "hexlag"
    val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        HexLagPatterns.init()

    }
}