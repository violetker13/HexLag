package violetker13.Hex.HexLag.casting

import at.petrak.hexcasting.api.casting.math.HexDir
import violetker13.Hex.HexLag.spells.hexlag_delay
import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action

import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.hex.HexActions

import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import violetker13.Hex.HexLag.HexLag.MOD_ID
import violetker13.Hex.HexLag.spells.hexlag_ConjureArrow

object HexLagPatterns {
    @JvmStatic
    fun init() {
        //register("hexlag_delay", "aqdeeewdwwd", HexDir.SOUTH_WEST, hexlag_delay())
        register("hexlag_conjurearrow","wwdedqde", HexDir.NORTH_EAST, hexlag_ConjureArrow() )
    }


    private fun register(name: String, signature: String, startDir: HexDir, action: Action) {
        Registry.register(
            HexActions.REGISTRY,
            Identifier.of(MOD_ID, name),
            ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), action)
        )
    }
}